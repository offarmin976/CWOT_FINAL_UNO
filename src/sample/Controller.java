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

        /*


      ,-----.,--.    ,-----.  ,-----.,--. ,--.,--.   ,--. ,-----. ,------. ,--. ,--. ,---.       ,-----. ,------.   ,---.  ,--.  ,--. ,----.   ,------.
     '  .--./|  |   '  .-.  ''  .--./|  .'   /|  |   |  |'  .-.  '|  .--. '|  .'   /'   .-'     '  .-.  '|  .--. ' /  O  \ |  ,'.|  |'  .-./   |  .---'
     |  |    |  |   |  | |  ||  |    |  .   ' |  |.'.|  ||  | |  ||  '--'.'|  .   ' `.  `-.     |  | |  ||  '--'.'|  .-.  ||  |' '  ||  | .---.|  `--,
     '  '--'\|  '--.'  '-'  ''  '--'\|  |\   \|   ,'.   |'  '-'  '|  |\  \ |  |\   \.-'    |    '  '-'  '|  |\  \ |  | |  ||  | `   |'  '--'  ||  `---.
      `-----'`-----' `-----'  `-----'`--' '--''--'   '--' `-----' `--' '--'`--' '--'`-----'      `-----' `--' '--'`--' `--'`--'  `--' `------' `------'

    EIN PROJEKT VON ARMIN BAJRICA, NORBERT HEINRICH & STEFAN FUCHS


     */

        @FXML
        private Button button9; //Test Purposes
        @FXML
        private Button button8; //Test Purposes
        @FXML
        private TextField field1; //Field Text for Hours, Minutes and Seconds
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
        private Button button7; //Start the Timer BUtton
        @FXML
        private ComboBox<String> combo_sound;
        @FXML
        private String comboselect_sound; //ComboSelect - Combobox for Sound Section

        private ObservableList<String> dbTypeSound = FXCollections.observableArrayList("cow_sms", "ducks", "nice_alarm_sound"); // Possible Sound Selection based in dbTypeSound List of Strings


        // LOKAL
        private String Seconds, Minutes, Hours, Ultimo, NameOfTimer; //For calculation purposes
        private Integer i_Seconds, i_Minutes, i_Hours, i_Ultimo;


        @FXML
        void setName(ActionEvent event) {
        //Set Name -> SetLabel Methode
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
            if ((Testing1 == "") || (Testing2 == "") || (Testing3 == "") || (Testing4 == "")) { //CheckValue Methode for Testing and Checking Values
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Someone or Something is missing - I dont want to be alone!", ButtonType.OK, ButtonType.CANCEL);
                alert.showAndWait(); //Alert Object to inform User about a Error or Exception with only one Button: OK Button - show it and wait for Response

                if (alert.getResult() == ButtonType.CANCEL) {
                    System.exit(0); //Button Cancel - Exit Programm
                }
                if (alert.getResult() == ButtonType.OK) {
                    //DO Nothing - let User check input
                }
            }

                if(((Integer.parseInt(Testing1) > 59) ||  (Integer.parseInt(Testing1) < 0)  || ((Integer.parseInt(Testing2) > 59) ||  (Integer.parseInt(Testing2) < 0))))
                {
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Theres something off - you must check!", ButtonType.OK, ButtonType.CANCEL);
                    alert2.showAndWait(); //Alert Object to inform User about a Error or Exception with only one Button: OK Button - show it and wait for Response

                    if (alert2.getResult() == ButtonType.CANCEL) {
                        System.exit(0); //Button Cancel - Exit Programm
                    }
                    if (alert2.getResult() == ButtonType.OK) {
                        //DO Nothing - let User check input
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
            if(CheckValue(field4.getText(),field3.getText(), field2.getText(), field1.getText()) == true) { //If Checking is TRUE (Meaning == Everything is Alright


                Seconds = field4.getText(); //Get the Value
                Minutes = field3.getText(); //Get the Value
                Hours = field2.getText(); //Get the Value

                NameOfTimer = field1.getText(); //Get the Value

                i_Seconds = Integer.parseInt(Seconds); //Converting
                i_Minutes = Integer.parseInt(Minutes); //Converting
                i_Hours = Integer.parseInt(Hours); //Converting

                comboselect_sound = combo_sound.getValue(); //Comboboxselection

                i_Ultimo = (i_Hours * 3600) + (i_Hours * 60) + i_Seconds; //Calculate full seconds to count down
                Ultimo = String.valueOf(i_Ultimo); //Calculate the Ultimo Seconds Value

                Node node = (Node) event.getSource(); //Create Node Object
                Stage stage = (Stage) node.getScene().getWindow(); //Prepare Node
                Scene scene = stage.getScene(); //Get the Scene

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EieruhrTimer.fxml")); //Create new FXMLLoader and Load second FXML
                Parent root = (Parent) fxmlLoader.load(); //Root Load of the new FCML
                Controller_Timer CT = fxmlLoader.getController(); //Create "Controller"-Controller to get methodes of Controller_Timer

                CT.SetLabel(field1.getText()); //SetLabel of Controller_Timer
                CT.timer2(i_Ultimo, comboselect_sound); //Start the acutall Timer Methode with Parameters


                scene.setRoot(root); //Initializing
            }

    }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            combo_sound.setItems(dbTypeSound); //Needed in initialize methode - setting the combobox values

    }

}
