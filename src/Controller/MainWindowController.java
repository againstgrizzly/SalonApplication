package Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import jfxtras.scene.control.LocalDatePicker;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {

    @FXML
    Button sideBarButton;
    @FXML
    LocalDatePicker datePicker;
    @FXML
    AnchorPane sideBarAnchorPane;
    @FXML
    AnchorPane mainWindowPane;
    @FXML
    SplitPane splitPane;
    @FXML
    Button homeButton;
    @FXML
    Button schedulingButton;
    @FXML
    Button clientButton;
    @FXML
    Button settingsButton;
    @FXML
    Tab homeTab;
    @FXML
    Tab schedulingTab;
    @FXML
    TabPane mainWindowTabPane;
    @FXML
    AnchorPane schedulingTabRootPane;

    @FXML
    private Label nameLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label timeLabel;

    private int hour;
    private int minute;
    private int second; //only used for testing so we don't have to wait;
    private int am_pm;

    @FXML private SchedulingTabController schedulingTabController;

    private boolean running = false;

    private String name = "Welcome, Captain Falcon";
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    Date date = new Date();

    private boolean isOpen = false;

    public MainWindowController() {
        System.out.println("Default Constructor Called");
    }

    public MainWindowController(String name) {
        this.name = name;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/schedulingTab.fxml"));
            AnchorPane schedulingPane = loader.load();
            schedulingTabRootPane.getChildren().addAll(schedulingPane);
            AnchorPane.setBottomAnchor(schedulingPane, 0.0);
            AnchorPane.setTopAnchor(schedulingPane, 0.0);
            AnchorPane.setLeftAnchor(schedulingPane, 0.0);
            AnchorPane.setRightAnchor(schedulingPane, 0.0);
        }catch(Exception e){
            e.printStackTrace();
        }
        //a temporary place to put stuff until i get organized
        aPlaceToPutStuffForNow();

        setNameDateTimeLabel();

        //Actions for the 4 main Buttons;
        buttonActions();


    }

    @FXML
    void OnActionSideBarButton(ActionEvent event) {
        if (isOpen) {
            System.out.println("Close");
            datePicker.setVisible(false);
            splitPane.setDividerPosition(0, 0.0389);
            isOpen = false;

        } else {
            System.out.println("Open");
            datePicker.setVisible(true);
            splitPane.setDividerPosition(0, 0.2001);
            isOpen = true;
        }

    }

    public void buttonActions() {

        homeButton.setOnMouseClicked(e -> {
            System.out.println("Home Button Clicked");
            mainWindowTabPane.getSelectionModel().select(homeTab);

        });

        schedulingButton.setOnMouseClicked(e -> {
            System.out.println("Scheduling Button Clicked");
            mainWindowTabPane.getSelectionModel().select(schedulingTab);

        });

        clientButton.setOnMouseClicked(e -> {
            System.out.println("Client Button Clicked");

        });

        settingsButton.setOnMouseClicked(e -> {
            System.out.println("Settings Button Clicked");

        });

    }

    public void aPlaceToPutStuffForNow() {
        datePicker.setVisible(false);
        splitPane.setDividerPositions(0.0389);
        SplitPane.Divider divider = splitPane.getDividers().get(0);
        divider.positionProperty().addListener(e -> {

            if (divider.getPosition() < 0.10) {
                datePicker.setVisible(false);
                isOpen = false;
            } else {
                datePicker.setVisible(true);
                isOpen = true;
            }

        });
    }


    public void setNameDateTimeLabel() {
        nameLabel.setText(name);
        dateLabel.setText(dateFormat.format(date));
        TimelineBuilder.create()
                .cycleCount(Timeline.INDEFINITE)
                .keyFrames(new KeyFrame(Duration.seconds(1), updateTime()))
                .build()
                .play();
    }

    //Hands time update for the clock
    private EventHandler updateTime() {
        return new EventHandler() {
            @Override
            public void handle(Event event) {
                Calendar calendar = Calendar.getInstance();

                //SECOND//////////////////////////////////////
                //A correction for when the second isn't 2 digits is implemented
                second = calendar.get(Calendar.SECOND);
                String secondsCorrection;
                if(second < 10) secondsCorrection = "0" + second;
                else secondsCorrection = String.valueOf(second);
                /////////////////////////////////////////////////////////////////

                //MINUTE//////////////////////////////////////
                //A correction for when the minute isn't 2 digits is implemented
                minute = calendar.get(Calendar.MINUTE);
                String minuteCorrection;
                if(minute < 10) minuteCorrection = "0" + minute;
                else minuteCorrection = String.valueOf(minute);
                ////////////////////////////////////////////


                hour = calendar.get(Calendar.HOUR);

                //AM and PM correction
                am_pm = calendar.get(Calendar.AM_PM);
                String am_pm_correction;
                if(am_pm == 0) am_pm_correction = "AM";
                else am_pm_correction = "PM";


                timeLabel.setText(hour + ":" + minuteCorrection + ":" + secondsCorrection + am_pm_correction);
            }
        };
    }


}
