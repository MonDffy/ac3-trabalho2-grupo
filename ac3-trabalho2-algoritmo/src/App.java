/* 
    -   git por que não lembro dos comandos: 
            1 - git add .
            2 - git commit -m ""
            3 - git push -u origin main
    -   Fazer a simulação;
    -   Criar teste do arquivo de entrada;
*/

import java.util.ArrayList;


public class App {
    public static void main(String[] args) throws Exception {

        ArrayList<String> input = new ArrayList<String>();
        String path = "input.txt";
        File.reader(path, input);

        File.testFile(input);

        InstructionStatus instructionStatus = new InstructionStatus();
        ReservationStations reservationStations = new ReservationStations();
        RegisterStatus registerStatus = new RegisterStatus();

        instructionStatus.setInstructions(input);
        
        Swing.tela(input, instructionStatus, reservationStations, registerStatus);

    }
}
