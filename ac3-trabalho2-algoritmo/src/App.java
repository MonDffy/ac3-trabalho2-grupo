/* 
    -   git por que não lembro dos comandos: 
            1 - git add .
            2 - git commit -m ""
            3 - git push -u origin main
    -   Renomeação de buffer
    -   Bug na dependecia 
*/

import java.util.ArrayList;

public class App {
    static void verificaDependencia(ArrayList<String[]> instructions) {

        ArrayList<String> podeConterDependencia = new ArrayList<>();

        for (int i = 0; i < instructions.size(); i++) {
            if (!instructions.get(i)[1].contains((CharSequence) podeConterDependencia)) {
                podeConterDependencia.add(instructions.get(0)[0]);
            }
            for (int j = 0; j < podeConterDependencia.size(); j++) {
                if (podeConterDependencia.get(j).equals(instructions.get(i)[2])
                        || podeConterDependencia.get(j).equals(instructions.get(i)[3])) {
                    System.out.println("Dependencia verdadeira: " + podeConterDependencia.get(j));
                }
            }

        }

    }

    public static void main(String[] args) throws Exception {

        ArrayList<String> input = new ArrayList<>();
        String path = "input.txt";
        File.reader(path, input);

        InstructionStatus instructionStatus = new InstructionStatus();
        ReorderBuffer reorderBuffer = new ReorderBuffer();
        ReservationStations reservationStations = new ReservationStations();
        FPRegisterStatus registerStatus = new FPRegisterStatus();

        ArrayList<String[]> instructions = new ArrayList<>();
        instructions = File.split(input);

        instructionStatus.setInstructions(input);
        // try {
        Swing.tela(instructions, instructionStatus, reorderBuffer, reservationStations, registerStatus);
        // } catch (Exception e) {
        // Swing.error();
        // }

        // reorderBuffer.setInstructions(input);

        // reorderBuffer.setDestination(instructions);

        // testar o split
        // for (int i = 0; i < instructions.size(); i++) {
        // for (int j = 0; j < instructions.get(i).length; j++) {
        // System.out.println("linha " + i + " coluna " + j + " " +
        // instructions.get(i)[j] + " - ");
        // }
        // }

    }
}
