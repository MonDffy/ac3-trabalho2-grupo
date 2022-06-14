import java.util.Random;

public class Simulation {

    static int nextCount = -1;
    static int is = -1;
    static int jumpValue;
    static int countInst = -1;
    static Boolean jump = false;
    static int rename = 0;
    static String[][] renamed = new String[30][2];

    Simulation() {
        for (int i = 0; i < 30; i++) {
            renamed[i][0] = "";
            renamed[i][1] = "";
        }
    }

    static void next(InstructionStatus instructionStatus, ReorderBuffer reorderBuffer,
            ReservationStations reservationStations, FPRegisterStatus registerStatus) {

        nextCount++;

        String nextInstruction = "";

        posCommit(reorderBuffer, reservationStations, registerStatus,
                instructionStatus);

        commit(reorderBuffer, reservationStations, registerStatus,
                instructionStatus);

        writeResult(reorderBuffer, reservationStations, registerStatus, instructionStatus);

        execucao(reorderBuffer, reservationStations, registerStatus, instructionStatus);

        if (jump) {
            for (int i = 0; i < jumpValue; i++) {
                instructionStatus.getNextInstruction();
                countInst++;
                jump = false;
            }
        }
        if (reservationStations.getNotBusy() != -1) {
            if (reorderBuffer.getNotBusy() != -1) {
                nextInstruction = instructionStatus.getNextInstruction();
                countInst++;
                if (nextInstruction.contains("BEQ")) {
                    instructionStatus.setInst2(countInst);
                    String[] str = nextInstruction.split("[ .() ]", 5);
                    jumpValue = Integer.parseInt(str[3]);
                    jump = randomBool();
                    reorderBuffer.setJump(reorderBuffer.getNotBusy(), jump);
                }
                is++;
                despacho(nextInstruction, reorderBuffer, reservationStations, registerStatus, instructionStatus);
            }
        }

        Boolean bool = false;
        for (int i = 0; i < reorderBuffer.getEntrySize(); i++) {
            if (!reorderBuffer.getInstruction(i).equals("")) {
                break;
            } else {
                bool = true;
            }
        }
        if (bool == true) {
            Swing.fim();
        }

    }

    static void despacho(String nextInstruction, ReorderBuffer reorderBuffer,
            ReservationStations reservationStations,
            FPRegisterStatus registerStatus, InstructionStatus instructionStatus) {

        int pos;
        String[] str = nextInstruction.split("[ .() ]", 5);
        int rb = reorderBuffer.getNotBusy();
        int rs = testReservationStation(str[0], reservationStations, reorderBuffer, rb);
        if (str[0].equals("STR")) {
            pos = 2;
        } else {
            pos = 1;
        }
        if ((rb != -1 && (rs != -1 || str[0].equals("BEQ")))) {
            reorderBuffer.setInstructions(rb, nextInstruction);
            reorderBuffer.setBusy(rb, "Yes");
            reorderBuffer.setDestination(rb, str[pos]);
            if (!str[0].equals("BEQ") && !str[0].equals("STR")) {
                reservationStations.setBusy(rs, "Yes");
                reservationStations.setOp(rs, str[0]);
                reservationStations.setDest(rs, reorderBuffer.getEntry(rb));
                setJK(reservationStations, reorderBuffer, str, pos, rs);
                String aux[] = str[pos].split("[XR]");
                String aux2 = aux[1];
                registerStatus.setLine2(Integer.parseInt(aux2), reservationStations.getName(rs));
                registerStatus.setLine3(Integer.parseInt(aux2), "Busy");
                reorderBuffer.reorderList.add(str[pos]);
            } else if (str[0].equals("BEQ")) {
                reservationStations.setBusy(rs, "Yes");
                reservationStations.setOp(rs, str[0]);
                reservationStations.setDest(rs, reorderBuffer.getEntry(rb));
                setJK(reservationStations, reorderBuffer, str, pos, rs);
                String aux[] = str[pos].split("[XR]");
                String aux2 = aux[1];
                registerStatus.setLine2(Integer.parseInt(aux2), reservationStations.getName(rs));
                registerStatus.setLine3(Integer.parseInt(aux2), "Busy");
                reorderBuffer.reorderList.add(str[pos]);
            }
            reorderBuffer.setState(rb, "Despacho");
        }
    }

    static void execucao(ReorderBuffer reorderBuffer, ReservationStations reservationStations,
            FPRegisterStatus registerStatus, InstructionStatus instructionStatus) {

        int count;
        int rb;
        String[] str;
        String instruction;
        for (int index = 0; index < reorderBuffer.getEntrySize(); index++) {
            if ((reorderBuffer.getInstruction(index).contains("STR")
                    && reorderBuffer.getState(index).equals("Despacho"))) {
                reorderBuffer.setState(index, "Execução");
            }
        }
        for (int rs = 0; rs < reservationStations.name.length; rs++) {
            if (reservationStations.getBusy(rs).equals("Yes")) {
                if (reservationStations.getVj(rs) != "" && !reorderBuffer.getInstruction(0).contains("BEQ")
                        && reservationStations.getBusy(rs).equals("Yes")) {
                    rb = Integer.parseInt(reservationStations.getDest(rs)) - 1;
                    instruction = reorderBuffer.getInstruction(rb);
                    str = instruction.split("[ .() ]", 5);
                    if ((reservationStations.getVk(rs) != "" || str[0].equals("LDR") || str[0].equals("STR"))) {
                        count = reservationStations.getCount(rs);
                        if (count == 0) {
                            reservationStations.setBusy(rs, "No");
                            setValue(instruction, rb, reorderBuffer);
                            correcao(reorderBuffer, reservationStations, registerStatus, instructionStatus);
                        } else {
                            reservationStations.setCount(rs, --count);
                        }
                        if (reorderBuffer.getState(rb).equals("Despacho")) {
                            reorderBuffer.setState(rb, "Execução");
                        }
                    }
                }
            }
            int index = 0;
            instruction = reorderBuffer.getInstruction(index);
            if (instruction.contains("BEQ") && reorderBuffer.getState(index).equals("Execução")) {
                reorderBuffer.setState(index, "Execução");
                count = reservationStations.getCount(rs);
                System.out.println(count);
                if (count == 0) {
                    reorderBuffer.setState(index, "Write Result");
                    Boolean bool = randomBool();
                    System.out.println("Jump real: " + bool);
                    if (reorderBuffer.getJump(index) != bool) {
                        if (bool) {
                            rb = Integer.parseInt(reservationStations.getDest(rs)) - 1;
                            for (int i = index; i < (reorderBuffer.getEntrySize() - rb); i++) {
                                reorderBuffer.deleteRow();
                                reservationStations.deleteRow();
                            }
                        } else {
                            for (int i = 0; i < jumpValue; i++) {
                                instructionStatus.getNextInstruction();
                                countInst++;
                            }
                        }
                    } else {
                        if (bool) {
                            instructionStatus.getNextInstruction();
                            countInst++;
                        } else {
                            rb = Integer.parseInt(reservationStations.getDest(rs)) - 1;
                            for (int i = index; i < (reorderBuffer.getEntrySize()); i++) {
                                reorderBuffer.deleteRow();
                                reservationStations.deleteRow();
                            }
                            instructionStatus.setInst(instructionStatus.getInst2());
                        }
                    }
                } else {
                    reservationStations.setCount(rs, --count);

                }
            }
        }

    }

    static void writeResult(ReorderBuffer reorderBuffer, ReservationStations reservationStations,
            FPRegisterStatus registerStatus, InstructionStatus instructionStatus) {

        int rb;
        String instrucao;
        String aux;
        String[] str;
        int pos;
        for (int index = 0; index < reorderBuffer.getEntrySize(); index++) {
            if ((reorderBuffer.getInstruction(index).contains("STR")
                    && reorderBuffer.getState(index).equals("Execução"))) {
                reorderBuffer.setState(index, "Write Result");
            }
        }
        for (int index = 0; index < reorderBuffer.getEntrySize(); index++) {
            if ((reorderBuffer.getInstruction(index).contains("LDR")
                    && reorderBuffer.getState(index).equals("Execução") && !reorderBuffer.getValue(index).equals(""))) {
                reorderBuffer.setState(index, "Write Result");
            }
        }
        for (int rs = 0; rs < reservationStations.name.length; rs++) {
            if (reservationStations.getQj(rs) != "") {
                aux = reservationStations.getQj(rs).replace("#", "");
                rb = Integer.parseInt(aux) - 1;
                aux = reservationStations.getDest(rs).replace("#", "");
                instrucao = reorderBuffer.getInstruction(Integer.parseInt(aux) - 1);
                str = instrucao.split("[ .() ]", 5);
                if (str[0].equals("STR")) {
                    pos = 2;
                } else {
                    pos = 1;
                }
                if (rb > -1 && reorderBuffer.getValue(rb) != "") {
                    reorderBuffer.removeFromList(reorderBuffer.getDestination(rb));
                    setJK(reservationStations, reorderBuffer, str, pos, rs);

                    if (reorderBuffer.getState(rb).equals("Execução")) {
                        reorderBuffer.setState(rb, "Write Result");
                    }
                }
            }
            if (reservationStations.getQk(rs) != "") {
                aux = reservationStations.getQk(rs).replace("#", "");
                rb = Integer.parseInt(aux) - 1;
                aux = reservationStations.getDest(rs).replace("#", "");
                instrucao = reorderBuffer.getInstruction(Integer.parseInt(aux) - 1);
                str = instrucao.split("[ .() ]", 5);
                if (str[0].equals("STR")) {
                    pos = 2;
                } else {
                    pos = 1;
                }
                if (!reorderBuffer.getValue(rb).equals("")) {
                    reorderBuffer.removeFromList(reorderBuffer.getDestination(rb));
                    setJK(reservationStations, reorderBuffer, str, pos, rs);

                    if (reorderBuffer.getState(rb).equals("Execução")) {
                        reorderBuffer.setState(rb, "Write Result");
                    }
                }
            }
            if (reservationStations.getQj(rs) == "" && reservationStations.getQk(rs) == "") {
                aux = reservationStations.getDest(rs).replace("#", "");
                if (aux != "") {
                    rb = Integer.parseInt(aux) - 1;
                    if (rb > -1 && !reorderBuffer.getValue(rb).equals("")) {
                        if (reorderBuffer.getState(rb).equals("Execução")) {
                            reorderBuffer.setState(rb, "Write Result");
                        }
                    }
                }
            }

        }
    }

    static void commit(ReorderBuffer reorderBuffer, ReservationStations reservationStations,
            FPRegisterStatus registerStatus, InstructionStatus instructionStatus) {

        int index = 0;
        if ((!reorderBuffer.getValue(index).equals("") && reorderBuffer.getState(index).equals("Write Result"))) {
            reorderBuffer.setState(index, "Commit");
        }
        for (index = 0; index < reorderBuffer.getEntrySize(); index++) {
            if ((reorderBuffer.getInstruction(index).contains("BEQ")
                    && reorderBuffer.getState(index).equals("Write Result"))) {
                reorderBuffer.setState(index, "Commit");
            }
        }
        for (index = 0; index < reorderBuffer.getEntrySize(); index++) {
            if ((reorderBuffer.getInstruction(index).contains("STR")
                    && reorderBuffer.getState(index).equals("Write Result"))) {
                reorderBuffer.setState(index, "Commit");
            }
        }

    }

    static void posCommit(ReorderBuffer reorderBuffer, ReservationStations reservationStations,
            FPRegisterStatus registerStatus, InstructionStatus instructionStatus) {

        if (reorderBuffer.getState(0).equals("Commit")) {
            reorderBuffer.deleteRow();
            for (int j = 1; j < reorderBuffer.getEntrySize(); j++) {
                String instruction = reorderBuffer.getInstruction(j);
                String[] str = instruction.split("[ .() ]", 5);
                if (instruction.length() < 1)
                    break;
                for (int i = 0; i < 6; i++) {
                    if (str[1].equals(renamed[i][1])) {
                        str[1] = renamed[i][0];
                    }
                    if (str[2].equals(renamed[i][1])) {
                        str[2] = renamed[i][0];
                    }
                    if (str[3].equals(renamed[i][1])) {
                        str[3] = renamed[i][0];
                    }
                    instruction = str[0] + " " + str[1] + "." + str[2] + "." + str[3];
                    reorderBuffer.setInstructions(j, instruction);
                    reorderBuffer.setDestination(j, str[1]);
                }

            }
            for (int i = 0; i < reservationStations.dest.length; i++) {
                if (reservationStations.getBusy(i) == "Yes") {
                    int aux = Integer.parseInt(reservationStations.getDest(i));
                    reservationStations.setDest(i, "" + --aux);
                }
                if (reservationStations.getQj(i) != null && reservationStations.getQj(i) != "") {
                    int aux = Integer.parseInt(reservationStations.getQj(i).replace("#", ""));
                    aux--;
                    reservationStations.setQj(i, "#" + aux);
                }
            }
        }
    }

    static int testReservationStation(String str,
            ReservationStations reservationStations, ReorderBuffer reorderBuffer, int rb) {

        int x = -1;

        String busy;
        switch (str) {
            case "LDR", "STR":
                for (int j = 0; j < 2; j++) {
                    busy = reservationStations.getBusy(j);
                    if (busy == "No") {
                        reservationStations.setCount(j, 1);
                        x = j;
                        break;
                    }
                }
                break;
            case "ADD", "SUB":
                for (int j = 2; j < 5; j++) {
                    busy = reservationStations.getBusy(j);
                    if (busy == "No") {
                        reservationStations.setCount(j, 2);
                        x = j;
                        break;
                    }
                }
                break;
            case "MUL":
                for (int j = 5; j < 7; j++) {
                    busy = reservationStations.getBusy(j);
                    if (busy == "No") {
                        reservationStations.setCount(j, 4);
                        x = j;
                        break;
                    }
                }
                break;
            case "SDIV":
                for (int j = 5; j < 7; j++) {
                    busy = reservationStations.getBusy(j);
                    if (busy == "No") {
                        reservationStations.setCount(j, 6);
                        x = j;
                        break;
                    }
                }
                break;
            case "BEQ":
                for (int j = 7; j < 8; j++) {
                    busy = reservationStations.getBusy(j);
                    if (busy == "No") {
                        reservationStations.setCount(j, 4);
                        x = j;
                        break;
                    }
                }
                break;
            default:
        }
        return x;
    }

    static void setJK(ReservationStations reservationStations, ReorderBuffer reorderBuffer, String[] str, int pos,
            int rs) {

        Boolean bool = true;
        for (int i = 0; i < reorderBuffer.reorderList.size(); i++) {
            if (str[0].equals("MUL")) {
                if (str[2].equals(reorderBuffer.getReorderList(i))) {
                    reservationStations.setQj(rs, "#" + (Integer.parseInt(reorderBuffer.getEntry(i))));
                    reservationStations.setVj(rs, "");
                    bool = false;
                    break;
                }
            } else if (str[2].equals(reorderBuffer.getReorderList(i))) {
                reservationStations.setQj(rs, "#" + (Integer.parseInt(reorderBuffer.getEntry(i))));
                reservationStations.setVj(rs, "");
                bool = false;
                break;
            }
        }
        if (bool == true) {
            reservationStations.setVj(rs, str[2]);
            reservationStations.setQj(rs, "");
        }
        bool = true;
        for (int i = 0; i < reorderBuffer.reorderList.size(); i++) {
            if (str[3].equals(reorderBuffer.getReorderList(i))) {
                reservationStations.setQk(rs, "#" + reorderBuffer.getEntry(i));
                reservationStations.setVk(rs, "");
                bool = false;
                break;
            }
        }
        if (bool == true) {
            reservationStations.setVk(rs, str[3]);
            reservationStations.setQk(rs, "");
        }
    }

    static void setValue(String instruction, int rb, ReorderBuffer reorderBuffer) {
        String[] str = instruction.split("[ .() ]", 5);
        String x = "";
        switch (str[0]) {
            case "LDR":
                x = "MEM{" + str[2] + "}";
                break;
            case "STR":
                x = str[1];
                break;
            case "ADD":
                x = str[2] + " + " + str[3];
                break;
            case "SUB":
                x = str[2] + " - " + str[3];
                break;
            case "MUL":
                x = str[2] + " x " + str[3];
                break;
            case "SDIV":
                x = str[2] + " / " + str[3];
                break;
            default:
        }
        reorderBuffer.setValue(rb, x);
    }

    static Boolean randomBool() {
        Random ran = new Random();
        return ran.nextBoolean();
    }

    static void correcao(ReorderBuffer reorderBuffer, ReservationStations reservationStations,
            FPRegisterStatus registerStatus, InstructionStatus instructionStatus) {
        int j;
        String[] str;
        for (int i = 0; i < reservationStations.name.length; i++) {
            if (reservationStations.getBusy(i).equals("Yes")) {
                if (reservationStations.getQj(i) != "") {
                    j = Integer.parseInt(reservationStations.getQj(i).replace("#", ""));
                    if (reorderBuffer.getValue(j) != "") {
                        str = reorderBuffer.getInstruction(j).split("[ .() ]", 5);
                        reservationStations.setVj(i, str[2]);
                        reservationStations.setQj(i, "");
                    }
                }
                if (reservationStations.getQk(i) != "") {
                    j = Integer.parseInt(reservationStations.getQk(i).replace("#", ""));
                    if (reorderBuffer.getValue(j) != "") {
                        str = reorderBuffer.getInstruction(j).split("[ .() ]", 5);
                        reservationStations.setVk(i, str[3]);
                        reservationStations.setQk(i, "");
                    }
                }
            }
        }
    }

}
