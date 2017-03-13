package Controller;

import Model.SchedulingTabModel;
import View.SchedulingTabView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Brannon on 3/10/2017.
 */
public class SchedulingTabController{

    SchedulingTabView schedulingTabView;
    SchedulingTabModel schedulingTabModel;

    public SchedulingTabController(SchedulingTabModel schedulingTabModel, SchedulingTabView schedulingTabView){
        this.schedulingTabModel = schedulingTabModel;
        this.schedulingTabView = schedulingTabView;

        sliderHandle();
    }

    public void sliderHandle(){
        schedulingTabView.getRootPane().setMaxHeight(10.0);

    }




}
