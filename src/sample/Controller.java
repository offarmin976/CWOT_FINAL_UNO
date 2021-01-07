package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class Controller implements Initializable {

        // CONTROLLER - CLOCKWORKS ORANGE TIMER PROTO FINAL UNO 1

        @FXML
        private Button button9;
        @FXML
        private Button button8;
        @FXML
        private TextField field1;
        @FXML
        private TextField field2;
        @FXML
        private TextField field3;
        @FXML
        private TextField field4;
        @FXML
        private ChoiceBox<?> drop5;
        @FXML
        private ChoiceBox<?> drop6;
        @FXML
        private Button button7;
        @FXML
        private ComboBox<String> combo_sound;
        private String comboselect_color;

        @FXML
        private ComboBox<String> combo_color;
        private String comboselect_sound;

        private ObservableList<String> dbTypeSound = FXCollections.observableArrayList("goat_sound", "elephant", "be_chillin"); // To extend...

        private ObservableList<String> dbTypeColor = FXCollections.observableArrayList("Black", "Red", "Blue", "Orange", "Yellow"); // To extend...

        // LOKAL
        private String Seconds, Minutes, Hours, Ultimo;
        private Integer i_Seconds, i_Minutes, i_Hours, i_Ultimo;

        Stage secondaryStage;

        void closeWindow(ActionEvent event) {
            //To be deleted
        }

        @FXML
        void minimizeWindow(ActionEvent event) {
        //To be deleted
        }

        @FXML
        void setName(ActionEvent event) {
        //To Do
        }

        @FXML
        private Text text1;

        @FXML
        private TextField field_time;

        @FXML
        private Button button_stop;

        @FXML
        private Button button_resume;

        @FXML
        void resumeTime(ActionEvent event) {
            //To Do
        }

        @FXML
        void stopTime(ActionEvent event) {
            //To Do
        }
    @FXML
        void startTime(ActionEvent event) throws Exception{

        Seconds = field4.getText();
        Minutes = field3.getText();
        Hours = field2.getText();

        i_Seconds = Integer.parseInt(Seconds);
        i_Minutes = Integer.parseInt(Minutes);
        i_Hours = Integer.parseInt(Hours);

        comboselect_color = combo_color.getEditor().getText();
        comboselect_sound = combo_sound.getEditor().getText();

        i_Ultimo = (i_Hours * 3600) + (i_Hours * 60) + i_Seconds;
        Ultimo = String.valueOf(i_Ultimo);

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EieruhrTimer.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Controller_Timer CT = fxmlLoader.getController();


        CT.timer2(i_Ultimo, comboselect_color, comboselect_sound );

        scene.setRoot(root);

    }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            combo_color.setItems(dbTypeColor);
            combo_sound.setItems(dbTypeSound);

    }

}
