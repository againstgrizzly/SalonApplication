package Model;

import MiscObjects.Appointment;
import MiscObjects.Employee;
import MiscObjects.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchedulingScreenModel {

    private String url = "jdbc:h2:" + // protocol
            System.getProperty("user.dir") + "/stylinDB";

    /* The following method queries the database to find all appointments for a given Date.
     * The method returns an arrayList of the appointment objects.
     */
    public List<Appointment> findAppsDate(Date date) {

        List<Appointment> apps = new ArrayList<>();
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url, "sa", "");
            //Getting all appointment ID's for a given Date
            //Creates an appointment object with each ID and adds it to an ArrayList
            String sql = "SELECT appt_id "
                    + "FROM appointments "
                    + "WHERE date = ?";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setDate(1, date);
            ResultSet rs = stmnt.executeQuery();
            rs.first();
            while (rs.next()) {
                Appointment tempApp = new Appointment();
                tempApp.setAppt_id(rs.getString(1));
                apps.add(tempApp);
            }
            //Cycles through the Appointment ID's to query the appt_serv table to find
            //all of the service id's for that appointment.
            for (Appointment app : apps) {
                app.setDate(date);
                List<Service> services = new ArrayList<>();
                sql = "SELECT service_id FROM Appt_serv "
                        + "WHERE appt_id = ?;";
                stmnt = conn.prepareStatement(sql);
                stmnt.setString(1, app.getAppt_id());
                rs = stmnt.executeQuery();
                rs.first();
                while (rs.next()) {
                    Service serv = new Service();
                    serv.setService_id(rs.getString(1));
                    services.add(serv);
                }
                //Queries the Services table to find all of the Service info
                //for the service_ids that were just found
                for (Service serv : services) {
                    sql = "SELECT name, description, price FROM service "
                            + "WHERE service_id = ?";
                    stmnt = conn.prepareStatement(sql);
                    stmnt.setString(1, serv.getService_id());
                    rs = stmnt.executeQuery();
                    rs.first();
                    serv.setName(rs.getString(1));
                    serv.setDescription(rs.getString(2));
                    serv.setPrice(rs.getDouble(3));
                }
                app.setServices(services);
                //Query the appointments table with the given appt_id
                //to find the rest of the info about the appointment.
                sql = "SELECT client_id, start_time, end_time, duration, notes, employee_id"
                        + " FROM appointments WHERE appt_id = ?";
                stmnt = conn.prepareStatement(sql);
                stmnt.setString(1, app.getAppt_id());
                rs = stmnt.executeQuery();
                rs.first();
                while (rs.next()) {
                    app.setClient_id(rs.getString(1));
                    app.setStart_time(rs.getTime(2));
                    app.setEnd_time(rs.getTime(3));
                    app.setDuration(rs.getInt(4));
                    app.setNotes(rs.getString(5));
                    app.setEmployee_id(rs.getString(6));
                }
            }
            conn.close(); //closing the connection
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            System.exit(1);
        }
        return apps;
    }//end findAppsDate

//    public List<Appointment> getAppointmentsForDate(Date date){
//
//        List<Appointment> appointmentsForThisDate = new ArrayList<>();
//
//        try{
//            Class.forName("org.h2.Driver");
//            Connection connection = DriverManager.getConnection(url, "sa", ""); // password
//            Statement statement = connection.createStatement();
//
//            //String query = "select * from appointments where date = '" + date + "';";
//            String query = "select * from appointments where date = '2017-03-05';";
//
//            ResultSet resultSet = statement.executeQuery(query);
//
//            if(resultSet.next())
//            while(resultSet.next()){
//
//                Appointment appointment = new Appointment();
//
//                appointment.setAppt_id(resultSet.getString("APPT_ID"));
//                appointment.setClient_id(resultSet.getString("CLIENT_ID"));
//                appointment.setEmployee_id(resultSet.getString("EMPLOYEE_ID"));
//                appointment.setDate(resultSet.getDate("DATE"));
//                appointment.setStart_time(resultSet.getTime("START_TIME"));
//                appointment.setEnd_time(resultSet.getTime("END_TIME"));
//                appointment.setDuration(resultSet.getInt("DURATION"));
//                appointment.setNotes(resultSet.getString("NOTES"));
//
//                List<Service> services = new ArrayList<>();
//
//                String queryServiceIdsForThisAppointment = "select s.service_id from appt_serv s where appt_id ='"+ appointment.getAppt_id() +"';";
//
//                ResultSet resultSet1 = statement.executeQuery(queryServiceIdsForThisAppointment);
//                List<String> listOfServiceIdsForThisAppointment = new ArrayList<>();
//
//                while(resultSet1.next()){
//                    listOfServiceIdsForThisAppointment.add(resultSet1.getString("SERVICE_ID"));
//                }
//
//
//                for(String serviceID: listOfServiceIdsForThisAppointment){
//                    String queryServicesForThisAppointment = "select * from service where service_id = '" + serviceID +"';";
//                    ResultSet resultSet2 = statement.executeQuery(queryServicesForThisAppointment);
//
//                    Service service = new Service();
//
//                        service.setService_id(resultSet2.getString("SERVICE_ID"));
//                        service.setName(resultSet2.getString("NAME"));
//                        service.setDescription(resultSet2.getString("DESCRIPTION"));
//                        service.setPrice(resultSet2.getDouble("PRICE"));
//
//                        services.add(service);
//
//                }
//
//                appointment.setServices(services);
//
//            }
//
//            connection.close();
//
//        }catch(Exception e){
//            e.printStackTrace();
//            System.out.println("There are no appointments for this date: " +  date);
//            return null;
//        }
//
//        return appointmentsForThisDate;
//    }

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


        while (resultSet.next()) {
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
        employee.setUsername(username);
        employee.setF_name(firstName);
        employee.setL_name(lastName);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setDate_of_birth(dateOfBirth);

        connection.close();

        return employee;
    }

    public List<Employee> getAllEmployees() {

        List<Employee> employeeList = new ArrayList<>();

        try {

            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(url, "sa", ""); // password
            Statement statement = connection.createStatement();

            String query = "select * from employees";

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){

                String employee_id = resultSet.getString("EMPLOYEE_ID");
                String username = resultSet.getString("USERNAME");
                String f_name = resultSet.getString("F_NAME");
                String l_name = resultSet.getString("L_NAME");
                long phone = resultSet.getLong("PHONE");
                String email = resultSet.getString("EMAIL");
                Date date_of_birth = resultSet.getDate("DATE_OF_BIRTH");

                Employee employee = new Employee();

                employee = new Employee();
                employee.setEmployee_id(employee_id);
                employee.setUsername(username);
                employee.setF_name(f_name);
                employee.setL_name(l_name);
                employee.setEmail(email);
                employee.setPhone(phone);
                employee.setDate_of_birth(date_of_birth);

                employeeList.add(employee);

            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employeeList;
    }
}



