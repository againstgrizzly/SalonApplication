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
    
    /*
	 * The following method can be used to insert a client
	 * into the database. It takes the URL(directory) of the database
	 * as one of the parameters. It assumes that the username for the 
	 * database is "sa" and the password is blank. It also assumes the
	 * table for clients is named CLIENT.
	 */
	public void InsertClient(Client client, String url){
		  try {
			  Class.forName("org.h2.Driver");
			  Connection conn = DriverManager.getConnection(url,"sa","");
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
		  }
		  catch(ClassNotFoundException | SQLException ex) {
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
	public Client GetClientInfo(long phoneNum, String url){
		Client client = null;
		  try {
			  Class.forName("org.h2.Driver");
			  Connection conn = DriverManager.getConnection(url,"sa","");
			  if (FindClient(phoneNum, conn)){
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
				  
			  }else{
				  /*Client was not found in the database
				   *We can handle that here with an error dialog
				   *or we can return a null client and the error
				   *dialog can be produced from the caller of this method.
				   */
			  }
			  conn.close(); //closing the connection
		  }
		  catch(ClassNotFoundException | SQLException ex) {
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
	public boolean FindClient(long phoneNum, Connection conn) throws SQLException{
		boolean found = false;
		String sql = "SELECT phone FROM client WHERE phone = ?";
		PreparedStatement stmnt = conn.prepareStatement(sql);
		stmnt.setLong(1, phoneNum);
		ResultSet rs = stmnt.executeQuery();
		if (rs.next()){
			found = true;
		}
		return found;
	} //End FindClient


} //End Class
