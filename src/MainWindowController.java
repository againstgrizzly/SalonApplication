

import com.jfoenix.controls.*;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Exchanger;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import javax.sound.sampled.Clip;


public class MainWindowController implements Initializable {

    //Color codes
    String cyan = "#00bcd4";
    String lightCyan = "#80deea";
    String darkCyan = "#0097a7";
    String pink = "#e91e63";





    //Main Window Stuff
    @FXML private AnchorPane topBarAnchorPane;
    @FXML private JFXHamburger menuHamburger;
    @FXML private JFXButton schedulingTab;
    @FXML private JFXButton clientsTab;
    @FXML private JFXButton otherTab;
    @FXML private JFXButton homeTab;
    @FXML private JFXTextField searchTextField;
    @FXML private AnchorPane mainMenuTabPane;
    @FXML BorderPane basePane;




    @FXML
    ChoiceBox lightChoiceBox;

    HashMap<JFXButton, Boolean> selectedTracker = new HashMap<>();


    //Components Window Stuff
    AnchorPane pane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabColor();
        hamburgerAndSideMenuTransitionHandling();
        searchFieldHandling();




    }

    public void searchFieldHandling(){
        searchTextFieldBeautification();




        searchTextField.setOnInputMethodTextChanged(e ->{
            System.out.println("your mom");
        });

    }

    private void searchTextFieldBeautification() {

   searchTextField.setPromptText("SEARCH");
        searchTextField.setFont(Font.loadFont("res/Roboto-Light.ttf", 123));

    }


    public void tabTransitions(){

        homeTab.setOnMouseClicked(e -> {

        });

        schedulingTab.setOnMouseClicked(e -> {

        });

        clientsTab.setOnMouseClicked(e -> {

        });

        otherTab.setOnMouseClicked(e -> {

        });





    }

    public void hamburgerAndSideMenuTransitionHandling(){
        HamburgerSlideCloseTransition h = new HamburgerSlideCloseTransition(menuHamburger);
        h.setRate(-1);
        menuHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            h.setRate(h.getRate() * -1);
            h.play();

//            if (x == 0) {//Dissapear
//                TranslateTransition transition = new TranslateTransition();
//                transition.setDuration(Duration.seconds(.2));
//                transition.setNode(pane);
//                transition.setToX(-200);
//                transition.setInterpolator(Interpolator.EASE_BOTH);
//                transition.play();
//                x = 1;
//            } else {//appear
//                if (!canvasPane.getChildren().contains(pane)) {
//                    canvasPane.getChildren().add(pane);
//                    pane.setTranslateX(-200);
//                }
//                TranslateTransition transition = new TranslateTransition();
//                transition.setDuration(Duration.seconds(.2));
//                transition.setNode(pane);
//                transition.setToX(0);
//                transition.setInterpolator(Interpolator.EASE_BOTH);
//                transition.play();
//                x = 0;
//            }
        });
    }

    public void tabColor(){
        selectedTracker.put(homeTab,true);
        selectedTracker.put(schedulingTab, false);
        selectedTracker.put(clientsTab, false);
        selectedTracker.put(otherTab, false);

        homeTab.setStyle("-fx-background-color:" + pink);
        homeTab.setButtonType(JFXButton.ButtonType.RAISED);

        //Other tab handle
        otherTab.setOnMouseClicked(e -> {

            for(JFXButton button : selectedTracker.keySet()){
                button.setStyle("-fx-background-color:" + darkCyan);
                selectedTracker.replace(button, false);
                button.setButtonType(JFXButton.ButtonType.FLAT);
            }

            selectedTracker.replace(otherTab, true);
            otherTab.setStyle("-fx-background-color:" + pink);
            otherTab.setButtonType(JFXButton.ButtonType.RAISED);



        });

        //Scheduling tab handle
        schedulingTab.setOnMouseClicked(e -> {
            System.out.println("Scheduling Pressed");

            for(JFXButton button : selectedTracker.keySet()){

                button.setStyle("-fx-background-color:" + darkCyan);
                selectedTracker.replace(button, false);
                button.setButtonType(JFXButton.ButtonType.FLAT);

            }

            selectedTracker.replace(schedulingTab, true);
            schedulingTab.setStyle("-fx-background-color:" + pink);
            schedulingTab.setButtonType(JFXButton.ButtonType.RAISED);


        });

        //Client tab handle
        clientsTab.setOnMouseClicked(e -> {

            for(JFXButton button : selectedTracker.keySet()){
                button.setStyle("-fx-background-color:" + darkCyan);
                selectedTracker.replace(button, false);
                button.setButtonType(JFXButton.ButtonType.FLAT);

            }

            selectedTracker.replace(clientsTab, true);
            clientsTab.setStyle("-fx-background-color:" + pink);
            clientsTab.setButtonType(JFXButton.ButtonType.RAISED);


        });

        //Home tab handle
        homeTab.setOnMouseClicked(e -> {

            for(JFXButton button : selectedTracker.keySet()){
                button.setStyle("-fx-background-color:" + darkCyan);
                selectedTracker.replace(button, false);
                button.setButtonType(JFXButton.ButtonType.FLAT);

            }

            selectedTracker.replace(homeTab, true);
            homeTab.setStyle("-fx-background-color:" + pink);
            homeTab.setButtonType(JFXButton.ButtonType.RAISED);


        });

    }

    public String searchPolling(){

        String searchField = searchTextField.getText();


        return "sr";
    }


    //Getters & Setters




}