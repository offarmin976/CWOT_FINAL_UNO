package sample;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.management.timer.Timer;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TimerTask;

public class Controller_Timer implements Initializable {

    // CONTROLLER TIMER- CLOCKWORKS ORANGE TIMER PROTO FINAL UNO 1

    private Thread countdownThread;
    private Stage primaryStage;
    Timer timer = new Timer();
    private Timeline timeline;
    int Count = 0;
    private AnimationTimer atimer;
    private static final Integer STARTTIME = 15;

    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    @FXML
    private AnchorPane apane;

    @FXML
    private Text text1;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Label time_label;

    @FXML
    void resumeTime(ActionEvent event) {
        //Not working - fix it with Release 2
        timer.start();

    }
    @FXML
    void stopTime(ActionEvent event) {
        //Not working - fix it with Release 2
    }

    public void SetLabel(String Text) {
        time_label.setText(Text);
        //Not working - fix it with Release 2

    }

    public void SetBackground(String PColor) {

        switch (PColor) {

            case "Black":
                apane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                break;

                //Not working - fix it with Release 2
        }

    }

    private void Clock(int TimeSec)
    {

        Timeline animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timelabel(TimeSec)));
        animation.setCycleCount(TimeSec);
        animation.play();

        //Setting a new Timeline for the PROTO 0

    }

    public void CountDown(final Integer TimeinSec, int INTTimesOfRepat) {

        Clock(INTTimesOfRepat);
        Count = INTTimesOfRepat;
    }
    public void timelabel(double TL_Sec) {

        // PROTOTYP 0 - IGNORE IT
         int tick = (int) TL_Sec;
         String S = "";

        if (tick > 0) {
                tick--;

        }
        if (tick == 0) {


        }

        S = tick + "";
        time_label.setText(S);

    }

    public void timer2( final Integer TimetoSec, final String CB_Color, final String CB_Sound)
    {
        SetBackground(CB_Color);
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
