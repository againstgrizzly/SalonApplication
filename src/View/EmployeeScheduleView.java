package View;

import MiscObjects.Appointment;
import MiscObjects.Employee;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.Base64;
import java.util.List;

/*
This purpose of this class is to create the visual representation
of an employee scheduling block for a specific date

 */

public class EmployeeScheduleView {

    private Label nameLabel;
    private Employee employee;
    private AnchorPane basePane;
    private List<Appointment> appointmentList;
    private List<AnchorPane> appointmentBlockList;


    public EmployeeScheduleView(Employee employee){
        this.employee = employee;

        basePane = new AnchorPane();
        appointmentList = employee.getAppointments();
        nameLabel = new Label();
        nameLabel.setText(employee.getF_name() + employee.getL_name());

    }

    public void appointmentParser(List<Appointment> appointmentList){

        for(Appointment appointment : appointmentList){
            String time = appointment.getAppt_time().toString();



        }

    }


}
