import java.sql.*;

public class MiscMethods {
	
	/*
	 * The following method can be used to insert an employee
	 * into the database. It takes the URL(directory) of the database
	 * as one of the parameters. It assumes that the username for the 
	 * database is "sa" and the password is blank. It also assumes the
	 * table for employees is named EMPLOYEE
	 */
	public void InsertEmployee(Employee emp, String url){
		  try {
			  Class.forName("org.h2.Driver");

			  Connection conn = DriverManager.getConnection(url,"sa","");
			  String sql = "INSERT INTO employee"
			  			+ "(f_name, l_name, email, phone, username, date_of_birth)"
			  			+ "VALUES(?,?,?,?,?,?)";
			  PreparedStatement stmnt = conn.prepareStatement(sql);
			  stmnt.setString(1, emp.getF_name());
			  stmnt.setString(2, emp.getL_name());
			  stmnt.setString(3, emp.getEmail());
			  stmnt.setInt(4, emp.getPhone());
			  stmnt.setString(5, emp.getUsername());
			  stmnt.setDate(6, emp.getDate_of_birth());
			  stmnt.execute();
			  
			  conn.close(); //closing the connection
		  }
		  catch(ClassNotFoundException | SQLException ex) {
			  System.out.println(ex);
			  System.exit(1);
		  }
	} //End InsertEmployee
	
	/*
	 * The following method can be used to insert a Service
	 * into the database. It takes the URL(directory) of the database
	 * as one of the parameters. It assumes that the username for the 
	 * database is "sa" and the password is blank. It also assumes the
	 * table for services is named SERVICE
	 */
	void InsertService(Service serv, String url){
		  try {
			  Class.forName("org.h2.Driver");

			  Connection conn = DriverManager.getConnection(url,"sa","");
			  String sql = "INSERT INTO service"
			  			+ "(name, description, price)"
			  			+ "VALUES(?,?,?)";
			  PreparedStatement stmnt = conn.prepareStatement(sql);
			  stmnt.setString(1, serv.getName());
			  stmnt.setString(2, serv.getDescription());
			  stmnt.setDouble(3, serv.getPrice());
			  stmnt.execute();
			  
			  conn.close(); //closing the connection
		  }
		  catch(ClassNotFoundException | SQLException ex) {
			  System.out.println(ex);
			  System.exit(1);
		  }
		
	} //End InsertService
	
	/*
	 * The following method can be used to insert an Appointment
	 * into the database. It takes the URL(directory) of the database
	 * as one of the parameters. It assumes that the username for the 
	 * database is "sa" and the password is blank. It also assumes the
	 * table for appointments is named APPOINTMENT
	 */
	void InsertAppointment(Appointment appt, String url){
		  try {
			  Class.forName("org.h2.Driver");

			  Connection conn = DriverManager.getConnection(url,"sa","");
			  String sql = "INSERT INTO appointment"
			  			+ "(appt_time, notes, client_id, employee_id, duration)"
			  			+ "VALUES(?,?,?,?,?)";
			  PreparedStatement stmnt = conn.prepareStatement(sql);
			  stmnt.setTime(1, appt.getAppt_time());
			  stmnt.setString(2, appt.getNotes());
			  stmnt.setString(3, appt.getClient_id());
			  stmnt.setString(4, appt.getEmployee_id());
			  stmnt.setInt(5, appt.getDuration());
			  stmnt.execute();
			  
			  conn.close(); //closing the connection
		  }
		  catch(ClassNotFoundException | SQLException ex) {
			  System.out.println(ex);
			  System.exit(1);
		  }
	}
	

} //End Class
