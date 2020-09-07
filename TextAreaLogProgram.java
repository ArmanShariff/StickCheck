import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

import javax.annotation.processing.FilerException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

public class TextAreaLogProgram extends JFrame {
    /**
     * The text area which is used for displaying logging information.
     */
    private JTextArea textArea;

    private JButton buttonStart = new JButton("Start");
    private JButton buttonClear = new JButton("Clear");

    private PrintStream standardOut;

    Font normalFont = font();

    public TextAreaLogProgram(Team team1, Team team2) {
        textArea = new JTextArea(50, 10);
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));

        textArea.setFont(normalFont);

        // keeps reference of standard output stream
        standardOut = System.out;

        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);

        // creates the GUI
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        add(buttonStart, constraints);

        constraints.gridx = 1;
        add(buttonClear, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        add(new JScrollPane(textArea), constraints);

        // adds event handler for button Start
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                runGame(team1, team2);
            }
        });

        // adds event handler for button Clear
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // clears the text area
                try {
                    textArea.getDocument().remove(0, textArea.getDocument().getLength());
                    standardOut.println("Text area cleared");
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setSize(600, 500);
        setLocationRelativeTo(null); // centers on screen
    }

    public Font font() {
        try {
            // Returned font is of pt size 1
            normalFont = Font.createFont(Font.TRUETYPE_FONT, new File("FugazOne-Regular.ttf"));

            // Derive and return a 12 pt version:
            // Need to use float otherwise
            // it would be interpreted as style

            return normalFont.deriveFont(12f);

        } catch (IOException|FontFormatException e) {
            // Handle exception
       }
       return normalFont;
    }

    /**
     * Prints log statements for testing in a thread
     */
    private void runGame(Team team1, Team team2) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Simulation game1 = new Simulation(team1, team2, false);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
    }

}