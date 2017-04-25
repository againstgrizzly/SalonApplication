package View;

import MiscObjects.Appointment;
import MiscObjects.Employee;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import jfxtras.scene.control.LocalDatePicker;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SchedulingScreenView {

    private AnchorPane rootPane;
    private List<Employee> employees;
    private Slider verticalSlider;
    private Slider horizontalSlider;
    private LocalDatePicker localDatePicker;
    private ListView<String> timesList;

    //Components for Scheduling ScreenView
    AnchorPane basePane; //left is time, right is scheduling

    AnchorPane timesPane;
    AnchorPane timeLabelPane; //This is where the time label will go
    AnchorPane allTheTimesPane; //This will have the times of the day

    Date todaysDate;
    List<EmployeeScheduleView> employeeScheduleViews;
    List<Appointment> appointmentsForTheCurrentlySelectedDate;

    BorderPane schedulingBorderPane;
    HBox namesHeaderPane; //This is where all the names of people will go
    HBox schedulingContainmentPane; //This is there all the schedules will be listed

    //ScrollPane timeScrollPane;
    ScrollPane schedulingScrollPane;


    public SchedulingScreenView(AnchorPane rootPane, Slider verticalSlider, Slider horizontalSlider, LocalDatePicker datePicker) {
        this.rootPane = rootPane;
        this.verticalSlider = verticalSlider;
        this.horizontalSlider = horizontalSlider;
        localDatePicker = datePicker;

        basePane = new AnchorPane();
        basePane.setStyle("-fx-background-color: green");


        AnchorPane.setTopAnchor(basePane, 0.0);
        AnchorPane.setBottomAnchor(basePane, 0.0);
        AnchorPane.setLeftAnchor(basePane, 0.0);
        AnchorPane.setRightAnchor(basePane, 0.0);

        timeBorderPaneSetup(basePane);
        schedulingBorderPaneSetup(basePane); //Creates the scheduling area of the scheduling view and adds it
        // to the right side of the basePane border
        /*
        Creates the time area of the scheduling view and adds to it
        the left side of the basePane border
         */

//        timeScrollPane.setPannable(false);
//        //Link the scroll bars
//        schedulingScrollPane.hvalueProperty().addListener(e -> {
//            System.out.println("TRIGGERED");
//            timeScrollPane.setHvalue(schedulingScrollPane.getHvalue());
//        });
            rootPane.getChildren().add(basePane);

//        timeScrollPane.setOnMouseEntered(e -> System.out.println("ENTERED"));
//        schedulingScrollPane.addEventFilter(ScrollEvent.ANY, e ->{
//
//            timeScrollPane.setVvalue(schedulingScrollPane.getVvalue());
//
//        });


    }


    public void schedulingBorderPaneSetup(AnchorPane basePane) {

        AnchorPane rightAnchorPane = new AnchorPane();
        rightAnchorPane.setStyle("-fx-background-color: pink");
        AnchorPane.setTopAnchor(rightAnchorPane, 0.0);
        AnchorPane.setLeftAnchor(rightAnchorPane, 0.0);
        AnchorPane.setRightAnchor(rightAnchorPane, 0.0);
        AnchorPane.setBottomAnchor(rightAnchorPane, 0.0);

        ScrollPane nameScrollPane = new ScrollPane();
        nameScrollPane.setPrefHeight(30.0);
        nameScrollPane.setStyle("-fx-background-color: red"); //Actual Color : #55606e
        AnchorPane.setTopAnchor(nameScrollPane, 0.0);
        AnchorPane.setLeftAnchor(nameScrollPane, 0.0);
        AnchorPane.setRightAnchor(nameScrollPane, 0.0);

        schedulingScrollPane = new ScrollPane();
        schedulingScrollPane.setStyle("-fx-background-color:blue"); //Actual Color: #55606e
        AnchorPane.setTopAnchor(schedulingScrollPane, 30.0);
        AnchorPane.setLeftAnchor(schedulingScrollPane, 0.0);
        AnchorPane.setRightAnchor(schedulingScrollPane, 0.0);
        AnchorPane.setBottomAnchor(schedulingScrollPane, 0.0);

        namesHeaderPane = new HBox();
        schedulingContainmentPane = new HBox();
        schedulingContainmentPane.setStyle("-fx-background-color: cyan");
        schedulingContainmentPane.setPrefHeight(4800.0);

        namesHeaderPane.getChildren().add(timeLabelPane);
        schedulingContainmentPane.getChildren().add(allTheTimesPane);

        nameScrollPane.setContent(namesHeaderPane);
        schedulingScrollPane.setContent(schedulingContainmentPane);


        rightAnchorPane.getChildren().add(nameScrollPane);
        rightAnchorPane.getChildren().add(schedulingScrollPane);

        basePane.getChildren().add(rightAnchorPane);



    }

    public void populateTodaysAppointmentsIntoSchedulingContentPane(List<Appointment> appointmentsForTheCurrentlySelectedDate) {

        if(namesHeaderPane.getChildren() != null && schedulingContainmentPane.getChildren() != null){
            namesHeaderPane.getChildren().clear();
            schedulingContainmentPane.getChildren().clear();
        }


        LocalDate local = localDatePicker.getLocalDate();

        java.sql.Date date = java.sql.Date.valueOf(local);

        employeeScheduleViews = new ArrayList<>();

        namesHeaderPane.getChildren().add(timeLabelPane);
        schedulingContainmentPane.getChildren().add(allTheTimesPane);

        for (Employee employee : employees) {

            EmployeeScheduleView employeeScheduleView = new EmployeeScheduleView(employee, date);

            AnchorPane namePane = employeeScheduleView.getNameAnchorPane();
            AnchorPane schedulePane = employeeScheduleView.getScheduleBasePane();

            namesHeaderPane.getChildren().add(namePane);
            AnchorPane.setBottomAnchor(namePane, 0.0);
            AnchorPane.setTopAnchor(namePane, 0.0);

            schedulingContainmentPane.getChildren().add(schedulePane);

            AnchorPane.setBottomAnchor(schedulePane, 0.0);
            AnchorPane.setTopAnchor(schedulePane, 0.0);

            employeeScheduleViews.add(employeeScheduleView);

        }

    }


    public void timeBorderPaneSetup(AnchorPane basePane) {


        Label label1 = new Label("Time");
        label1.setFont(Font.font(14.0));
        label1.setTextFill(Color.WHITE);
        label1.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(label1, 0.0);
        AnchorPane.setBottomAnchor(label1, 0.0);
        AnchorPane.setLeftAnchor(label1, 0.0);
        AnchorPane.setRightAnchor(label1, 0.0);

        timeLabelPane = new AnchorPane(label1);
        AnchorPane.setTopAnchor(label1, 0.0);
        AnchorPane.setLeftAnchor(label1, 0.0);
        timeLabelPane.setStyle("-fx-background-color: #55606e");
        timeLabelPane.setPrefSize(100.0, 30.0);
        timeLabelPane.setOnMouseEntered(e -> {
            timeLabelPane.setStyle("-fx-background-color: #343a41");
        });
        timeLabelPane.setOnMouseExited(e -> {
            timeLabelPane.setStyle("-fx-background-color: #55606e");
        });

//        timeScrollPane = new ScrollPane();
//        AnchorPane.setTopAnchor(timeScrollPane, 30.0);
//        AnchorPane.setLeftAnchor(timeScrollPane, 0.0);
//        AnchorPane.setBottomAnchor(timeScrollPane, 0.0);
//        timeScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        timeScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        allTheTimesPane = new AnchorPane();
        allTheTimesPane.setPrefWidth(100.0);
        insertTimesAndLogin(allTheTimesPane);

        //timeScrollPane.setContent(allTheTimesPane);
        System.out.println("All Pane Height: " + allTheTimesPane.getHeight());

        //basePane.getChildren().add(timeScrollPane);
        //basePane.getChildren().add(timeLabelPane);
    }

    public void insertTimesAndLogin(AnchorPane allTheTimesPane) {

        //There are 1440 minutes in a day
        int numberOfFifteenIncrementsInADay = 96;

        List<Label> labelList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();

        for (int i = 0; i < numberOfFifteenIncrementsInADay; i++) {
            Label label = new Label();
            label.setAlignment(Pos.CENTER);
            label.setTextFill(Color.WHITE);
            labelList.add(label);
        }

        String hours[] = {"24", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23"};

        String minutes[] = {"00", "15", "30", "45"};

        for (int i = 0; i < hours.length; i++) {

            String hour = hours[i];

            for (int j = 0; j < minutes.length; j++) {
                String minute = minutes[j];
                String time = hour + ":" + minute;
                timeList.add(time);
            }
        }

        for (int i = 0; i < labelList.size(); i++) {

            labelList.get(i).setText(timeList.get(i));

        }

        double sizeOfPanel = 4800;
        double incrementSize = 50;
        double position = 0;

        for (int i = 0; i < labelList.size(); i++) {


            AnchorPane pane = new AnchorPane();
            pane.setPrefHeight(50.0);
            pane.setStyle("-fx-background-color: #55606e");
            pane.setOnMouseEntered(e -> {
                pane.setStyle("-fx-background-color: #343a41");
                System.out.println(AnchorPane.getTopAnchor(pane));
            });
            pane.setOnMouseExited(e -> {
                pane.setStyle("-fx-background-color: #55606e");
            });


            AnchorPane.setBottomAnchor(labelList.get(i), 0.0);
            AnchorPane.setTopAnchor(labelList.get(i), 0.0);
            AnchorPane.setRightAnchor(labelList.get(i), 0.0);
            AnchorPane.setLeftAnchor(labelList.get(i), 0.0);
            pane.getChildren().add(labelList.get(i));


            AnchorPane.setTopAnchor(pane, position);
            AnchorPane.setRightAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);

            allTheTimesPane.getChildren().add(pane);
            position += incrementSize;

        }

        System.out.println(position);


    }

    public AnchorPane getRootPane() {
        return rootPane;
    }

    public void setRootPane(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Slider getVerticalSlider() {
        return verticalSlider;
    }

    public void setVerticalSlider(Slider verticalSlider) {
        this.verticalSlider = verticalSlider;
    }

    public Slider getHorizontalSlider() {
        return horizontalSlider;
    }

    public void setHorizontalSlider(Slider horizontalSlider) {
        this.horizontalSlider = horizontalSlider;
    }

    public LocalDatePicker getLocalDatePicker() {
        return localDatePicker;
    }

    public void setLocalDatePicker(LocalDatePicker localDatePicker) {
        this.localDatePicker = localDatePicker;
    }

    public ListView<String> getTimesList() {
        return timesList;
    }

    public void setTimesList(ListView<String> timesList) {
        this.timesList = timesList;
    }
}