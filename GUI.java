import java.awt.*;
import javax.swing.*;

class GUI{
    public static void main(String args[]){

        //creating frame
       JFrame frame = new JFrame("Stick Check");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(1650,650);
       frame.setLayout(new FlowLayout());
       
       //creating button
       JButton startButton = new JButton("Start Game");
       startButton.setPreferredSize(new Dimension(300, 75));
       startButton.setBounds(300, 300, 75, 175);
       frame.getContentPane().add(startButton); // Adds Button to content pane of frame
       frame.setVisible(true);
    }
}