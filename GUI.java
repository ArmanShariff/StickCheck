import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.text.*;
import javafx.scene.paint.*;

public class GUI extends Application {
    Stage window;
    Scene titleScreen, teamSelect, teamStats, chooseAction, calendar, playerStats, editLines;

    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("StickCheck");
    }
}
