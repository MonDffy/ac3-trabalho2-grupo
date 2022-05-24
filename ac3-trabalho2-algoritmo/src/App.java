/* 
    victor esteve aqui
    -   git por que não lembro dos comandos: 
            1 - git add .
            2 - git commit -m ""
            3 - git push -u origin main
    -   Fazer a simulação;
    -   Criar teste do arquivo de entrada;
*/

import java.util.ArrayList;

public class App {

    static ArrayList<String[]> split(ArrayList<String> input) {

        ArrayList<String[]> instructions = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String[] aux = input.get(0).split("[ ,()]");
            instructions.add(aux);
        }
        return instructions;

    }

    public static void main(String[] args) throws Exception {

        ArrayList<String> input = new ArrayList<>();
        String path = "input.txt";
        File.reader(path, input);

        File.testFile(input);

        InstructionStatus instructionStatus = new InstructionStatus();
        ReservationStations reservationStations = new ReservationStations();
        RegisterStatus registerStatus = new RegisterStatus();

        instructionStatus.setInstructions(input);

        // Split no input
        ArrayList<String[]> instructions = new ArrayList<>();
        instructions = split(input);

        Swing.tela(instructions, instructionStatus, reservationStations, registerStatus);

    }
}
