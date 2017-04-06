package View;

import MiscObjects.Appointment;
import MiscObjects.Employee;
import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Brannon on 4/3/2017.
 */
public class AddAppointmentWindowView {

    private Stage stage;


    private VBox vBox;

    private Date date;
    private Employee employee;

    private BorderPane basePane;

    private AnchorPane topPane;
    private Label topPaneLabel;

    private AnchorPane appointmentPane;
    private Label clientLabel;
    private Label phoneNumberLabel;
    private Button selectClientButton;

    private Label serviceLabel;
    private ScrollPane servicesScrollPane;
    private List<String> servicesList;

    private Label startTimeLabel;
    private ChoiceBox<String> startTimeChoiceBox;
    private Label endTimeLabel;
    private JFXCheckBox endTimeChoiceBox;

    private CheckBox recurring;

    private ToggleGroup daysOfTheWeekGroup;
    private ToggleButton sundayToggleButton;
    private ToggleButton mondayToggleButton;
    private ToggleButton tuesdayToggleButotn;
    private ToggleButton wednesdayToggleButton;
    private ToggleButton thursdayToggleButton;
    private ToggleButton fridayToggleButton;
    private ToggleButton saturdayToggleButton;

    private Label clientNotesLabel;
    private TextArea clientNotesTextField;
    private Label appointmentNotesLabel;
    private TextArea appointmentNotesTextField;

    private Button confirm;
    private Button cancel;

    private String stylinTheme = "css/StylinThemeCalmPro.css";


    public AddAppointmentWindowView(Employee employee, Date date) {
        stage = new Stage();
        stage.setResizable(false);

        this.employee = employee; //employee selected from scheduling pane;

        basePane = new BorderPane();

        topPaneSetup();
        appointmentPaneSetup();


        Scene scene = new Scene(basePane, 800, 600);
        scene.getStylesheets().add(stylinTheme);
        stage.setScene(scene);
        stage.show();

    }

    public void appointmentPaneSetup() {

        appointmentPane = new AnchorPane();

        clientLabel = new Label("Anderson Cooper");
        clientLabel.setFont(Font.font(24.0));
        appointmentPane.getChildren().add(clientLabel);
        AnchorPane.setTopAnchor(clientLabel, 5.0);
        AnchorPane.setLeftAnchor(clientLabel, 5.0);

        phoneNumberLabel = new Label();
        phoneNumberLabel.setText("Contact Number: " + employee.getPhone());
        phoneNumberLabel.setFont(Font.font(14.0));
        appointmentPane.getChildren().add(phoneNumberLabel);
        AnchorPane.setLeftAnchor(phoneNumberLabel, 5.0);
        AnchorPane.setTopAnchor(phoneNumberLabel, 35.0);

        selectClientButton = new Button();
        selectClientButton.setText("Change");
        selectClientButton.setPrefWidth(180);
        selectClientButton.setPrefHeight(30);
        selectClientButton.setFont(Font.font(14));
        appointmentPane.getChildren().add(selectClientButton);
        AnchorPane.setLeftAnchor(selectClientButton, 5.0);
        AnchorPane.setTopAnchor(selectClientButton, 55.0);

        serviceLabel = new Label("Services");
        serviceLabel.setFont(Font.font(24.0));
        appointmentPane.getChildren().add(serviceLabel);
        AnchorPane.setLeftAnchor(serviceLabel, 5.0);
        AnchorPane.setTopAnchor(serviceLabel, 100.0);

        servicesScrollPane = new ScrollPane();

        servicesScrollPane.setContent(vBox);

        appointmentPane.setStyle("-fx-background-color: #e9e9e9");

        appointmentPane.getChildren().add(servicesScrollPane);
        AnchorPane.setBottomAnchor(servicesScrollPane, 0.0);
        AnchorPane.setLeftAnchor(servicesScrollPane, 0.0);
        AnchorPane.setTopAnchor(servicesScrollPane, 130.0);
        AnchorPane.setRightAnchor(servicesScrollPane, 420.0);

        clientNotesLabel = new Label("Client Notes");
        clientNotesLabel.setFont(Font.font(24.0));
        appointmentPane.getChildren().add(clientNotesLabel);
        AnchorPane.setLeftAnchor(clientNotesLabel, 420.0);
        AnchorPane.setTopAnchor(clientNotesLabel, 100.0);

        clientNotesTextField = new TextArea();
        clientNotesTextField.setPromptText("Enter client notes here");
        appointmentPane.getChildren().add(clientNotesTextField);
        AnchorPane.setTopAnchor(clientNotesTextField, 130.0);
        AnchorPane.setBottomAnchor(clientNotesTextField, 260.0);
        AnchorPane.setRightAnchor(clientNotesTextField, 10.0);
        AnchorPane.setLeftAnchor(clientNotesTextField, 420.0);

        appointmentNotesLabel = new Label("Appointment Notes");
        appointmentNotesLabel.setFont(Font.font(24.0));
        appointmentPane.getChildren().add(appointmentNotesLabel);
        AnchorPane.setTopAnchor(appointmentNotesLabel, 320.0);
        AnchorPane.setLeftAnchor(appointmentNotesLabel, 420.0);

        appointmentNotesTextField = new TextArea();
        appointmentNotesTextField.setPromptText("Enter appointment notes here");
        appointmentPane.getChildren().add(appointmentNotesTextField);
        AnchorPane.setTopAnchor(appointmentNotesTextField, 350.0);
        AnchorPane.setBottomAnchor(appointmentNotesTextField, 30.0);
        AnchorPane.setRightAnchor(appointmentNotesTextField, 10.0);
        AnchorPane.setLeftAnchor(appointmentNotesTextField, 420.0);

        choiceBoxSetup();


        basePane.setCenter(appointmentPane);
    }

    public void setUpServicesList(List<String> services){
        vBox = new VBox();

        for(String service : services){
            JFXCheckBox checkBox = new JFXCheckBox(service);
            checkBox.setCheckedColor(Color.rgb(36, 175, 178));
            checkBox.setFont(Font.font(14.0));
            vBox.getChildren().add(checkBox);
            System.out.println(service);
            VBox.setVgrow(servicesScrollPane, Priority.ALWAYS);
        }
    }

    public void choiceBoxSetup(){

        startTimeLabel = new Label("Start Time");
        appointmentPane.getChildren().add(startTimeLabel);
        AnchorPane.setTopAnchor(startTimeLabel, 10.0);
        AnchorPane.setLeftAnchor(startTimeLabel, 420.0);


        startTimeChoiceBox = new ChoiceBox<String>(
                FXCollections.observableArrayList("8:15am", "b", "c", "d", "e"));
        Label label = new Label();
        String[] greetings = new String[] { "A", "B", "C", "D", "E" };
        startTimeChoiceBox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                        label.setText(greetings[new_value.intValue()]);
                    }
                });

        appointmentPane.getChildren().add(startTimeChoiceBox);
        AnchorPane.setTopAnchor(startTimeChoiceBox, 30.0);
        AnchorPane.setLeftAnchor(startTimeChoiceBox, 420.0);
        AnchorPane.setRightAnchor(startTimeChoiceBox, 230.0);

        endTimeLabel = new Label("End Time");
        appointmentPane.getChildren().add(endTimeLabel);
        AnchorPane.setTopAnchor(endTimeLabel, 10.0);
        AnchorPane.setLeftAnchor(endTimeLabel, 620.0);


        startTimeChoiceBox = new ChoiceBox<String>(
                FXCollections.observableArrayList("10:15am", "b", "c", "d", "e"));
        Label label2 = new Label();
        String[] greetings2 = new String[] { "A", "B", "C", "D", "E" };
        startTimeChoiceBox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                        label2.setText(greetings2[new_value.intValue()]);
                    }
                });

        appointmentPane.getChildren().add(startTimeChoiceBox);
        AnchorPane.setTopAnchor(startTimeChoiceBox, 30.0);
        AnchorPane.setLeftAnchor(startTimeChoiceBox, 620.0);
        AnchorPane.setRightAnchor(startTimeChoiceBox, 30.0);


    }

    public void topPaneSetup() {
        topPane = new AnchorPane();
        topPane.setStyle("-fx-background-color: pink");
        topPaneLabel = new Label();
        topPaneLabel.setText("Scheduling with " + employee.getF_name() + " " + employee.getL_name() + " on " + "4/2/2017");
        topPaneLabel.setFont(Font.font(20.0));
        topPane.getChildren().add(topPaneLabel);

        AnchorPane.setLeftAnchor(topPaneLabel, 20.0);
        AnchorPane.setTopAnchor(topPaneLabel, 0.0);
        AnchorPane.setBottomAnchor(topPaneLabel, 0.0);


        basePane.setTop(topPane);
    }

}
