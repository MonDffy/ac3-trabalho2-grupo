/* 
    -   git por que não lembro dos comandos: 
            1 - git add .
            2 - git commit -m ""
            3 - git push -u origin main
    -   Fazer a simulação;
    -   Criar teste do arquivo de entrada;
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/* Tabelas  */

class InstructionStatus {

    String title = "Instruction Status";
    String[] column = { "Instruction", "Issue", "Execute", "Write Result" };
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
        return this.title;
    }

    String getColumn(int i) {
        return this.column[i];
    }

    String getInstruction(int index) {
        return this.instruction.get(index);
    }

    int getInstructionSize() {
        return this.instruction.size();
    }

    void setIssue(int index) {
        this.issue.set(index, "x");
    }

    String getIssue(int index) {
        return this.issue.get(index);
    }

    void setExecute(int index) {
        this.execute.set(index, "x");
    }

    String getExecute(int index) {
        return this.execute.get(index);
    }

    void setWriteResult(int index) {
        this.writeResult.set(index, "x");
    }

    String getWriteResult(int index) {
        return this.writeResult.get(index);
    }

}

class ReservationStations {

    String title = "Reservation Stations";
    String[] column = { "Name", "Busy", "Op", "Vj", "Vk", "Qj", "Qk", "A" };
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

    int getColumnSize() {
        return column.length;
    }

    String getName(int index) {
        return name[index];
    }

    void setBusy(int index, String ent) {
        busy[index] = ent;
    }

    String getBusy(int index) {
        return busy[index];
    }

    void setOp(int index, String ent) {
        op[index] = ent;
    }

    String getOp(int index) {
        return op[index];
    }

    void setVj(int index, String ent) {
        vj[index] = ent;
    }

    String getVj(int index) {
        return vj[index];
    }

    void setVk(int index, String ent) {
        vk[index] = ent;
    }

    String getVk(int index) {
        return vk[index];
    }

    void setQj(int index, String ent) {
        qj[index] = ent;
    }

    String getQj(int index) {
        return qj[index];
    }

    void setQk(int index, String ent) {
        qj[index] = ent;
    }

    String getQk(int index) {
        return qk[index];
    }

    void setA(int index, String ent) {
        a[index] = ent;
    }

    String getA(int index) {
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

    void setLine2(int index, String ent) {
        line2.set(index + 1, ent);
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
        for (int i = 0; i < 30; i++) {
            line1.add("F" + i);
            line2.add("");
        }

    }

}

/* Cria tabelas em html */

class Tables {

    static String getColumn1(InstructionStatus instructionStatus) {
        String text = "<tr>";
        ;
        for (int i = 0; i < 4; i++) {
            text += "<th>" +
                    instructionStatus.getColumn(i) +
                    "</th>";
        }
        text += "</tr>";
        return text;
    }

    static String getRow1(InstructionStatus instructionStatus) {
        String text = "";
        for (int i = 0; i < instructionStatus.getInstructionSize(); i++) {
            text += "<tr>" +
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
        String text = "<tr>";
        ;
        for (int i = 0; i < reservationStations.getColumnSize(); i++) {
            text += "<th>" +
                    reservationStations.getColumn(i) +
                    "</th>";
        }
        text += "</tr>";
        return text;
    }

    static String getRow2(ReservationStations reservationStations) {
        String text = "";
        for (int i = 0; i < 7; i++) {
            text += "<tr>" +
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
        String text = "<tr>";
        for (int i = 0; i < registerStatus.getLineSize(); i++) {
            text += "<th>" +
                    registerStatus.getLine1(i) +
                    "</th>";
        }
        text += "</tr>";
        return text;
    }

    static String getRow3(RegisterStatus registerStatus) {
        String text = "<tr>";
        for (int i = 0; i < registerStatus.getLineSize(); i++) {
            text += "<td>" +
                    registerStatus.getLine2(i) +
                    "</td>";
        }
        text += "</tr>";
        return text;
    }

    static String getTables(InstructionStatus instructionStatus, ReservationStations reservationStations,
            RegisterStatus registerStatus) {
        String column1 = getColumn1(instructionStatus);
        String row1 = getRow1(instructionStatus);
        String column2 = getColumn2(reservationStations);
        String row2 = getRow2(reservationStations);
        String column3 = getColumn3(registerStatus);
        String row3 = getRow3(registerStatus);
        String text = "<html>" +
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

class File {

    static void reader(String path, ArrayList<String> input) throws IOException {

        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String line = "";
        while (true) {
            line = buffRead.readLine();
            if (line != null) {
                input.add(line);
            } else {
                break;
            }
        }
        buffRead.close();
    }

    static void testFile(ArrayList<String> input) {

        // Boolean valid = true;
        // if (valid = true) {

        // } else {

        // }

    }

}

/* Interface Gráfica */

class Swing {

    static void tela(InstructionStatus instructionStatus, ReservationStations reservationStations,
            RegisterStatus registerStatus) {

        JFrame frame = new JFrame("Simulador");
        // frame.setLocationRelativeTo(null);
        JLabel label = new JLabel();
        JButton nextButton = new JButton("Next");
        // JButton previousButton = new JButton("Previous");
        JPanel panel = new JPanel();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        label.setText(Tables.getTables(instructionStatus, reservationStations, registerStatus));

        panel.add(label);
        panel.add(nextButton);
        // panel.add(previousButton);

        // previousButton.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent event) {
        // }
        // });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Simulation.next(instructionStatus, reservationStations, registerStatus);
                label.setText(Tables.getTables(instructionStatus, reservationStations, registerStatus));
            }
        });

        frame.getContentPane().add(panel);

    }

    static void error(int screenWidth, int screenHeight) {

        JFrame frame = new JFrame("Error");
        frame.setVisible(true);
        JLabel label = new JLabel();
        JPanel panel = new JPanel();
        JButton button = new JButton("Finalizar");

        frame.setLocationRelativeTo(null);
        frame.setSize(screenWidth / 10, screenHeight / 8);
        label.setText(
                "<html>" +
                        "<br>" +
                        "A entrada de dados não é válida." +
                        "<br> <br>" +
                        "</html>");

        panel.add(label);
        panel.add(button);
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

    }

    static void fim(int screenWidth, int screenHeight) {

        JFrame frame = new JFrame("Fim");
        frame.setVisible(true);
        JLabel label = new JLabel();
        JPanel panel = new JPanel();
        JButton button = new JButton("Finalizar");

        frame.setLocationRelativeTo(null);
        frame.setSize(screenWidth / 15, screenHeight / 8);
        label.setText(
                "<html>" +
                        "<br>" +
                        "Fim do programa." +
                        "<br> <br>" +
                        "</html>");

        panel.add(label);
        panel.add(button);
        frame.getContentPane().add(panel);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

    }

}

/* Simulação */

class Simulation {

    static int a = 0;

    // static void previous(JLabel label, InstructionStatus instructionStatus,
    // ReservationStations reservationStations,
    // RegisterStatus registerStatus) {

    // }

    static void next(InstructionStatus instructionStatus, ReservationStations reservationStations,
            RegisterStatus registerStatus) {

        instructionStatus.setExecute(a);
        reservationStations.setA(a, "teste1");
        registerStatus.setLine2(a, "teste2");
        a++;

    }

}

/* Principal */

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
        
        Swing.tela(instructionStatus, reservationStations, registerStatus);

    }
}
