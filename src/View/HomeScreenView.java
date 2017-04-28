package View;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sun.plugin.javascript.navig.Anchor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeScreenView {

    private AnchorPane root;
    private AnchorPane homePane;

    private Label greetingLabel;
    private Label appointmentsTodayLabel;
    private Label numberOfAppointmentsToday;
    private Label numberOfCompletedAppointmentsLabel;
    private Label numberOfRemainingAppointments;
    private Label appointmentsCompletedLabel;
    private Label appointmentsRemainingLabel;
    private Label nextAppointmentTimeLabel;
    private Label yourNextAppointmentLabel;
    private Button addApptButton;
    private Label yourNextBreak;
    private Label username;
    private Label clientNameAndTime;
    private Label timeOfBreakAndDuration;
    private String firstName;
    private Button refreshButton;

    public HomeScreenView(AnchorPane root, String firstName){
        this.root = root;
        this.firstName = firstName;
        homePane = new AnchorPane();
        homePane.setStyle("-fx-background-color: #000000");

        numberOfAppointmentsToday = new Label("4");
        numberOfAppointmentsToday.setFont(Font.font(14.0));
        numberOfCompletedAppointmentsLabel = new Label("0");
        numberOfCompletedAppointmentsLabel.setFont(Font.font(14.0));
        numberOfRemainingAppointments = new Label("4");
        numberOfRemainingAppointments.setFont(Font.font(14.0));
        nextAppointmentTimeLabel = new Label("6:30 PM");
        nextAppointmentTimeLabel.setFont(Font.font(14.0));

        greetingLabel = new Label(randomGreeting(firstName));
        greetingLabel.setFont(Font.font(50.0));
        AnchorPane.setTopAnchor(greetingLabel, 20.0);
        AnchorPane.setRightAnchor(greetingLabel, 0.0);
        AnchorPane.setLeftAnchor(greetingLabel, 0.0);
        greetingLabel.setAlignment(Pos.CENTER);

        appointmentsTodayLabel = new Label("Appointments Today: " + numberOfAppointmentsToday.getText());
        appointmentsTodayLabel.setFont(Font.font(14.0));
        AnchorPane.setTopAnchor(appointmentsTodayLabel, 120.0);
        AnchorPane.setLeftAnchor(appointmentsTodayLabel, 30.0);

        appointmentsCompletedLabel = new Label("Number Of Completed Appointments: "+ numberOfCompletedAppointmentsLabel.getText());
        appointmentsCompletedLabel.setFont(Font.font(14.0));
        AnchorPane.setTopAnchor(appointmentsCompletedLabel, 150.0);
        AnchorPane.setLeftAnchor(appointmentsCompletedLabel, 30.0);

        appointmentsRemainingLabel = new Label("Number Of Remaining Appointments: " + numberOfRemainingAppointments.getText());
        appointmentsRemainingLabel.setFont(Font.font(14.0));
        AnchorPane.setTopAnchor(appointmentsRemainingLabel, 180.0);
        AnchorPane.setLeftAnchor(appointmentsRemainingLabel, 30.0);

        yourNextAppointmentLabel = new Label("Your Next Appointment Is At: " + nextAppointmentTimeLabel.getText());
        yourNextAppointmentLabel.setFont(Font.font(14.0));
        AnchorPane.setTopAnchor(yourNextAppointmentLabel, 210.0);
        AnchorPane.setLeftAnchor(yourNextAppointmentLabel, 30.0);


        root.getChildren().addAll(greetingLabel, appointmentsTodayLabel, appointmentsCompletedLabel, appointmentsRemainingLabel, yourNextAppointmentLabel);
        root.getChildren().add(homePane);

    }

    String randomGreeting(String name){
        List<String> randomGreeting = new ArrayList<>();
        randomGreeting.add("Suh, " + name + "?");
        randomGreeting.add("Welcome back, " + name);
        randomGreeting.add("Lookin' good, " + name);
        randomGreeting.add("Stylin' hairdo! " + name);

        Random random = new Random();
        String randomG = randomGreeting.get(random.nextInt(randomGreeting.size()));
        return randomG;
    }


    public Label getGreetingLabel() {
        return greetingLabel;
    }

    public Label getAppointmentsTodayLabel() {
        return appointmentsTodayLabel;
    }

    public Label getAppointmentsCompletedLabel() {
        return appointmentsCompletedLabel;
    }

    public Label getAppointmentsRemainingLabel() {
        return appointmentsRemainingLabel;
    }

    public Label getYourNextAppointmentLabel() {
        return yourNextAppointmentLabel;
    }

    public void setGreetingLabel(Label greetingLabel) {
        this.greetingLabel = greetingLabel;
    }

    public void setAppointmentsTodayLabel(Label appointmentsTodayLabel) {
        this.appointmentsTodayLabel = appointmentsTodayLabel;
    }

    public void setAppointmentsCompletedLabel(Label appointmentsCompletedLabel) {
        this.appointmentsCompletedLabel = appointmentsCompletedLabel;
    }

    public void setAppointmentsRemainingLabel(Label appointmentsRemainingLabel) {
        this.appointmentsRemainingLabel = appointmentsRemainingLabel;
    }

    public void setYourNextAppointmentLabel(Label yourNextAppointmentLabel) {
        this.yourNextAppointmentLabel = yourNextAppointmentLabel;
    }

    public Label getNumberOfAppointmentsToday() {
        return numberOfAppointmentsToday;
    }

    public void setNumberOfAppointmentsToday(Label numberOfAppointmentsToday) {
        this.numberOfAppointmentsToday = numberOfAppointmentsToday;
    }

    public Label getNumberOfCompletedAppointmentsLabel() {
        return numberOfCompletedAppointmentsLabel;
    }

    public void setNumberOfCompletedAppointmentsLabel(Label numberOfCompletedAppointmentsLabel) {
        this.numberOfCompletedAppointmentsLabel = numberOfCompletedAppointmentsLabel;
    }

    public Label getNumberOfRemainingAppointments() {
        return numberOfRemainingAppointments;
    }

    public void setNumberOfRemainingAppointments(Label numberOfRemainingAppointments) {
        this.numberOfRemainingAppointments = numberOfRemainingAppointments;
    }

    public Label getNextAppointmentTimeLabel() {
        return nextAppointmentTimeLabel;
    }

    public void setNextAppointmentTimeLabel(Label nextAppointmentTimeLabel) {
        this.nextAppointmentTimeLabel = nextAppointmentTimeLabel;
    }

    public AnchorPane getRoot() {
        return root;
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }
}
