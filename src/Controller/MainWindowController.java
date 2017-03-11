package Controller;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import jfxtras.scene.control.LocalDatePicker;

import java.net.URL;
import java.util.ResourceBundle;




public class MainWindowController implements Initializable {

    @FXML Button sideBarButton;
    @FXML LocalDatePicker datePicker;
    @FXML AnchorPane sideBarAnchorPane;
    @FXML AnchorPane mainWindowPane;
    @FXML SplitPane splitPane;

    @FXML Button homeButton;
    @FXML Button schedulingButton;
    @FXML Button clientButton;
    @FXML Button settingsButton;
    @FXML Tab homeTab;
    @FXML Tab schedulingTab;
    @FXML TabPane mainWindowTabPane;

    @FXML Label nameLabel;
    @FXML Label dateLabel;
    @FXML Label timeLabel;

    private boolean isOpen = false;

    @Override public void initialize(URL location, ResourceBundle resources) {

        //a temporary place to put stuff until i get organized
        aPlaceToPutStuffForNow();


        //Actions for the 4 main Buttons;
        buttonActions();
    }

    @FXML void OnActionSideBarButton(ActionEvent event) {
        if(isOpen){
            System.out.println("Close");
            datePicker.setVisible(false);
            splitPane.setDividerPosition(0, 0.0389);
            isOpen = false;

        }else{
            System.out.println("Open");
            datePicker.setVisible(true);
            splitPane.setDividerPosition(0, 0.2001);
            isOpen = true;
        }

    }

    public void buttonActions(){

        homeButton.setOnMouseClicked(e -> {
            System.out.println("Home Button Clicked");
            mainWindowTabPane.getSelectionModel().select(homeTab);

        });

        schedulingButton.setOnMouseClicked(e ->{
            System.out.println("Scheduling Button Clicked");
            mainWindowTabPane.getSelectionModel().select(schedulingTab);

        });

        clientButton.setOnMouseClicked(e -> {
            System.out.println("Client Button Clicked");

        });

        settingsButton.setOnMouseClicked(e -> {
            System.out.println("Settings Button Clicked");

        });

    }
    public void aPlaceToPutStuffForNow(){
        datePicker.setVisible(false);
        splitPane.setDividerPositions(0.0389);
        SplitPane.Divider divider = splitPane.getDividers().get(0);
        divider.positionProperty().addListener(e -> {

            if(divider.getPosition() < 0.10){
                datePicker.setVisible(false);
                isOpen = false;
            }else{
                datePicker.setVisible(true);
                isOpen = true;
            }

        });
    }


}
