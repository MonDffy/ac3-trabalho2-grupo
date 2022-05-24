import java.util.ArrayList;

public class Simulation {

    // static void previous(JLabel label, InstructionStatus instructionStatus,
    // ReservationStations reservationStations,
    // RegisterStatus registerStatus) {

    // }

    static void next(ArrayList<String[]> instructions, InstructionStatus instructionStatus,
            ReservationStations reservationStations, RegisterStatus registerStatus) {

        // teste de conflito e renomeação (fazer por ultimo)

        // instructions já está splitada

        int rs = testReservationStation(instructions, reservationStations);

        if (rs == -1) {
            System.out.println("colocar em espera"); // Apenas para teste
            // colocar em espera
        } else {
            System.out.println("inserir na estação de reserva"); // Apenas para teste
            // inserir na estação de reserva
            reservationStations.setBusy(rs, "Yes"); // Apenas para teste
        }

    }

    static int testReservationStation(ArrayList<String[]> instructions,
            ReservationStations reservationStations) {

        // definir as instruções
        int x = -1;
        int i = 3; // Define qual instrução será testada, ainda precisa fazer essa parte
        String busy;
        String str = instructions.get(i)[0];
        System.out.println(str);
        i++;
        switch (str) {
            case "L.D":
                for (int j = 0; j < 2; j++) {
                    busy = reservationStations.getBusy(j);
                    if (busy == "No") {
                        x = j;
                        break;
                    }
                }
                break;
            case "ADD.D", "SUB.D":
                for (int j = 2; j < 5; j++) {
                    busy = reservationStations.getBusy(j);
                    if (busy == "No") {
                        x = j;
                        break;
                    }
                }
                break;
            case "MUL.D", "DIV.D":
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
