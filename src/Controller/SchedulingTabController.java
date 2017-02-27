package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import jfxtras.scene.control.CalendarPicker;
import sun.plugin.javascript.navig.Anchor;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class SchedulingTabController implements Initializable {

    @FXML
    private AnchorPane schedulingTabAnchorPane;

    @FXML
    private AnchorPane calendarPane;

    private DatePicker datePicker;



    private int i = 0;


    //Is called when created in MainWindowController
    public SchedulingTabController() {


    }

    @Override //Is called when the window  is first used by javaFX
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guiSetup() {


    }

    public AnchorPane getSchedulingTabAnchorPane(){
        return schedulingTabAnchorPane;
    }


}
