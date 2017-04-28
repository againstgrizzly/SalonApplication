package MiscObjects;


import Controller.AddAppointmentWindowController;
import Model.AddAppointmentWindowModel;
import View.AddAppointmentWindowView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.List;


//
public class AppointmentPane {

    private String url = "jdbc:h2:" + // protocol
            System.getProperty("user.dir") + "/stylinDB";

    Map<String, Double> startTimeDistance;
    Map<String, Double> endTimeDistance;

    private List<AnchorPane> appointmentPanels;
    private List<Client> clients = new ArrayList<>();
    private Date date;
    Employee employee;


    private AnchorPane namePane; //I need ad this to top HBAR
    private AnchorPane scheduleBasePane;//I need

    public AppointmentPane(Employee employee, Date date, AnchorPane nameAnchorPane, AnchorPane scheduleBasePane) {
        this.employee = employee;
        this.date = date;

        namePane = nameAnchorPane;
        namePane.setPrefSize(200.0, 30.0);
        namePane.setStyle("-fx-background-color: #55606e");
        namePane.setOnMouseEntered(e -> {
            namePane.setStyle("-fx-background-color: #9e9e9e");
        });
        namePane.setOnMouseExited(e -> {
            namePane.setStyle("-fx-background-color: #55606e");
        });

        this.scheduleBasePane = scheduleBasePane;
        scheduleBasePane.setPrefSize(200.0, 4800.0);
        scheduleBasePane.setStyle("-fx-background-color: #d2d4d8");
        scheduleBasePane.setOnMouseEntered(e -> scheduleBasePane.setStyle("-fx-background-color: pink"));
        scheduleBasePane.setOnMouseExited(e -> scheduleBasePane.setStyle("-fx-background-color: #d2d4d8"));
        //scheduleBasePane.getChildren().add(new Label("HELLO"));

        appointmentPaneHashMapSetup();


        System.out.println(new java.sql.Date(date.getTime()));

        namePaneSetup();
        schedulePanelSetup();

    }

    void namePaneSetup() {


        Label nameLabel = new Label(employee.getF_name() + " " + employee.getL_name());
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setTextFill(Color.WHITE);
        nameLabel.setFont(javafx.scene.text.Font.font(14.0));

        namePane.getChildren().add(nameLabel);
        AnchorPane.setBottomAnchor(nameLabel, 0.0);
        AnchorPane.setRightAnchor(nameLabel, 0.0);
        AnchorPane.setTopAnchor(nameLabel, 0.0);
        AnchorPane.setLeftAnchor(nameLabel, 0.0);


    }


    void schedulePanelSetup() {

        System.out.println("Employee: " + employee.getF_name());
        System.out.println("Date: " + date);

        List<Appointment> appointmentsForThisEmployeeOnThisDate = findApps(employee, date);


        System.out.println(date);
        double counter = 0;
        for (Appointment appointment : appointmentsForThisEmployeeOnThisDate) {
            //Label label = new Label("Hello" + counter);
            //scheduleBasePane.getChildren().add(label);
            //AnchorPane.setTopAnchor(label, counter);
            //counter += 200;

//
            AnchorPane appointmentAnchorPane = new AnchorPane();
            Client client = getClientForThisAppointment(appointment);
            String clientId = client.getClient_id();
            appointmentAnchorPane.setAccessibleText(clientId);
            String yourMom = appointmentAnchorPane.getAccessibleText();
            clients.add(client);

            Label clientNameLabel = new Label();
            Label servicesBeingProvidedLabel = new Label();
            Label startAndEndTimeLabel = new Label();

            clientNameLabel.setText(client.getF_name() + " " + client.getL_name());
            clientNameLabel.setTextFill(Color.WHITE);
            clientNameLabel.setFont(Font.font(14.0));
            clientNameLabel.setAlignment(Pos.CENTER);
            AnchorPane.setTopAnchor(clientNameLabel, 20.0);
            AnchorPane.setRightAnchor(clientNameLabel, 0.0);
            AnchorPane.setLeftAnchor(clientNameLabel, 0.0);

            StringBuilder builder = new StringBuilder();
//            for (Service service : appointment.getServices()) {
//                builder.append(service.getName() + "\n");
//            }

            for(int i = 0; i < appointment.getServices().size(); i++){

                if(appointment.getServices().size()-1 == i){
                    builder.append(appointment.getServices().get(i).getName());
                }else{
                    builder.append(appointment.getServices().get(i).getName() + ", ");
                }

            }

            servicesBeingProvidedLabel.setText(builder.toString());
            servicesBeingProvidedLabel.setTextFill(Color.WHITE);
            servicesBeingProvidedLabel.setFont(Font.font(14.0));
            servicesBeingProvidedLabel.setAlignment(Pos.CENTER);
            AnchorPane.setTopAnchor(servicesBeingProvidedLabel, 40.0);
            AnchorPane.setLeftAnchor(servicesBeingProvidedLabel, 0.0);
            AnchorPane.setRightAnchor(servicesBeingProvidedLabel, 0.0);

            startAndEndTimeLabel.setText(appointment.getStart_time() + " - " + appointment.getEnd_time());
            startAndEndTimeLabel.setTextFill(Color.WHITE);
            startAndEndTimeLabel.setFont(Font.font(14.0));
            startAndEndTimeLabel.setAlignment(Pos.CENTER);
            AnchorPane.setTopAnchor(startAndEndTimeLabel, 60.0);
            AnchorPane.setLeftAnchor(startAndEndTimeLabel, 0.0);
            AnchorPane.setRightAnchor(startAndEndTimeLabel, 0.0);


            appointmentAnchorPane.getChildren().add(clientNameLabel);


            appointmentAnchorPane.getChildren().add(servicesBeingProvidedLabel);


            appointmentAnchorPane.getChildren().add(startAndEndTimeLabel);


            AnchorPane.setRightAnchor(appointmentAnchorPane, 0.0);
            AnchorPane.setLeftAnchor(appointmentAnchorPane, 0.0);

//            System.out.println("Start Distance: " + startTimeDistance.get(appointment.getStart_time()));
            //System.out.println("End Distance: " + endTimeDistance.get(appointment.getEnd_time()));

            positionSetup();
            //startTimeDistance.get(appointment.getStart_time().toString())

            AnchorPane.setTopAnchor(appointmentAnchorPane, startTimeDistance.get(appointment.getStart_time().toString()));
            AnchorPane.setBottomAnchor(appointmentAnchorPane, endTimeDistance.get(appointment.getEnd_time().toString()));

            System.out.println("Base Pane Height: " + scheduleBasePane.getHeight());



            scheduleBasePane.getChildren().add(appointmentAnchorPane);

            appointmentAnchorPane.setStyle("-fx-background-color: #24afb2");
            //Handles
            appointmentAnchorPane.setOnMouseExited(e -> {
                appointmentAnchorPane.setStyle("-fx-background-color: #24afb2");
            });
            appointmentAnchorPane.setOnMouseEntered(e -> {
                appointmentAnchorPane.setStyle("-fx-background-color: #68a2ff");
                System.out.println(AnchorPane.getTopAnchor(appointmentAnchorPane));
            });

            appointmentAnchorPane.setOnMouseClicked(e -> {
                System.out.println("you clicked me: " + appointmentAnchorPane.toString());
                String accessibleText = appointmentAnchorPane.getAccessibleText();
                Client thisClient = new Queries().getClientInfoUsingID(appointmentAnchorPane.getAccessibleText());



                AddAppointmentWindowView addAppointmentWindowView = new AddAppointmentWindowView(employee, date);
                AddAppointmentWindowModel addAppointmentWindowModel = new AddAppointmentWindowModel();
                AddAppointmentWindowController addAppointmentWindowController = new AddAppointmentWindowController(addAppointmentWindowModel, addAppointmentWindowView, employee, thisClient);

                addAppointmentWindowView.getClientLabel().setText(thisClient.getF_name() + " " + thisClient.getL_name());
                addAppointmentWindowView.getPhoneNumberLabel().setText(String.valueOf(thisClient.getPhone()));
                addAppointmentWindowView.getClientNotesTextField().setText(thisClient.getColor_formula());
                addAppointmentWindowView.getStartTimeChoiceBox().getItems().clear();
                List<String> stringList = new ArrayList<>();
                stringList.add("8:00 AM");
                ObservableList<String> something = FXCollections.observableList(stringList);
                addAppointmentWindowView.getStartTimeChoiceBox().setItems(something);

                List<String> endList = new ArrayList<>();
                endList.add("8:30 AM");
                ObservableList<String> somethingElse = FXCollections.observableList(endList);
                addAppointmentWindowView.getEndTimeChoiceBox().getItems().clear();
                addAppointmentWindowView.getEndTimeChoiceBox().setItems(somethingElse);



            });

        }


    }


    void positionSetup(){

        double initialHeight = 48;
        double initialCounter = 0;
        double counter2 = 4800;
        startTimeDistance = new HashMap<>();
        endTimeDistance = new HashMap<>();

        String hours[] = {"24", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String minutes[] = {"00", "15", "30", "45"};

        for(int i = 0; i < hours.length; i++){
            for(int j = 0 ;  j < minutes.length; j++){

                startTimeDistance.put(hours[i]+":"+minutes[j]+":"+ "00" , initialCounter);
                endTimeDistance.put(hours[i]+":"+minutes[j]+":"+"00", counter2);
                initialCounter+=50;
                counter2-=50;

            }
        }

        scheduleBasePane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            }
        });

    }

/*The following method can be used to find all of the appointments for a
     *given employee and date. It takes the URL(directory) of the database as
	 *a parameter. It also takes an employee id and a date. The method is
	 * an Array List method and will return an ArrayList of appointment objects
	 * for the given date and employee.
	 */

    List<Appointment> findApps(Employee employee, Date date) {

        List<Appointment> appointments = new ArrayList<>();

        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url, "sa", "");
            Statement statement = conn.createStatement();

            String queryForThisEmployeeOnThisDate = "select * " +
                    "from appointments " +
                    "where appointments.date = '" + date.toString() + "' " +
                    "and appointments.employee_id = '" + employee.getEmployee_id() + "';";

            ResultSet resultSet1 = statement.executeQuery(queryForThisEmployeeOnThisDate);

            while (resultSet1.next()) {
                Appointment appointment = new Appointment();
                List<Service> services = new ArrayList<>();

                appointment.setAppt_id(resultSet1.getString("APPT_ID"));
                appointment.setClient_id(resultSet1.getString("CLIENT_ID"));
                appointment.setEmployee_id(resultSet1.getString("EMPLOYEE_ID"));
                appointment.setDate(resultSet1.getDate("DATE"));
                appointment.setStart_time(resultSet1.getTime("START_TIME"));
                appointment.setEnd_time(resultSet1.getTime("END_TIME"));
                appointment.setDuration(resultSet1.getInt("DURATION"));
                appointment.setNotes(resultSet1.getString("NOTES"));

                String queryForServicesFprThisAppointment = "select s.service_id, s.name, s.description, s.price " +
                        "from service s " +
                        "inner join appt_serv ass " +
                        "on s.service_id = ass.service_id " +
                        "inner join appointments a " +
                        "on a.appt_id = ass.appt_id " +
                        "where a.appt_id = '" + appointment.getAppt_id() + "';";

                Statement statement2 = conn.createStatement();

                ResultSet resultSet2 = statement2.executeQuery(queryForServicesFprThisAppointment);

                while (resultSet2.next()) {
                    Service service = new Service();
                    service.setService_id(resultSet2.getString("SERVICE_ID"));
                    service.setName(resultSet2.getString("NAME"));
                    service.setDescription(resultSet2.getString("DESCRIPTION"));
                    service.setPrice(resultSet2.getDouble("PRICE"));

                    services.add(service);
                }

                appointment.setServices(services);

                appointments.add(appointment);

            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return appointments;
    }

    Client getClientForThisAppointment(Appointment appointment) {
        Client client = new Client();

        try {

            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url, "sa", "");
            Statement statement = conn.createStatement();
            String query = "select *\n" +
                    "from client c\n" +
                    "where c.client_id = '" + appointment.getClient_id() + "';";

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

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    public void appointmentPaneHashMapSetup() {


    }


    public void handles() {


    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public List<AnchorPane> getAppointmentPanels() {
        return appointmentPanels;
    }

    public void setAppointmentPanels(List<AnchorPane> appointmentPanels) {
        this.appointmentPanels = appointmentPanels;
    }

    public AnchorPane getScheduleBasePane() {
        return scheduleBasePane;
    }

    public void setScheduleBasePane(AnchorPane scheduleBasePane) {
        this.scheduleBasePane = scheduleBasePane;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public AnchorPane getNamePane() {
        return namePane;
    }

    public void setNamePane(AnchorPane namePane) {
        this.namePane = namePane;
    }
}