package Controller;

import MiscObjects.Appointment;
import Model.SchedulingScreenModel;
import View.SchedulingScreenView;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import jfxtras.scene.control.LocalDatePicker;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import MiscObjects.AppointmentPane;

/**
 * Created by Brannon on 3/10/2017.
 */
public class SchedulingScreenController{

    private SchedulingScreenModel schedulingScreenModel;
    private SchedulingScreenView schedulingScreenView;
    private List<Appointment> appointmentList;
    private List<AppointmentPane> appointmentPaneList;
    private Map<Integer, Appointment> appointmentMap = new HashMap<>();

    public SchedulingScreenController(SchedulingScreenModel schedulingScreenModel, SchedulingScreenView schedulingScreenView){
        this.schedulingScreenModel = schedulingScreenModel;
        this.schedulingScreenView = schedulingScreenView;

        datePickerListener();



    }

    public void insertInitialAppointments(){
        //I need to get all the appointments for this day
        Date todaysDate = Date.valueOf(LocalDate.now());
        appointmentList = schedulingScreenModel.findAppsDate(todaysDate);

        for(Appointment appointment : appointmentList){
            appointmentMap.put(Integer.valueOf(appointment.getClient_id()), appointment);
        }


    }

    public void datePickerListener(){

        LocalDatePicker datePicker = schedulingScreenView.getLocalDatePicker();

        datePicker.localDateProperty().addListener(e -> {
            System.out.println(datePicker.getLocalDate());
        });


    }





}
