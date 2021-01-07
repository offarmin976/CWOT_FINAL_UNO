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
        Parent root = FXMLLoader.load(getClass().getResource("EieruhrConfig.fxml"));
        primaryStage.setTitle("Die Eieruhr von Clockworks Orange");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public class Clock extends Controller_Timer {


    }



    public static void main(String[] args) {
        launch(args);
    }
}
