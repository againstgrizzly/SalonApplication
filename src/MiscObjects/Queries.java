package MiscObjects;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


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
    public void InsertEmployee(Employee emp, String password, int usertype) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url, "sa", "");
            InsertLogin(emp.getUsername(), password, usertype); //Handling the Logins table
            String sql = "INSERT INTO employees"
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

    public void InsertLogin(String username, String password, int usertype){
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url, "sa", "");

            //Query to see if it exists
            String sql = "SELECT username FROM logins WHERE username = ?";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, username);
            ResultSet rs = stmnt.executeQuery();
            rs.first();
            if (!rs.next()){ // not existing in the table

                //Inserting into the Logins table
                sql = "INSERT INTO logins (username, password, usertype)"
                        + "values(?,?,?)";
                stmnt = conn.prepareStatement(sql);
                stmnt.setString(1, username);
                stmnt.setString(2, password);
                stmnt.setInt(3, usertype);
                stmnt.execute();
            }else{ //username already exists
                //HANDLE CASE THAT USERNAME ALREADY EXISTS
            }



            conn.close(); //closing the connection
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(1);
        }
    } //End InsertLogin



    /*
     * The following method can be used to insert a client
	 * into the database. It takes the URL(directory) of the database
	 * as one of the parameters. It assumes that the username for the 
	 * database is "sa" and the password is blank. It also assumes the
	 * table for clients is named CLIENT.
	 */
    public void InsertClient(Client client) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url, "sa", "");
            String sql = "INSERT INTO client"
                    + "(f_name, l_name, phone, email, employee_id, color_formula, "
                    + "address, city, state, postal_code)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, client.getF_name());
            stmnt.setString(2, client.getL_name());
            stmnt.setLong(3, client.getPhone());
            stmnt.setString(4, client.getEmail());
            stmnt.setString(5, client.getEmployee_id());
            stmnt.setString(6, client.getColor_formula());
            stmnt.setString(7, client.getAddress());
            stmnt.setString(8, client.getCity());
            stmnt.setString(9, client.getState());
            stmnt.setString(10, client.getPostal_code());
            stmnt.execute();

            conn.close(); //closing the connection
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            System.exit(1);
        }
    } // End InsertClient

    /* The following method can be used to get a client from the
     * database when searching with the client's phone number.
     * It requires a phone number(int) and the URL(directory)
     * of the database. It assumes that the username for the database
     * is "sa" and the password is blank. It also assumes that the
     * table for clients is named CLIENT.
     */
    public Client GetClientInfo(long phoneNum, String url) {
        Client client = null;
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url, "sa", "");
            if (FindClient(phoneNum, conn)) {
                String sql = "SELECT f_name,l_name, phone, email, employee_id, "
                        + "color_formula, address, city, state, postal_code"
                        + " FROM client WHERE phone = ?";
                PreparedStatement stmnt = conn.prepareStatement(sql);
                stmnt.setLong(1, phoneNum);
                ResultSet rs = stmnt.executeQuery();
                rs.first();
                client.setF_name(rs.getString(1));
                client.setL_name(rs.getString(2));
                client.setPhone(rs.getLong(3));
                client.setEmail(rs.getString(4));
                client.setEmployee_id(rs.getString(5));
                client.setColor_formula(rs.getString(6));
                client.setAddress(rs.getString(7));
                client.setCity(rs.getString(8));
                client.setState(rs.getString(9));
                client.setPostal_code(rs.getString(10));

            } else {
                  /*Client was not found in the database
				   *We can handle that here with an error dialog
				   *or we can return a null client and the error
				   *dialog can be produced from the caller of this method.
				   */
            }
            conn.close(); //closing the connection
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            System.exit(1);
        }
        return client;
    }

    /* The following Boolean method can be used to determine if a Client is in
     * the database. It can be used when searching by the client's phone number
     * It takes an established Connection object as a parameter.
     * It assumes that the caller will have the appropriate TRY CATCH blocks.
     */
    public boolean FindClient(long phoneNum, Connection conn) throws SQLException {
        boolean found = false;
        String sql = "SELECT phone FROM client WHERE phone = ?";
        PreparedStatement stmnt = conn.prepareStatement(sql);
        stmnt.setLong(1, phoneNum);
        ResultSet rs = stmnt.executeQuery();
        if (rs.next()) {
            found = true;
        }
        return found;
    } //End FindClient

    public String resetPIN(String pin, String username) {
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(url, "sa", "");

            Statement statement = connection.createStatement();
            String query = "update logins set password = '" + pin + "' where username = '" + username + "';";
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pin;
    }

    public Boolean returnPIN(String username, String dateOfBirth) {
        boolean validInput = false;
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date dob;
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(url, "sa", "");

            dob = df.parse(dateOfBirth);
            String newDate = df.format(dob);

            System.out.println(newDate);
            Statement statement = connection.createStatement();

            String query = "select * from employees where username = '" + username + "' and date_of_birth = '" + newDate + "';";
            ResultSet resultSet = statement.executeQuery(query);

            if (!resultSet.next()) {
                validInput = false;
            } else {
                validInput = true;
            }
            resultSet.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return validInput;
    }

    public Boolean UsernameDisplayAttempt(String firstname, String lastname, String dateOfBirth) {
        boolean validInput = false;
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(url, "sa", "");

            Statement statement = connection.createStatement();
            String query = "select * from employees where f_name = '" + firstname + "' and l_name = '" + lastname + "' and date_of_birth = '" + dateOfBirth + "';";
            ResultSet resultSet = statement.executeQuery(query);

            if (!resultSet.next()) {
                validInput = false;
            } //sets validInput to false for empty table meaning that it didn't find that user/pin
            else {
                validInput = true;
            }
            resultSet.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return validInput;
    }


    public Client getClientInfoUsingID(String id) {

        Client client = new Client();

        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(url, "sa", ""); // password
            Statement statement = connection.createStatement();

            //Get client using client id
            String query = "select * from client where client.client_id = '" + id + "';";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                client.setClient_id(resultSet.getString("CLIENT_ID"));
                client.setEmployee_id(resultSet.getString("EMPLOYEE_ID"));
                client.setF_name(resultSet.getString("F_NAME"));
                client.setL_name(resultSet.getString("L_NAME"));
                client.setPhone(resultSet.getLong("PHONE"));
                client.setEmail(resultSet.getString("EMAIL"));
                client.setColor_formula(resultSet.getString("COLOR_FORMULA"));
                client.setAddress(resultSet.getString("ADDRESS"));
                client.setCity(resultSet.getString("CITY"));
                client.setState(resultSet.getString("STATE"));
                client.setPostal_code(resultSet.getString("POSTAL_CODE"));
            }

            connection.close();//close that connect, bruh

        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }

        return client;
    }

    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<>();

        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(url, "sa", ""); // password
            Statement statement = connection.createStatement();

            String query = "select * from employees;";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                Employee employee = new Employee();

                employee.setEmployee_id(resultSet.getString("EMPLOYEE_ID"));
                employee.setUsername(resultSet.getString("USERNAME"));
                employee.setF_name(resultSet.getString("F_NAME"));
                employee.setL_name(resultSet.getString("L_NAME"));
                employee.setPhone(resultSet.getLong("PHONE"));
                employee.setEmail(resultSet.getString("EMAIL"));
                employee.setDate_of_birth(resultSet.getDate("DATE_OF_BIRTH"));

                employees.add(employee);

            }

            connection.close();//close that connect, bruh

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }


    public Appointment getEmployeeAppointments() {
        Appointment appointment = new Appointment();
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(url, "sa", ""); // password
            Statement statement = connection.createStatement();

            String query = "select * from appointments;";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                appointment.setAppt_id(resultSet.getString("APPT_ID"));
                appointment.setClient_id(resultSet.getString("CLIENT_ID"));
                appointment.setEmployee_id(resultSet.getString("EMPLOYEE_ID"));
                appointment.setDate(resultSet.getDate("DATE"));
                appointment.setStart_time(resultSet.getTime("START_TIME"));
                appointment.setEnd_time(resultSet.getTime("END_TIME"));
                appointment.setDuration(resultSet.getInt("DURATION"));
                appointment.setNotes(resultSet.getString("NOTES"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointment;
    }

}//End Class
