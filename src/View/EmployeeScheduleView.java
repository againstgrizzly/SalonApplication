package View;

import MiscObjects.AppointmentPane;
import MiscObjects.Employee;
import javafx.scene.layout.AnchorPane;
import java.sql.Date;

/*
This purpose of this class is to create the visual representation
of an employee scheduling block for a specific date

 */

public class EmployeeScheduleView {

    AppointmentPane appointmentPane;
    Employee employee;
    Date date;

    AnchorPane nameAnchorPane;
    AnchorPane scheduleBasePane;

    public EmployeeScheduleView(Employee employee, Date date){
        this.employee = employee;
        this.date = date;


        nameAnchorPane = new AnchorPane();

        scheduleBasePane = new AnchorPane();

        appointmentPane = new AppointmentPane(employee, date, nameAnchorPane, scheduleBasePane);

    }

    public AppointmentPane getAppointmentPane() {
        return appointmentPane;
    }

    public void setAppointmentPane(AppointmentPane appointmentPane) {
        this.appointmentPane = appointmentPane;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AnchorPane getNameAnchorPane() {
        return nameAnchorPane;
    }

    public void setNameAnchorPane(AnchorPane nameAnchorPane) {
        this.nameAnchorPane = nameAnchorPane;
    }

    public AnchorPane getScheduleBasePane() {
        return scheduleBasePane;
    }

    public void setScheduleBasePane(AnchorPane scheduleBasePane) {
        this.scheduleBasePane = scheduleBasePane;
    }
}

