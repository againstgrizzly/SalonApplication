package Model;

import MiscObjects.Appointment;
import MiscObjects.Employee;
import MiscObjects.Queries;
import MiscObjects.Service;

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
            String query = "select * from LOGINS" +
                    " where username = '" + username +
                    "' and password = '" + pin + "';";
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

    public Employee getLoggedInEmployee(String username) throws Exception {

        Employee employee;
        String employee_id = null;
        //String username; already defined, but leaving for visually reference
        String firstName = null;
        String lastName = null;
        long phone = -1;
        String email = null;
        Date dateOfBirth = null;


        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(url, "sa", ""); // password
        Statement statement = connection.createStatement();

        //Get employee information
        String query = "select * " +
                "from employees e " +
                "where e.username = '" + username + "';";

        ResultSet resultSet = statement.executeQuery(query);


        while(resultSet.next()) {
            employee_id = resultSet.getString("EMPLOYEE_ID");
            firstName = resultSet.getString("F_NAME");
            lastName = resultSet.getString("L_NAME");
            phone = resultSet.getLong("PHONE");
            email = resultSet.getString("EMAIL");
            dateOfBirth = resultSet.getDate("DATE_OF_BIRTH");
        }

        System.out.println(email);

        employee = new Employee();

        employee.setEmployee_id(employee_id);
        employee.setF_name(firstName);
        employee.setL_name(lastName);
        employee.setPhone(phone);
        employee.setEmail(email);
        employee.setDate_of_birth(dateOfBirth);

        return employee;
    }

}