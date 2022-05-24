import java.util.ArrayList;

public class Simulation {

    // static void previous(JLabel label, InstructionStatus instructionStatus,
    // ReservationStations reservationStations,
    // RegisterStatus registerStatus) {

    // }

    static void next(ArrayList<String[]> instructions, InstructionStatus instructionStatus,
            ReservationStations reservationStations,
            RegisterStatus registerStatus) {

        // teste de conflito e renomeação (fazer por ultimo)

        // instructions já está splitada

        int rt = testReservationStation(instructions, reservationStations);
        // definar as possíveis instruções
        if (rt == -1) {
            System.out.println("colocar em espera");
            // colocar em espera
        } else {
            System.out.println("inserir na estação de reserva");
            // inserir na estação de reserva
        }

    }

    static int testReservationStation(ArrayList<String[]> instructions, ReservationStations reservationStations) {
        int x = -1;
        String busy = "no";
        for (int i = 0; i < instructions.size(); i++) {
            String str = instructions.get(0)[i];
            switch (str) {
                case "L.D":
                    busy = reservationStations.getBusy(0);
                    if (busy == "no") {
                        x = 0;
                        break;
                    }
                    busy = reservationStations.getBusy(1);
                    if (busy == "no") {
                        x = 1;
                        break;
                    }
                    break;
                case "SUB.D":
                case "ADD.D":
                    busy = reservationStations.getBusy(2);
                    if (busy == "no") {
                        x = 2;
                        break;
                    }
                    busy = reservationStations.getBusy(3);
                    if (busy == "no") {
                        x = 3;
                        break;
                    }
                    busy = reservationStations.getBusy(4);
                    if (busy == "no") {
                        x = 4;
                        break;
                    }
                    break;
                case "DIV.D":
                case "MUL.D":
                    busy = reservationStations.getBusy(5);
                    if (busy == "no") {
                        x = 5;
                        break;
                    }
                    busy = reservationStations.getBusy(6);
                    if (busy == "no") {
                        x = 6;
                        break;
                    }
                    break;
            }
            break;
        }
        return x;
    }

}
