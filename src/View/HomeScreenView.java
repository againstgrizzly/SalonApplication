package View;


import javafx.geometry.Pos;
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
    private Label appointmentsCompletedLabel;
    private Label appointmentsRemainingLabel;
    private Label yourNextAppointmentLabel;
    private Label yourNextBreak;
    private Label username;
    private Label numberOfAppointmentsToday;
    private Label numberOfRemainingAppointments;
    private Label clientNameAndTime;
    private Label timeOfBreakAndDuration;
    private String firstName;

    public HomeScreenView(AnchorPane root, String firstName){
        this.root = root;
        this.firstName = firstName;
        homePane = new AnchorPane();
        homePane.setStyle("-fx-background-color: #000000");

        greetingLabel = new Label(randomGreeting(firstName));
        greetingLabel.setFont(Font.font(50.0));
        root.getChildren().add(greetingLabel);
        AnchorPane.setTopAnchor(greetingLabel, 50.0);
        AnchorPane.setRightAnchor(greetingLabel, 0.0);
        AnchorPane.setLeftAnchor(greetingLabel, 0.0);
        greetingLabel.setAlignment(Pos.CENTER);

        appointmentsTodayLabel = new Label("Appointments Today:");
        appointmentsTodayLabel.setFont(Font.font(14.0));
        root.getChildren().add(appointmentsTodayLabel);





        root.getChildren().add(homePane);


    }

    String randomGreeting(String name){
        List<String> randomGreeting = new ArrayList<>();
        randomGreeting.add("Suh, " + name + "?");
        randomGreeting.add("Welcome back, " + name);
        randomGreeting.add("Lookin' good, " + name);

        Random random = new Random();
        String randomG = randomGreeting.get(random.nextInt(randomGreeting.size()));
        return randomG;
    }





}
