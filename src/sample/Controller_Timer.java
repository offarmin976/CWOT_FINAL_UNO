package sample;

import com.sun.javafx.geom.Rectangle;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.management.timer.Timer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.TextStyle;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TimerTask;

public class Controller_Timer implements Initializable {

    /*


      ,-----.,--.    ,-----.  ,-----.,--. ,--.,--.   ,--. ,-----. ,------. ,--. ,--. ,---.       ,-----. ,------.   ,---.  ,--.  ,--. ,----.   ,------.
     '  .--./|  |   '  .-.  ''  .--./|  .'   /|  |   |  |'  .-.  '|  .--. '|  .'   /'   .-'     '  .-.  '|  .--. ' /  O  \ |  ,'.|  |'  .-./   |  .---'
     |  |    |  |   |  | |  ||  |    |  .   ' |  |.'.|  ||  | |  ||  '--'.'|  .   ' `.  `-.     |  | |  ||  '--'.'|  .-.  ||  |' '  ||  | .---.|  `--,
     '  '--'\|  '--.'  '-'  ''  '--'\|  |\   \|   ,'.   |'  '-'  '|  |\  \ |  |\   \.-'    |    '  '-'  '|  |\  \ |  | |  ||  | `   |'  '--'  ||  `---.
      `-----'`-----' `-----'  `-----'`--' '--''--'   '--' `-----' `--' '--'`--' '--'`-----'      `-----' `--' '--'`--' `--'`--'  `--' `------' `------'

    EIN PROJEKT VON ARMIN BAJRICA, NORBERT HEINRICH & STEFAN FUCHS


     */

    private String G_CBSoundSelect = "";
    public Stage primaryStage_alt;
    Timer timer = new Timer();
    private Timeline timeline;
    int Count = 0;
    private AnimationTimer atimer; //Create AnimationTimer Object
    private static final Integer STARTTIME = 15;
    boolean resume_possible = true;
    private double DurationsD = 0.5; //Create Duration for each keyframe

    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);


    @FXML
    private AnchorPane apane;

    @FXML
    private Text text1; //Textfield for Name on Timer Window

    @FXML
    private Button button3; //Stop Button on Timer Window

    @FXML
    private Button button4; //Resume Button on Timer Window

    @FXML
    private Label time_label; //Label for Time in FXML on Timer Window

    @FXML
    private Label name_label; //Label for Name in FXML on Timer Window

    @FXML
    void resumeTime(ActionEvent event) {
        if (resume_possible == true) {
            timeline.play(); //Getting the Time & TimeLine Object to resume the CountDown
            timer.start(); //KeyFrame restart
        }
    }
    @FXML
    void stopTime(ActionEvent event) {
        timeline.stop(); //Getting the Time & TimeLine Object to stop the CountDown
        timer.stop(); //Keyframe stopping
        //play_audio(false);



    }
    public void SetLabel(String Text) {

        name_label.setText(Text); //Set Label for Timer Window

    }


    private void play_audio(boolean dowhat) {


            File F = new File("src/sample/" + G_CBSoundSelect + ".wav"); //File Object to search in SRC / SAMPLE Folder for the .WAV Data

            try {
                Clip c = AudioSystem.getClip(); // Create a new Audio-Clip and open the selected File with the AudioInputStream
                c.open(AudioSystem.getAudioInputStream(F));
                if (dowhat == true) { //IF created for eventual Possibility to Break the playing when finished with Timer
                    c.loop(5); //LOOP Function -> Will loop Audio-File 5 Times and then stop
                }
                if (dowhat == false) { //IF created for eventual Possibility to Break the playing when finished with Timer
                    c.stop(); //Stop complete playing of the file
                }

            } catch (Exception e) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Something went wrong trying to play the requested Sound - Im really sorry!!!", ButtonType.OK);
                alert.showAndWait(); //Alert Object to inform User about a Error or Exception with only one Button: OK Button - show it and wait for Response

            }

        }

    public void timer2( final Integer TimetoSec, final String CB_Sound)
    {
        //Final Integer - no correction possible anymore
        G_CBSoundSelect = CB_Sound;
        Count = TimetoSec;
        timeline = new Timeline(); //Create a new Timeline Object for Time-Sequences
        timeline.setCycleCount(Timeline.INDEFINITE); //Repeat Timeline Object infinitely
        timeline.setAutoReverse(true); //Defines whether this Animation reverses direction on alternating cycles.


        Duration duration = Duration.seconds(DurationsD); //Duration between each Keyframe

        //To Do everytime EventHanlder finishes with Action
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                time_label.setFont(new Font(50) ); //Setting Font for Timer Window
                time_label.setText(String.valueOf(Count)); //Set the Value of Countind Down

                Count = Count - 1; //Counting down

                if(Count < 0)
                {
                    //When Countdown is finished exectue next steps;
                    play_audio(true);//Acitvate Sound
                    resume_possible = false;//Deactivate the Resume Button
                    timeline.stop();//Stop the TimeLine
                    timer.stop(); //Stop the Time
                    timeline.pause();//Safety first ;)

                }

            }
        };
        KeyFrame keyFrame = new KeyFrame(duration, onFinished);
        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);
        timeline.play(); //Starting the Timeline Object
        timer.start();  //Making it start


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
