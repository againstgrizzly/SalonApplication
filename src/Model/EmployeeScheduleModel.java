package Model;

import MiscObjects.Appointment;
import MiscObjects.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brannon on 4/3/2017.
 */
public class EmployeeScheduleModel {

    private String url = "jdbc:h2:" + // protocol
            System.getProperty("user.dir") + "/stylinDB";

    private Statement statement;
    private Employee employee;

    public EmployeeScheduleModel(Employee employee) {
        this.employee = employee;
    }


    //TODO
    public List<Appointment> getAppointmentsFromDatabase(String employeeID) {

        List<Appointment> appointmentList = new ArrayList<>();

        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url, "sa", "");
            Statement statement = conn.createStatement();
            String query = "select * from appointments" +
                    "where employee_id =" + employeeID;

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppt_id(resultSet.getString("APPT_ID"));
                appointment.setClient_id(resultSet.getString("CLIENT_ID"));
                appointment.setEmployee_id(resultSet.getString("EMPLOYEE_ID"));
                //get time? timestamp? from database
                appointment.setNotes(resultSet.getString("NOTES"));
                appointment.setDuration(resultSet.getInt("DURATION"));

                appointmentList.add(appointment);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointmentList;

    }

}
