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
     * The following method can be used to insert a client
	 * into the database. It takes the URL(directory) of the database
	 * as one of the parameters. It assumes that the username for the 
	 * database is "sa" and the password is blank. It also assumes the
	 * table for clients is named CLIENT.
	 */
    public void InsertClient(Client client, String url) {
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


} //End Class
