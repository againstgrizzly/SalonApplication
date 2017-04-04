package Model;

import MiscObjects.Appointment;
import MiscObjects.Employee;
import MiscObjects.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginModel {

    // The Model performs all the calculations needed
// and that is it. It doesn't know the View
// exists
    private String url = "jdbc:h2:" + // protocol
            System.getProperty("user.dir") + "/stylinDB";

    private Statement statement;

    public boolean isLoginValid(String username, String pin) {
        String inputtedUsername = username;
        int inputtedPin = Integer.valueOf(pin);
        boolean validInput = LoginAttempt(inputtedUsername, inputtedPin);
        return validInput;
    }

    //Is called when the user logs in. If the username/pin combination
    //exists in the database returns true, if not returns false
    public boolean LoginAttempt(String username, int pin) {
        boolean validInput = false;
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(url, "sa", ""); // password

            Statement statement = connection.createStatement();
            String query = "select * from logins where username = '" + username + "' and password = " + pin + ";";
            ResultSet resultSet = statement.executeQuery(query);

            if (!resultSet.next()) {
                validInput = false;
            } //sets validInput to false for empty table meaning that it didn't find that user/pin
            else {
                validInput = true;
            } //yeah buddy

            resultSet.close();
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return validInput;
    }

    //This method is used to create the employee object for the employee currently logged in
    public Employee getLoggedInEmployee(String username) {
        Employee loggedInEmployee = new Employee();
        List<Appointment> appointments = new ArrayList<>();
        String employee_id;
        String username1;
        String firstName;
        String lastName;
        String email;
        long phoneNumber;
        Date dateOfBirth;

        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url, "sa", "");
            Statement statement = conn.createStatement();
            String query = "select e.employee_id ID, e.username USERNAME, e.f_name FIRSTNAME, e.l_name LASTNAME, e.email EMAIL, e.phone PHONE, e.date_of_birth DATEOFBIRTH from employees e where e.username = '" + username + "';";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                employee_id = resultSet.getString("ID");
                username1 = resultSet.getString("USERNAME");
                firstName = resultSet.getString("FIRSTNAME");
                lastName = resultSet.getString("LASTNAME");
                email = resultSet.getString("EMAIL");
                phoneNumber = resultSet.getLong("PHONE");
                dateOfBirth = resultSet.getDate("DATEOFBIRTH");
                loggedInEmployee = new Employee();
                loggedInEmployee.setEmployee_id(employee_id);
                loggedInEmployee.setUsername(username1);
                loggedInEmployee.setF_name(firstName);
                loggedInEmployee.setL_name(lastName);
                loggedInEmployee.setEmail(email);
                loggedInEmployee.setPhone(phoneNumber);
                loggedInEmployee.setDate_of_birth(dateOfBirth);
            }

            //Get all the appointments for the currently logged in employee
            query = "select * from appointments where employee_id = " + loggedInEmployee.getEmployee_id() + ";";
            resultSet = statement.executeQuery(query);
            System.out.println("the employee who is loggin in is:" + loggedInEmployee.getEmployee_id());

            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                String appointmentId = resultSet.getString("APPT_ID");
                String clientId = resultSet.getString("CLIENT_ID");
                String employeeID = resultSet.getString("EMPLOYEE_ID");
                Timestamp timestamp = resultSet.getTimestamp("TIME");
                String notes = resultSet.getString("NOTES");
                int duration = resultSet.getInt("DURATION");
                appointment.setAppt_id(appointmentId);
                appointment.setClient_id(clientId);
                appointment.setEmployee_id(employeeID);
                appointment.setAppt_time(timestamp);
                appointment.setNotes(notes);
                appointment.setDuration(duration);
                appointments.add(appointment);
            }

            loggedInEmployee.setAppointments(appointments);

            return loggedInEmployee;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}