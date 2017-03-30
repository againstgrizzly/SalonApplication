package MiscObjects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Queries {

    private String url = "jdbc:h2:" + // protocol
            System.getProperty("user.dir") + "/stylinDB";

    private Statement statement;


    /*
     * The following method can be used to insert an employee
     * into the database. It takes the URL(directory) of the database
     * as one of the parameters. It assumes that the username for the
     * database is "sa" and the password is blank. It also assumes the
     * table for employees is named EMPLOYEE
     */
    public void InsertEmployee(Employee emp, String url) {
        try {
            Class.forName("org.h2.Driver");

            Connection conn = DriverManager.getConnection(url, "sa", "");
            String sql = "INSERT INTO employee"
                    + "(f_name, l_name, email, phone, username, date_of_birth)"
                    + "VALUES(?,?,?,?,?,?)";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, emp.getF_name());
            stmnt.setString(2, emp.getL_name());
            stmnt.setString(3, emp.getEmail());
            stmnt.setDouble(4, emp.getPhone());
            stmnt.setString(5, emp.getUsername());
            stmnt.setDate(6, emp.getDate_of_birth());
            stmnt.execute();

            conn.close(); //closing the connection
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(1);
        }
    } //End InsertEmployee

    /*
     * The following method can be used to insert a MiscObjects.Service
     * into the database. It takes the URL(directory) of the database
     * as one of the parameters. It assumes that the username for the
     * database is "sa" and the password is blank. It also assumes the
     * table for services is named SERVICE
     */
    void InsertService(Service serv, String url) {
        try {
            Class.forName("org.h2.Driver");

            Connection conn = DriverManager.getConnection(url, "sa", "");
            String sql = "INSERT INTO service"
                    + "(name, description, price)"
                    + "VALUES(?,?,?)";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, serv.getName());
            stmnt.setString(2, serv.getDescription());
            stmnt.setDouble(3, serv.getPrice());
            stmnt.execute();

            conn.close(); //closing the connection
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(1);
        }

    } //End InsertService

    /*
     * The following method can be used to insert an MiscObjects.Appointment
     * into the database. It takes the URL(directory) of the database
     * as one of the parameters. It assumes that the username for the
     * database is "sa" and the password is blank. It also assumes the
     * table for appointments is named APPOINTMENT
     */
    void InsertAppointment(Appointment appt, String url) {
        try {
            Class.forName("org.h2.Driver");

            Connection conn = DriverManager.getConnection(url, "sa", "");
            String sql = "INSERT INTO appointment"
                    + "(appt_time, notes, client_id, employee_id, duration)"
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setTimestamp(1, appt.getAppt_time());
            stmnt.setString(2, appt.getNotes());
            stmnt.setString(3, appt.getClient_id());
            stmnt.setString(4, appt.getEmployee_id());
            stmnt.setInt(5, appt.getDuration());
            stmnt.execute();

            conn.close(); //closing the connection
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(1);
        }
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
        double phoneNumber;
        Date dateOfBirth;

        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url, "sa", "");
            Statement statement = conn.createStatement();
            String query = "select e.employee_id ID, e.username USERNAME, e.f_name FIRSTNAME, e.l_name LASTNAME, e.email EMAIL, e.phone PHONE, e.date_of_birth DATEOFBIRTH from employees e where e.username = '"+ username +"';";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                employee_id = resultSet.getString("ID");
                username1 = resultSet.getString("USERNAME");
                firstName = resultSet.getString("FIRSTNAME");
                lastName = resultSet.getString("LASTNAME");
                email = resultSet.getString("EMAIL");
                phoneNumber = resultSet.getDouble("PHONE");
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

            while(resultSet.next()){
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

    //This is used to get all the employees data that are
    //currently in the system
    public List<Employee> getAllEmployees(){
        List<Employee> listOfEmployees = new ArrayList<>();
        String employee_id;
        String username1;
        String firstName;
        String lastName;
        String email;
        double phoneNumber;
        Date dateOfBirth;

        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url, "sa", "");
            Statement statement = conn.createStatement();
            String query = "select * from employees";
            ResultSet resultSet = statement.executeQuery(query);

            //Creates a list of employee objects for each employee and adds their information sans appointments
            while (resultSet.next()) {
                employee_id = resultSet.getString("EMPLOYEE_ID");
                username1 = resultSet.getString("USERNAME");
                firstName = resultSet.getString("F_NAME");
                lastName = resultSet.getString("L_NAME");
                phoneNumber = resultSet.getDouble("PHONE");
                email = resultSet.getString("EMAIL");
                dateOfBirth = resultSet.getDate("DATE_OF_BIRTH");
                Employee employee = new Employee();
                employee.setEmployee_id(employee_id);
                employee.setUsername(username1);
                employee.setF_name(firstName);
                employee.setL_name(lastName);
                employee.setEmail(email);
                employee.setPhone(phoneNumber);
                employee.setDate_of_birth(dateOfBirth);
                listOfEmployees.add(employee);
            }

            //Get all the appointments for the currently logged in employee
            for(Employee e : listOfEmployees) {
                List<Appointment> appointmentList = new ArrayList<>();

                query = "select * from appointments where employee_id ='" + e.getEmployee_id() + "'";
                resultSet = statement.executeQuery(query);

                while(resultSet.next()){
                    Appointment appointment = new Appointment();
                    appointment.setAppt_id(resultSet.getString("APPT_ID"));
                    appointment.setClient_id(resultSet.getString("CLIENT_ID"));
                    appointment.setEmployee_id(resultSet.getString("EMPLOYEE_ID"));
                    appointment.setAppt_time(resultSet.getTimestamp("TIME"));
                    appointment.setNotes(resultSet.getString("NOTES"));
                    appointment.setDuration(resultSet.getInt("DURATION"));
                    appointmentList.add(appointment);
                }
                e.setAppointments(appointmentList);
            }


            return listOfEmployees;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


} //End Class
