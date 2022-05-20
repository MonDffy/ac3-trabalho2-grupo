/* 
    - linha a mais na primeira tabela por conta
    da variável "line" no Class.reader; 
    -
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/* Tabelas  */

class InstructionStatus {

    String title = "Instruction Status tefr";
    String[] column = { "Instruction", "Issue", "Execute", "Write Result"};
    ArrayList<String> instruction = new ArrayList<String>();
    ArrayList<String> issue = new ArrayList<String>();
    ArrayList<String> execute = new ArrayList<String>();
    ArrayList<String> writeResult = new ArrayList<String>();

    void setInstructions(ArrayList<String> input) {
        for (int i = 0; i < input.size(); i++) {
            instruction.add(i, input.get(i));
        }
        for (int i = 0; i < input.size(); i++) {
            issue.add(i, " ");
            execute.add(i, " ");
            writeResult.add(i, " ");
        }
    }

    String getTitle() {
        return title;
    }

    String getColumn(int i) {
        return column[i];
    }

    String getInstruction(int index) {
        return instruction.get(index);
    }
    int getInstructionSize() {
        return instruction.size();
    }

    String getIssue(int index) {
        return issue.get(index);
    }

    String getExecute(int index) {
        return execute.get(index);
    }

    String getWriteResult(int index) {
        return writeResult.get(index);
    }

}

class ReservationStations {

String title = "Reservation Stations"; 
String[] column = {"Name", "Busy", "Op", "Vj", "Vk", "Qj", "Qk", "A"};
String name[] = new String[7];
String busy[] = new String[7];
String op[] = new String[7];
String vj[] = new String[7];
String vk[] = new String[7];
String qj[] = new String[7];
String qk[] = new String[7];
String a[] = new String[7];

String getTitle() {
    return title;
}

String getColumn(int index) {
    return column[index];
}

int getColumnSize(){
    return column.length;
}

public String getName(int index) {
    return name[index];
}

public String getBusy(int index) {
    return busy[index];
}

public String getOp(int index) {
    return op[index];
}

public String getVj(int index) {
    return vj[index];
}

public String getVk(int index) {
    return vk[index];
}

public String getQj(int index) {
    return qj[index];
}

public String getQk(int index) {
    return qk[index];
}

public String getA(int index) {
    return a[index];
}

ReservationStations() {

    name[0] = "Load1";
    name[1] = "Load2";
    name[2] = "Add1";
    name[3] = "Add2";
    name[4] = "Add3";
    name[5] = "Mult1";
    name[6] = "Mult2";
    for (int i = 0; i < 7; i++) {
        busy[i] = "No";
        op[i] = " ";
        vj[i] = " ";
        vk[i] = " ";
        qj[i] = " ";
        qk[i] = " ";
        a[i] = " ";
    }

}

}

class RegisterStatus {

    String title = "Register Status";
    ArrayList<String> line1 = new ArrayList<String>();
    ArrayList<String> line2 = new ArrayList<String>();

    String getTitle() {
        return title;
    }

    String getLine1(int index) {
        return line1.get(index);
    }

    String getLine2(int index) {
        return line2.get(index);
    }

    int getLineSize() {
        return line1.size();
    }

    RegisterStatus() {
        line1.add("Field");
        line2.add("Qi");
        for(int i = 0; i < 30; i++){
            line1.add("F"+i);
            line2.add("");
        }

    }

}

/* Cria tabelas em html */

class Tables {

    static String getColumn1(InstructionStatus instructionStatus) {
        String text = 
            "<tr>";
            ;
        for(int i = 0; i < 4; i++) {
            text += 
                "<th>" +
                    instructionStatus.getColumn(i) +
                "</th>";   
        }
        text += 
            "</tr>";
        return text;
    }

    static String getRow1(InstructionStatus instructionStatus) {
        String text = "";
        for(int i = 0; i < instructionStatus.getInstructionSize(); i++) {
            text += 
                "<tr>" + 
                    "<td>" +
                        instructionStatus.getInstruction(i) +
                    "</td>" +
                    "<td>" +
                        instructionStatus.getIssue(i) +
                    "</td>" +
                    "<td>" +
                        instructionStatus.getExecute(i) +
                    "</td>" +
                    "<td>" +
                        instructionStatus.getWriteResult(i) +
                    "</td>" +
                "</tr>";
        }
        return text;
    }

    static String getColumn2(ReservationStations reservationStations) {
        String text = 
            "<tr>";
            ;
        for(int i = 0; i < reservationStations.getColumnSize(); i++) {
            text += 
                "<th>" +
                    reservationStations.getColumn(i) +
                "</th>";   
        }
        text += 
            "</tr>";
        return text;
    }

    static String getRow2(ReservationStations reservationStations) {
        String text = "";
        for(int i = 0; i < 7; i++) {
            text += 
                "<tr>" + 
                    "<td>" +
                        reservationStations.getName(i) +
                    "</td>" +
                    "<td>" +
                        reservationStations.getBusy(i) +
                    "</td>" +
                    "<td>" +
                        reservationStations.getOp(i) +
                    "</td>" +
                    "<td>" +
                        reservationStations.getVj(i) +
                    "</td>" +
                    "<td>" +
                        reservationStations.getVk(i) +
                    "</td>" +
                    "<td>" +
                        reservationStations.getQj(i) +
                    "</td>" +
                    "<td>" +
                        reservationStations.getQk(i) +
                    "</td>" +
                    "<td>" +
                        reservationStations.getA(i) +
                    "</td>" +
                "</tr>";
        }
        return text;
    }

    static String getColumn3(RegisterStatus registerStatus) {
        String text = 
            "<tr>";
        for(int i = 0; i < registerStatus.getLineSize(); i++) {
            text += 
                "<th>" +
                    registerStatus.getLine1(i) +
                "</th>";   
        }
        text += 
            "</tr>";
        return text;
    }

    static String getRow3(RegisterStatus registerStatus) {
        String text = 
            "<tr>";
        for(int i = 0; i < registerStatus.getLineSize(); i++) {
            text += 
                "<td>" +
                    registerStatus.getLine2(i) +
                "</td>";
        }
        text += 
            "</tr>";
        return text;
    }

    static String getTables(InstructionStatus instructionStatus, ReservationStations reservationStations, RegisterStatus registerStatus) {
        String column1 = getColumn1(instructionStatus);
        String row1 = getRow1(instructionStatus);
        String column2 = getColumn2(reservationStations);
        String row2 = getRow2(reservationStations);
        String column3 = getColumn3(registerStatus);
        String row3 = getRow3(registerStatus);
        String text = 
            "<html>" +
                "<table border='1'>" +
                    "<tr>" +
                        "<th colsplan='4'>" +
                            instructionStatus.getTitle() +
                        "</th>" +
                    "</tr>" +
                    column1 +
                    row1 +
                "</table>" +
                "<br>" + 
                "<table border='1'>" +
                    "<tr>" +
                        "<th colsplan='4'>" +
                            reservationStations.getTitle() +
                        "</th>" +
                    "</tr>" +
                    column2 +
                    row2 +
                "</table>" +
                "<br>" + 
                "<table border='1'>" +
                    "<tr>" +
                        "<th colsplan='4'>" +
                            registerStatus.getTitle() +
                        "</th>" +
                    "</tr>" +
                    column3 +
                    row3 +
                "</table>" +
            "</html>";
        return text;
    }
}

/* Leitura de Arquivo */

class File  {

    static void reader(String path, ArrayList<String> input) throws IOException {

        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String line = "";
        while (true) {
            if (line != null) {
                input.add(line);
            } else {
                break;
            }
            line = buffRead.readLine();
        }
        buffRead.close();
    }

}

/* Interface Gráfica */

class Swing {

    static void tela(InstructionStatus instructionStatus, ReservationStations reservationStations, RegisterStatus registerStatus) {

        JFrame frame = new JFrame();
        JLabel label = new JLabel();

        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        label.setText(Tables.getTables(instructionStatus, reservationStations, registerStatus));
        frame.getContentPane().add(label);

    }

}

/* Principal */

public class App {
    public static void main(String[] args) throws Exception {

        ArrayList<String> input = new ArrayList<String>();
        String path = "input.txt";
        File.reader(path, input);

        InstructionStatus instructionStatus = new InstructionStatus();
        ReservationStations reservationStations = new ReservationStations();
        RegisterStatus registerStatus = new RegisterStatus();

        instructionStatus.setInstructions(input);
        Swing.tela(instructionStatus, reservationStations, registerStatus);

    }
}
