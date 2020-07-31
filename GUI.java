import java.nio.file.Paths;

import javax.swing.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
class GUI{
    public static void main(String args[]) throws Exception{
       music(); 
       JFrame frame = new JFrame("My First GUI");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(1650,650);
       JButton button = new JButton("STICK CHECK");
       frame.getContentPane().add(button); // Adds Button to content pane of frame
       frame.setVisible(true);
       }

       static MediaPlayer mediaPlayer;

       public static void music() {
           String s = "action.mp3";
           Media h = new Media(Paths.get(s).toUri().toString());
           mediaPlayer = new MediaPlayer(h);
           mediaPlayer.play();
    }   
}