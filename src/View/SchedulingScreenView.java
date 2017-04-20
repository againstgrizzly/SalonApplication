package View;

import MiscObjects.Employee;
import MiscObjects.Queries;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import jfxtras.scene.control.LocalDatePicker;
import sun.plugin.javascript.navig.Anchor;

import java.sql.Date;
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

    BorderPane schedulingBorderPane;
    HBox namesHeaderPane; //This is where all the names of people will go
    HBox schedulingContainmentPane; //This is there all the schedules will be listed

    ScrollPane scrollSchedulingContaintmentPane; //This will hold the scheduling containment pane to scroll in
    ScrollPane scrollAllTimesPane; //This will hold the allTimesPane to scroll in

    public SchedulingScreenView(AnchorPane rootPane, Slider verticalSlider, Slider horizontalSlider, LocalDatePicker datePicker) {
        this.rootPane = rootPane;
        this.verticalSlider = verticalSlider;
        this.horizontalSlider = horizontalSlider;
        localDatePicker = datePicker;

        basePane = new AnchorPane();
        basePane.setStyle("-fx-background-color: blue");
        basePane.setOnMouseClicked(e -> {System.out.println("Clicked Me");});
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



        rootPane.getChildren().add(basePane);


    }




    public void schedulingBorderPaneSetup(AnchorPane basePane) {

        AnchorPane rightAnchorPane = new AnchorPane();
        rightAnchorPane.setStyle("-fx-background-color: red");
        AnchorPane.setTopAnchor(rightAnchorPane, 0.0);
        AnchorPane.setLeftAnchor(rightAnchorPane, 100.0);
        AnchorPane.setRightAnchor(rightAnchorPane, 0.0);
        AnchorPane.setBottomAnchor(rightAnchorPane, 0.0);

        ScrollPane nameScrollPane = new ScrollPane();
        nameScrollPane.setPrefHeight(30.0);
        nameScrollPane.setStyle("-fx-background-color: #55606e");
        AnchorPane.setTopAnchor(nameScrollPane, 0.0);
        AnchorPane.setLeftAnchor(nameScrollPane, 0.0);
        AnchorPane.setRightAnchor(nameScrollPane, 0.0);

        ScrollPane schedulingContentPane = new ScrollPane();
        AnchorPane.setTopAnchor(schedulingContentPane, 30.0);
        AnchorPane.setLeftAnchor(schedulingContentPane, 0.0);
        AnchorPane.setRightAnchor(schedulingContentPane, 0.0);
        AnchorPane.setBottomAnchor(schedulingContentPane, 0.0);

        rightAnchorPane.getChildren().addAll(nameScrollPane, schedulingContentPane);

        basePane.getChildren().add(rightAnchorPane);

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
        timeLabelPane.setOnMouseEntered(e -> {timeLabelPane.setStyle("-fx-background-color: #343a41");});
        timeLabelPane.setOnMouseExited(e -> {timeLabelPane.setStyle("-fx-background-color: #55606e");});

        ScrollPane myScrollPane = new ScrollPane();
        AnchorPane.setTopAnchor(myScrollPane, 30.0);
        AnchorPane.setLeftAnchor(myScrollPane, 0.0);
        AnchorPane.setBottomAnchor(myScrollPane, 0.0);
        myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        allTheTimesPane = new AnchorPane();
        allTheTimesPane.setPrefWidth(100.0);
        insertTimesAndLogin(allTheTimesPane);

        myScrollPane.setContent(allTheTimesPane);





        basePane.getChildren().add(myScrollPane);
        basePane.getChildren().add(timeLabelPane);
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

        String hours[] = {"24" ,"01", "02", "03", "04", "05", "06", "07", "08", "09",
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

        double sizeOfPanel = 1440;
        double incrementSize = 15;
        double position = 0;

        for (int i = 0; i < labelList.size(); i++) {


            AnchorPane pane = new AnchorPane();
            pane.setStyle("-fx-background-color: #55606e");
            pane.setOnMouseEntered(e -> {pane.setStyle("-fx-background-color: #343a41");});
            pane.setOnMouseExited(e -> {pane.setStyle("-fx-background-color: #55606e");});


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
        System.out.println("Size of panel: " + allTheTimesPane.getLayoutY());



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