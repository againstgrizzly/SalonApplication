package View;

import MiscObjects.Appointment;
import MiscObjects.Employee;
import MiscObjects.Queries;
import Model.EmployeeScheduleModel;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sun.plugin.javascript.navig.Anchor;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/*
This purpose of this class is to create the visual representation
of an employee scheduling block for a specific date

 */

public class EmployeeScheduleView {

    AnchorPane basePane;
    AnchorPane namePane;
    Label nameLabel;
    AnchorPane scheduleHolderPane;
    List<AnchorPane> appointmentsForToday = new ArrayList<>();
    List<Appointment> appointmentList = new ArrayList<>();

    Appointment testAppointment;

    Employee employee;
    String name = "e";


    public EmployeeScheduleView(Employee employee) {
        this.employee = employee;
        //testAppointment = new Queries().getAllEmployees().get(0).getAppointments().get(0);

        namePane = new AnchorPane();
        nameLabel = new Label(name);
        namePane.getChildren().add(nameLabel);


        scheduleHolderPane = new AnchorPane();

        populateAppointments(scheduleHolderPane);

        addAppointmentToEmployeeScheduleView(new Appointment());

        basePane = new AnchorPane();

    }

    public void populateAppointments(AnchorPane scheduleHolderPane) {


    }

    public void addAppointmentToEmployeeScheduleView(Appointment appointment) {

        double basePaneHeight = basePane.getHeight();
        //double

    }


    public void scheduleHolderPaneInterfacing() {

        scheduleHolderPane.setOnMouseClicked(e -> {
            //Open new appointment dialog window

            AnchorPane newAppointmentPane;

            if (false) {


            } else {
                //do nothing
            }

        });

        scheduleHolderPane.setOnMouseEntered(e -> {

        });

        scheduleHolderPane.setOnMouseExited(e -> {

        });

    }

    public Appointment newAppointment() {
        return new Appointment();//place holder
    }


}

