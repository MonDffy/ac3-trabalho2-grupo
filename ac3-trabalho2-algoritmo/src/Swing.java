import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Swing {

    static void tela(ArrayList<String[]> instructions, InstructionStatus instructionStatus, ReorderBuffer reorderBuffer,
            ReservationStations reservationStations, FPRegisterStatus registerStatus) {

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

        label.setText(Tables.getTables(instructionStatus, reorderBuffer, reservationStations, registerStatus));

        panel.add(label);
        panel.add(nextButton);
        // panel.add(previousButton);

        // previousButton.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent event) {
        // }
        // });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Simulation.next(instructionStatus, reorderBuffer, reservationStations, registerStatus);
                label.setText(Tables.getTables(instructionStatus, reorderBuffer, reservationStations, registerStatus));
            }
        });

        frame.getContentPane().add(panel);

    }

    static void error() {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
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

    static void fim() {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

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
