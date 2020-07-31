import java.nio.file.Paths;

import java.awt.*;
import javax.swing.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
class GUI{
    public static void main(String args[]){
       //creating frame
       JFrame frame = new JFrame("Stick Check");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(1650,650);
       //frame.setLayout(new FlowLayout());

       //creating button
       JButton startButton = new JButton("Start Game");
       startButton.setPreferredSize(new Dimension(300, 75));
       startButton.setBounds(0, 0, 75, 175);

       //creating panel
       JPanel startPanel = new JPanel(new BorderLayout());
       startPanel.add(startButton, BorderLayout.CENTER);
       frame.add(startPanel);
       frame.setVisible(true);

       //music();
       }

    MediaPlayer mediaPlayer;
    public void music() {
        String s = "action.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
    }   
}