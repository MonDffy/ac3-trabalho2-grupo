import java.util.Random;

public class Simulation {

    static int nextCount = -1;
    static int is = -1;
    static int jumpValue;
    static int countInst = -1;
    static Boolean jump = false;
    // static int rename = -1;

    // static void previous(JLabel label, InstructionStatus instructionStatus,
    // ReservationStations reservationStations,
    // RegisterStatus registerStatus) {

    // }

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

        // if (is < 6) {
        // for (int j = 0; j < 6; j++) {
        // if (reorderBuffer.getNotBusy() != -1) {
        // String[] str = nextInstruction.split("[ .() ]", 5);
        // if (str[0].equals("BEQ")) {
        // reorderBuffer.setJump(j, randomBool());
        // }
        // if (reorderBuffer.getJump(j)) {
        // for (int i = 0; i < 3; i++) {
        // nextInstruction = instructionStatus.getNextInstruction();
        // }
        // } else {
        // nextInstruction = instructionStatus.getNextInstruction();
        // }
        // is++;
        // despacho(nextInstruction, reorderBuffer, reservationStations, registerStatus,
        // instructionStatus);
        // }

        // }
        // } else {
        if (jump) {
            for (int i = 0; i < jumpValue; i++) {
                instructionStatus.getNextInstruction();
                countInst++;
                jump = false;
            }
        }
        if (reorderBuffer.getNotBusy() != -1) {
            nextInstruction = instructionStatus.getNextInstruction();
            countInst++;
            // if (nextInstruction != "break") {
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
        // }

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
        // if (reorderBuffer.reorderList.contains(str[pos])) {
        // str[0] = "R" + rename + 1;
        // }
        if (rb != -1 && (rs != -1 || str[0].equals("BEQ"))) {
            reorderBuffer.setInstructions(rb, nextInstruction);
            reorderBuffer.setBusy(rb, "Yes");
            reorderBuffer.setDestination(rb, str[pos]);
            if (!str[0].equals("BEQ")) {
                reservationStations.setBusy(rs, "Yes");
                reservationStations.setOp(rs, str[0]);
                reservationStations.setDest(rs, reorderBuffer.getEntry(rb));
                setJK(reservationStations, reorderBuffer, str, pos, rs);
                String aux[] = str[pos].split("X");
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
        String instruction;
        String[] str;
        for (int rs = 0; rs < reservationStations.name.length; rs++) {
            if (reservationStations.getVj(rs) != "" && !reorderBuffer.getInstruction(0).contains("BEQ")) {
                rb = Integer.parseInt(reservationStations.getDest(rs)) - 1;
                instruction = reorderBuffer.getInstruction(rb);
                str = instruction.split("[ .() ]", 5);
                if ((reservationStations.getVk(rs) != "" || str[0].equals("LDR") || str[0].equals("STR"))) {
                    count = reservationStations.getCount(rs);
                    if (count == 0) {
                        reservationStations.setBusy(rs, "No");
                        setValue(instruction, rb, reorderBuffer);
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
        if (instruction.contains("BEQ") && reorderBuffer.getState(index).equals("Despacho")) {

            reorderBuffer.setState(index, "Execução");
            Boolean bool = randomBool();
            System.out.println("Execução jump: " + bool);
            if (reorderBuffer.getJump(index) != bool) {
                if (!bool) {
                    for (int i = index; i < 5; i++) {
                        reorderBuffer.deleteRow();
                        reservationStations.deleteRow();
                    }
                    instructionStatus.setInst(instructionStatus.getInst2());
                } else {
                    for (int i = 0; i < jumpValue; i++) {
                        instructionStatus.getNextInstruction();
                        countInst++;
                    }
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
                    if (!reorderBuffer.getValue(rb).equals("")) {
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
        if ((!reorderBuffer.getValue(index).equals("") && reorderBuffer.getState(index).equals("Write Result"))
                || (reorderBuffer.getInstruction(index).contains("BEQ")
                        && reorderBuffer.getState(index).equals("Execução"))) {
            reorderBuffer.setState(index, "Commit");
        }

    }

    static void posCommit(ReorderBuffer reorderBuffer, ReservationStations reservationStations,
            FPRegisterStatus registerStatus, InstructionStatus instructionStatus) {

        if (reorderBuffer.getState(0).equals("Commit")) {
            reorderBuffer.deleteRow();
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
        // if (reorderBuffer.getInstruction(5).equals("")) {

        // }
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
                        reservationStations.setCount(j, 3);
                        x = j;
                        break;
                    }
                }
                break;
            case "MUL", "SDIV":
                for (int j = 5; j < 7; j++) {
                    busy = reservationStations.getBusy(j);
                    if (busy == "No") {
                        reservationStations.setCount(j, 2);
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
            if (str[2].equals(reorderBuffer.getReorderList(i))) {
                reservationStations.setQj(rs, "#" + (Integer.parseInt(reorderBuffer.getEntry(i))));
                reservationStations.setVj(rs, "");
                bool = false;
                break;
            }
        }
        if (bool == true) {
            if (str[0].equals("STR")) {
                reservationStations.setVj(rs, str[1]);
                reservationStations.setQj(rs, "");
            } else {
                reservationStations.setVj(rs, str[2]);
                reservationStations.setQj(rs, "");
            }
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

}
