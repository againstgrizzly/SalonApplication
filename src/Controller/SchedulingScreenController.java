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
public class SchedulingScreenController {

    private SchedulingScreenModel schedulingScreenModel;
    private SchedulingScreenView schedulingScreenView;
    private List<Appointment> appointmentList;
    private List<AppointmentPane> appointmentPaneList;
    private Map<Integer, Appointment> appointmentMap = new HashMap<>();

    public SchedulingScreenController(SchedulingScreenModel schedulingScreenModel, SchedulingScreenView schedulingScreenView) {
        this.schedulingScreenModel = schedulingScreenModel;
        this.schedulingScreenView = schedulingScreenView;

        schedulingScreenView.setEmployees(schedulingScreenModel.getAllEmployees());

        datePickerListener();


    }


    public void getAppointmentsForASpecificDate() {

    }


    public void datePickerListener() {

        LocalDatePicker datePicker = schedulingScreenView.getLocalDatePicker();
        Date date = Date.valueOf(datePicker.getLocalDate());

        schedulingScreenView.populateTodaysAppointmentsIntoSchedulingContentPane(schedulingScreenModel.findAppsDate(date));

        datePicker.localDateProperty().addListener(e -> {
            if (datePicker.getLocalDate() != null && date.toLocalDate() != datePicker.getLocalDate()) {
                LocalDate newDate = datePicker.getLocalDate();
                schedulingScreenView.populateTodaysAppointmentsIntoSchedulingContentPane(schedulingScreenModel.findAppsDate(Date.valueOf(schedulingScreenView.getLocalDatePicker().getLocalDate())));
            }

            System.out.println(datePicker.getLocalDate());
        });


    }


}
