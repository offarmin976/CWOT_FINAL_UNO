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
import javafx.scene.control.Button;
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

    // CONTROLLER TIMER- CLOCKWORKS ORANGE TIMER PROTO FINAL UNO 1

    private String G_CBSoundSelect = "";
    private Thread countdownThread;
    public Stage primaryStage_neu;
    public Stage primaryStage_alt;
    Timer timer = new Timer();
    private Timeline timeline;
    int Count = 0;
    private AnimationTimer atimer;
    private static final Integer STARTTIME = 15;
    boolean resume_possible = true;

    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    File F = new File("src/sample/" + G_CBSoundSelect + ".wav");

    public void setPrimaryStage(Stage primaryStage_neu) {
        this.primaryStage_alt = primaryStage_neu;

    }

    @FXML
    private AnchorPane apane;

    @FXML
    private Text text1;

    @FXML
    private Rectangle background_rectangle;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Label time_label;

    @FXML
    private Label label_name;

    @FXML
    void resumeTime(ActionEvent event) {
        if (resume_possible == true) {
            timeline.play();
            timer.start();
        }
    }
    @FXML
    void stopTime(ActionEvent event) {
        timeline.stop();
        timer.stop();
        play_audio(false);



    }

    public void SetLabel(String Text) {

        label_name.setText(Text);

    }


    private void play_audio(boolean dowhat) {


            File F = new File("src/sample/" + G_CBSoundSelect + ".wav");

            try {
                Clip c = AudioSystem.getClip();
                c.open(AudioSystem.getAudioInputStream(F));
                if (dowhat == true) {
                    c.loop(5);
                }
                if (dowhat == false) {
                    c.stop();
                }

            } catch (Exception e) {


            }


        }

    public void timer2( final Integer TimetoSec, final String CB_Sound)
    {
        G_CBSoundSelect = CB_Sound;
        Count = TimetoSec;
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        atimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                time_label.setText(String.valueOf(Count));
                Count = Count - 1; //Test for PROTO 0
                //To Do everytime a new Keyframe is reached

            }
        };
        Duration duration = Duration.seconds(0.5); //Due to unknown Reasons the Duration Seconds has to be set at 0.5 - probably due to JAVAFX's not coherent time management - CRITICAL for PROTO 2

        //To Do everytime EventHanlder finishes with Action
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                time_label.setFont(new Font(50) ); //Setting Font for Timer Window
                time_label.setText(String.valueOf(Count));

                Count = Count - 1; //Counting down

                if(Count < 0)
                {
                    play_audio(true);
                    resume_possible = false;
                    timeline.stop();
                    timer.stop();
                    timeline.pause();

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
