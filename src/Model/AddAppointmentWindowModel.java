package Model;

import MiscObjects.Appointment;
import MiscObjects.Employee;
import MiscObjects.Service;
import MiscObjects.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brannon on 4/3/2017.
 */
public class AddAppointmentWindowModel {

    String url = "jdbc:h2:" + // protocol
            System.getProperty("user.dir") + "/stylinDB";

    private Employee employee;

    //This query returns all the services that is offered by the salon
    public List<Service> getServices(Employee employee) {

        String username = employee.getUsername();

        List<Service> services = new ArrayList<>();


        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url, "sa", "");
            Statement statement = conn.createStatement();
            String query = "select * from service";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");
                double price = resultSet.getDouble("PRICE");
                String serviceID = resultSet.getString("SERVICE_ID");

                //Create the service object
                Service service = new Service();
                service.setService_id(serviceID);

                //add it to the list of services
                services.add(service);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return services;
    }
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();

        try {

            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(url, "sa", ""); // password
            Statement statement = connection.createStatement();

            String query = "select * " +
                    "from client;";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Client client = new Client();

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

                clients.add(client);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clients;
    }

    public List<Service> getServicesForThisEmployee(Employee employee) {

        List<Service> serviceList = new ArrayList<>();


        try {

            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url, "sa", "");
            Statement statement = conn.createStatement();
            String query = "select * \n" +
                    "from service s \n" +
                    "inner join emp_serv e\n" +
                    "on s.service_id = e.service_id\n" +
                    "inner join employees emp \n" +
                    "on e.employee_id = emp.employee_id\n" +
                    "where emp.employee_id = '" + employee.getEmployee_id() + "';";


            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                Service service = new Service();
                service.setService_id(resultSet.getString("SERVICE_ID"));
                service.setName(resultSet.getString("NAME"));
                service.setDescription(resultSet.getString("DESCRIPTION"));
                service.setPrice(resultSet.getDouble("PRICE"));

                serviceList.add(service);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceList;
    }

    /*
     * The following method can be used to insert a MiscObjects.Service
     * into the database. It takes the URL(directory) of the database
     * as one of the parameters. It assumes that the username for the
     * database is "sa" and the password is blank. It also assumes the
     * table for services is named SERVICE
     */
    void InsertService(Service serv) {
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
     *
     * Updated by Brannon (4/5/17)
     */

    void InsertAppointment(Appointment appt, List<Service> services) {
            try {
                Class.forName("org.h2.Driver");

                Connection conn = DriverManager.getConnection(url, "sa", "");

                //Insert new appointment into database
                String sql = "insert into appointments" +
                        "(client_id, employee_id, date, start_time, end_time, duration, notes)" +
                        "values(?,?,?,?,?,?)";

                PreparedStatement stmnt = conn.prepareStatement(sql);
                stmnt.setString(1, appt.getClient_id());
                stmnt.setString(2, appt.getEmployee_id());
                stmnt.setDate(3, appt.getDate());
                stmnt.setTime(4, appt.getStart_time());
                stmnt.setTime(5, appt.getEnd_time());
                stmnt.setInt(6, appt.getDuration());
                stmnt.setString(7, appt.getNotes());

            stmnt.execute();

            //Insert services for this appointment into APPT_SERV
            //I'm putting all this after the first query instead of within it
            //so that when it queries for the appointment id (which is auto generated by the
            //database) it exists
            Statement statement = conn.createStatement();

            String getNewlyInsertedAppointmentID = "select top 1 appt_id " +
                    "from appointments" +
                    "order by appt_id desc;";

            ResultSet resultSet = statement.executeQuery(getNewlyInsertedAppointmentID);

            String appointmentID = resultSet.getString("appt_id");
            appt.setAppt_id(appointmentID);


            //Insert services for a specific ID into APPT_SERV
            String sql2 = "insert into appt_serv" +
                    "(appt_id,service_id)" +
                    "values(?,?)";

            for (Service service : services) {
                stmnt = conn.prepareStatement(sql2);
                stmnt.setString(1, appointmentID);
                stmnt.setString(2, service.getService_id());
            }

            conn.close(); //closing the connection
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(1);
        }
    }

    public int timeToMinutes(Time time){

        int hoursToMinutes;
        int minutes;

        hoursToMinutes = time.getMinutes();
        minutes = hoursToMinutes + time.getMinutes();

        System.out.print(minutes);
        return minutes;
    }

    public Time minutesToTime(){
return null;    }

}
