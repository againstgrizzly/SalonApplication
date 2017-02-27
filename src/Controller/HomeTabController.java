package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;




public class HomeTabController implements Initializable {

    @FXML AnchorPane homeTabAnchorPane;

    public HomeTabController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public AnchorPane getSchedulingTabAnchorPane(){
        return homeTabAnchorPane;
    }
}
