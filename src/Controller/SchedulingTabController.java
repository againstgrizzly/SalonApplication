package Controller;

import Model.Stylist;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Brannon on 3/10/2017.
 */
public class SchedulingTabController implements Initializable {

    @FXML
    AnchorPane rootPane;

    List<Stylist> stylistList = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        testing();




    }

    public void testing(){

    }
}
