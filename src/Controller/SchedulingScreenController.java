package Controller;

import Model.SchedulingScreenModel;
import View.SchedulingScreenView;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import jfxtras.scene.control.LocalDatePicker;

/**
 * Created by Brannon on 3/10/2017.
 */
public class SchedulingScreenController{

    private SchedulingScreenModel schedulingScreenModel;
    private SchedulingScreenView schedulingScreenView;

    public SchedulingScreenController(SchedulingScreenModel schedulingScreenModel, SchedulingScreenView schedulingScreenView){
        this.schedulingScreenModel = schedulingScreenModel;
        this.schedulingScreenView = schedulingScreenView;

        datePickerListener();



    }

    public void datePickerListener(){

        LocalDatePicker datePicker = schedulingScreenView.getLocalDatePicker();

        datePicker.localDateProperty().addListener(e -> {
            System.out.println(datePicker.getLocalDate());
        });


    }





}
