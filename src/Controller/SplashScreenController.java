package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController implements Initializable {

    @FXML
    private AnchorPane splashScreenAnchorPane;
    private String splashScreenBackgroundColor = "#e91e63";

    public SplashScreenController(){
        System.out.println("Splash Screen Controller Constructor");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        splashScreenAnchorPane.setStyle("-fx-background-color: " + splashScreenBackgroundColor + ";");

    }

    public void setSplashScreenBackgroundColor(String splashScreenBackgroundColor){
        this.splashScreenBackgroundColor = splashScreenBackgroundColor;
    }

}
