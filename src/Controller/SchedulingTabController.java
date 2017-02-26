package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SchedulingTabController implements Initializable {

    com.sun.javafx.scene.control.skin.DatePickerSkin skin;

    @FXML
    private AnchorPane schedulingTabAnchorPane;

    @FXML
    private AnchorPane calendarPane;

    private DatePicker datePicker = new DatePicker();


    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }


}
