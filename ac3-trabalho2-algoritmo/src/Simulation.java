import java.util.ArrayList;

public class Simulation {

    // static void previous(JLabel label, InstructionStatus instructionStatus,
    // ReservationStations reservationStations,
    // RegisterStatus registerStatus) {

    // }

    static void next(InstructionStatus instructionStatus, ReorderBuffer reorderBuffer,
            ReservationStations reservationStations, FPRegisterStatus registerStatus) {

        // Despacho

        String nextInstruction = instructionStatus.getNextInstruction();
        String[] str = nextInstruction.split("[ .()]", 5);
        int rb = reorderBuffer.getNotBusy();
        int rs = testReservationStation(str[0], reservationStations);
        if (rb != -1 && (rs != -1 || str[0].equals("BR"))) {
            reorderBuffer.setInstructions(rb, nextInstruction);
            reorderBuffer.setBusy(rb, "Yes");
            if (!str[0].equals("BR")) {
                reservationStations.setBusy(rs, "Yes");
                reservationStations.setOp(rs, str[0]);
                reservationStations.setDest(rs, reorderBuffer.getEntry(rb));
                reservationStations.setVj(rs, str[2]);
                reservationStations.setVk(rs, str[3]);
            }
            reorderBuffer.setState(rb, "Despacho");
            instructionStatus.setStatus(rb, "Despacho");
        } else {
            System.out.println("Não dá");
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
            case "LDUR", "STUR":
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

}
