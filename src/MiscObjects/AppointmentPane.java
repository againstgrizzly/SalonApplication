package MiscObjects;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.sql.*;

class AppointmentPane {

    Label clientNameLabel;
    Label timeSpanLabel;
    Label serviceLabel;
    Appointment appointment;
    Client client;
    AnchorPane anchorPane;
    Time startTime;
    int startTimeInMinutes;
    int endTimeInMinutes;
    String clientName;


    public AppointmentPane(Appointment appointment, Client client) {
        this.appointment = appointment;
        this.client = client;
        //Determine the start and end time of the appointment in minutes
        //index 0 is start index 1 is end


        //Get client so we can set the name
        clientNameLabel = new Label();
        clientName = client.getF_name() + " " + client.getL_name();

        //Setup timespan label
        String timeSpan = formatTimeSpan(appointment);
        timeSpanLabel = new Label(timeSpan);

        //Setup service label
        serviceLabel = new Label();
        //serviceLabel.setText(appointment.get);


        anchorPane = new AnchorPane();


        handles();

    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void handles() {

        anchorPane.setOnMouseClicked(e -> {
            System.out.println(appointment.getAppt_id());

        });

    }

    public int timeToMinutes(Time time) {

        int hours = time.getHours();
        int minutes = time.getMinutes();

        int totalNumberOfMinutes = (time.getHours() * 60) + minutes;
        return totalNumberOfMinutes;
    }

    public String formatTimeSpan(Appointment appointment){
        String startTime = appointment.getStart_time().toString().substring(0, 5);//cut off seconds
        String endTime = appointment.getEnd_time().toString().substring(0, 5);

        String timeSpan = startTime + "-" + endTimeInMinutes;
        return timeSpan;
    }


}