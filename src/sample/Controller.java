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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
        @FXML
        private String comboselect_sound;

        private ObservableList<String> dbTypeSound = FXCollections.observableArrayList("cow_sms", "ducks", "nice_alarm_sound"); // To extend...


        // LOKAL
        private String Seconds, Minutes, Hours, Ultimo, NameOfTimer;
        private Integer i_Seconds, i_Minutes, i_Hours, i_Ultimo;

        Stage secondaryStage;


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

        public boolean CheckValue(String Testing1, String Testing2, String Testing3, String Testing4)
        {
            boolean again = false;
            if ((Testing1 == "") || (Testing2 == "") || (Testing3 == "") || (Testing4 == "")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Someone or Something is missing - I dont want to be alone!", ButtonType.OK, ButtonType.CANCEL);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.CANCEL) {
                    System.exit(0);
                }
                if (alert.getResult() == ButtonType.OK) {

                }
            }

                if(((Integer.parseInt(Testing1) > 59) ||  (Integer.parseInt(Testing1) < 0)  || ((Integer.parseInt(Testing2) > 59) ||  (Integer.parseInt(Testing2) < 0))))
                {
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Theres something off - you must check!", ButtonType.OK, ButtonType.CANCEL);
                    alert2.showAndWait();

                    if (alert2.getResult() == ButtonType.CANCEL) {
                        System.exit(0);
                    }
                    if (alert2.getResult() == ButtonType.OK) {

                    }

                }

            else
            {
                again = true;
            }

            return again;
        }
    @FXML
        void startTime(ActionEvent event) throws Exception{
            if(CheckValue(field4.getText(),field3.getText(), field2.getText(), field1.getText()) == true) {


                Seconds = field4.getText();
                Minutes = field3.getText();
                Hours = field2.getText();

                NameOfTimer = field1.getText();

                i_Seconds = Integer.parseInt(Seconds);
                i_Minutes = Integer.parseInt(Minutes);
                i_Hours = Integer.parseInt(Hours);

                comboselect_sound = combo_sound.getValue();

                i_Ultimo = (i_Hours * 3600) + (i_Hours * 60) + i_Seconds;
                Ultimo = String.valueOf(i_Ultimo);

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = stage.getScene();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EieruhrTimer.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Controller_Timer CT = fxmlLoader.getController();


                CT.SetLabel(NameOfTimer);

                CT.timer2(i_Ultimo, comboselect_sound);


                scene.setRoot(root);
            }

    }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            combo_sound.setItems(dbTypeSound);

    }

}
