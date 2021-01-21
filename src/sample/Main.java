package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //CLOCKWORKS ORANGE - MAIN TIMER PROTOTYP FINAL UNO 1
        Parent root = FXMLLoader.load(getClass().getResource("EieruhrConfig.fxml")); //Setting the Parent-Node-FXML Loader
        primaryStage.setTitle("DIE EIERUHR"); //Setting Title
        primaryStage.setScene(new Scene(root, 240, 400)); //Root Windows Size
        primaryStage.setResizable(false); //Not allowing to resize
        primaryStage.show(); //show and "open" the stage!
    }

    public static void main(String[] args) {
        launch(args);
    }
}
