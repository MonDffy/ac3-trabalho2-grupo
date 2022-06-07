import java.security.interfaces.RSAKey;

public class Simulation {

    static int nextCount = -1;
    static int is = -1;

    // static void previous(JLabel label, InstructionStatus instructionStatus,
    // ReservationStations reservationStations,
    // RegisterStatus registerStatus) {

    // }

    static void next(InstructionStatus instructionStatus, ReorderBuffer reorderBuffer,
            ReservationStations reservationStations, FPRegisterStatus registerStatus) {

        // Despacho

        nextCount++;
        // System.out.println(nextCount);

        String nextInstruction = "";
        if (is < 6) {
            for (int i = 0; i < 6; i++) {
                if (reorderBuffer.getNotBusy() != -1) {
                    nextInstruction = instructionStatus.getNextInstruction();
                    is++;
                    despacho(nextInstruction, reorderBuffer, reservationStations, registerStatus, instructionStatus);
                }

            }
        }
        execucao(reorderBuffer, reservationStations, registerStatus, instructionStatus);

        // execucao(nextInstruction);

        // Escrita

        // Escreve resultado no CDB

    }

    static void despacho(String nextInstruction, ReorderBuffer reorderBuffer,
            ReservationStations reservationStations,
            FPRegisterStatus registerStatus, InstructionStatus instructionStatus) {

        int pos;
        String[] str = nextInstruction.split("[ .() ]", 5);
        int rb = reorderBuffer.getNotBusy();
        int rs = testReservationStation(str[0], reservationStations, reorderBuffer, rb);
        if (rb != -1 && (rs != -1 || str[0].equals("BR"))) {
            reorderBuffer.setInstructions(rb, nextInstruction);
            reorderBuffer.setBusy(rb, "Yes");
            if (str[0].equals("STR")) {
                pos = 2;
            } else {
                pos = 1;
            }
            if (!str[0].equals("BR")) {
                reservationStations.setBusy(rs, "Yes");
                reservationStations.setOp(rs, str[0]);
                reservationStations.setDest(rs, reorderBuffer.getEntry(rb));
                reorderBuffer.setReorderList(reorderBuffer.getEntry(rb));
                setJK(reservationStations, reorderBuffer, str, pos, rb, rs);
                String aux[] = str[pos].split("X");
                String aux2 = aux[1];
                registerStatus.setLine2(Integer.parseInt(aux2), reservationStations.getName(rs));
                registerStatus.setLine3(Integer.parseInt(aux2), "Busy");
            }
            reorderBuffer.setDestination(rb, str[pos]);
            reorderBuffer.setState(rb, "Despacho");
        }
    }

    static void execucao(ReorderBuffer reorderBuffer, ReservationStations reservationStations,
            FPRegisterStatus registerStatus, InstructionStatus instructionStatus) {

        int count;
        int rb;
        String instruction;
        for (int rs = 0; rs < reservationStations.name.length; rs++) {
            System.out.println(rs);
            if (reservationStations.getVj(rs) != "") {
                if (reservationStations.getVk(rs) != "") {
                    count = reservationStations.getCount(rs);
                    if (count == 0) {
                        reservationStations.setBusy(rs, "No");
                        rb = Integer.parseInt(reservationStations.getDest(rs)) - 1;
                        instruction = reorderBuffer.getInstruction(rb);
                        setValue(instruction, rb, reorderBuffer);
                        reorderBuffer.setState(rb, "Execução");

                    } else {
                        reservationStations.setCount(rs, --count);
                    }
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
            int rb, int rs) {

        Boolean bool = true;
        for (int i = 0; i < reorderBuffer.getEntrySize(); i++) {
            if (i != rs && str[2].equals(reorderBuffer.getDestination(i))) {
                reservationStations.setQj(rs, "#" + reorderBuffer.getEntry(i));
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
        for (int i = 0; i < 6; i++) {
            if (str[3].equals(reorderBuffer.getDestination(i))) {
                reservationStations.setQk(rs, "#" + reorderBuffer.getEntry(i));
                reservationStations.setQj(rs, "");
                bool = false;
                break;
            }
        }
        if (bool == true) {
            reservationStations.setVk(rs, str[3]);
            reservationStations.setQj(rs, "");
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

}
