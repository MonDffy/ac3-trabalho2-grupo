
public class Simulation {

    // static void previous(JLabel label, InstructionStatus instructionStatus,
    // ReservationStations reservationStations,
    // RegisterStatus registerStatus) {

    // }

    static void next(InstructionStatus instructionStatus, ReorderBuffer reorderBuffer,
            ReservationStations reservationStations, FPRegisterStatus registerStatus) {

        // Despacho
        String nextInstruction = "";
        int pos;
        try {
            nextInstruction = instructionStatus.getNextInstruction();
        } catch (Exception e) {
        } finally {
            String[] str = nextInstruction.split("[ .()]", 5);
            int rb = reorderBuffer.getNotBusy();
            int rs = testReservationStation(str[0], reservationStations);

            if (rb != -1 && (rs != -1 || str[0].equals("BR"))) {
                reorderBuffer.setInstructions(rb, nextInstruction);
                reorderBuffer.setBusy(rb, "Yes");
                if (str[0].equals("STR")) {
                    pos = 2;
                } else {
                    pos = 1;
                }
                reorderBuffer.setDestination(rb, str[pos]);
                if (!str[0].equals("BR")) {
                    reservationStations.setBusy(rs, "Yes");
                    reservationStations.setOp(rs, str[0]);
                    reservationStations.setDest(rs, reorderBuffer.getEntry(rb));
                    setJK(reservationStations, reorderBuffer, str, pos, rb, rs);
                    String aux[] = str[pos].split("X");
                    String aux2 = aux[1];
                    registerStatus.setLine2(Integer.parseInt(aux2), reservationStations.getName(rs));
                    registerStatus.setLine3(Integer.parseInt(aux2), "Busy");
                }
                reorderBuffer.setState(rb, "Despacho");
                instructionStatus.setStatus(rb, "Despacho");
            } else {
                System.out.println("Não dá");
            }

        }

        // Execução

        // Verificar se os operandos estão disponíveis
        // Executar a operação (podem levar vários ciclos e load exige duas etapas)

        // Escrita

        // Escreve resultado no CDB

    }

    static int testReservationStation(String str,
            ReservationStations reservationStations) {

        int x = -1;

        String busy;
        switch (str) {
            case "LDR", "STR":
                for (int j = 0; j < 2; j++) {
                    busy = reservationStations.getBusy(j);
                    if (busy == "No") {
                        x = j;
                        break;
                    }
                }
                break;
            case "ADD", "SUB":
                for (int j = 2; j < 5; j++) {
                    busy = reservationStations.getBusy(j);
                    if (busy == "No") {
                        x = j;
                        break;
                    }
                }
                break;
            case "MUL", "SDIV":
                for (int j = 5; j < 7; j++) {
                    busy = reservationStations.getBusy(j);
                    if (busy == "No") {
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
            if (str[2].equals(reorderBuffer.getDestination(i))) {
                reservationStations.setQj(rs, "#" + reorderBuffer.getEntry(i));
                bool = false;
            }
        }
        if (bool == true) {
            if (str[0].equals("STR")) {
                reservationStations.setVj(rs, str[1]);
            } else {
                reservationStations.setVj(rs, str[2]);
            }
        }
        bool = true;
        for (int i = 0; i < 6; i++) {
            if (str[3].equals(reorderBuffer.getDestination(i))) {
                reservationStations.setQk(rs, "#" + reorderBuffer.getEntry(i));
                bool = false;
            }
        }
        if (bool == true) {
            reservationStations.setVk(rs, str[3]);
        }
    }

    static void setValue(String[] str, int rb, ReorderBuffer reorderBuffer) {
        String x = "";
        switch (str[0]) {
            case "LDR":
                x = "MEM{" + str[2] + "}";
                break;
            case "STR":
                x = str[1];
                break;
            case "ADD":
                x = str[1] + " + " + str[2];
                break;
            case "SUB":
                x = str[1] + " - " + str[2];
                break;
            case "MUL":
                x = str[1] + " x " + str[2];
                break;
            case "SDIV":
                x = str[1] + " / " + str[2];
                break;
            default:
        }
        reorderBuffer.setValue2(rb, x);
    }

}
