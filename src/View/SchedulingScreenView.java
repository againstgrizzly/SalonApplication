package View;


import MiscObjects.Employee;
import MiscObjects.Queries;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import jfxtras.scene.control.LocalDatePicker;

import java.util.ArrayList;
import java.util.List;

public class SchedulingScreenView {

    private ScrollPane rootPane;
    private HBox schedulingBasePane;
    private VBox timeBox;
    private List<Employee> employees;

    private Slider verticalSlider;
    private Slider horizontalSlider;
    private LocalDatePicker localDatePicker;

    private ListView<String> timesList;



    public SchedulingScreenView(ScrollPane rootPane, Slider verticalSlider, Slider horizontalSlider, LocalDatePicker datePicker) {
        this.rootPane = rootPane;
        this.verticalSlider = verticalSlider;
        this.horizontalSlider = horizontalSlider;
        localDatePicker = datePicker;


        employees = new Queries().getAllEmployees();
        timesList = new ListView<>();
        AnchorPane testingPane = new AnchorPane();
        testingPane.setStyle("-fx-background-color: #00c7d4");
        testingPane.setPrefWidth(200);
        testingPane.setPrefHeight(1280);

        schedulingBasePane = new HBox();
        schedulingBasePane.setStyle("-fx-background-color: #f600ff");

        timeBox = new VBox();
        createTimeBox(timeBox);



        schedulingBasePane.getChildren().add(timeBox);
        schedulingBasePane.getChildren().add(testingPane);

        rootPane.setContent(schedulingBasePane);



        rootPane.setContent(schedulingBasePane);
    }

    public void createTimeBox(VBox timeBox){

        int timeHeight = 40;
        int timeWidth = 50;
//
//
        Pane pane1 = new Pane();
        pane1.setStyle("-fx-background-color: #565656");
        pane1.setPrefHeight(timeHeight);
        pane1.setPrefWidth(timeWidth);
        Label label1 = new Label("Time");
        label1.setFont(Font.font(20));
        label1.setTextFill(Color.WHITE);
        pane1.getChildren().add(label1);
        label1.setAlignment(Pos.CENTER);
        timeBox.getChildren().add(pane1);
//


        String hours[] = {"11", "12", "1", "2", "3", "4", "5", "6"};
        String minutes[] = {"00", "15", "30", "45"};

        for(int i = 0; i < hours.length; i++){

            String h = hours[i];

            for(int j = 0; j < minutes.length; j++){

                String m = minutes[j];
                String time;
                if(i < 2) {
                     time = h + ":" + m + "am";
                }else{
                    time = h + ":" + m + "pm";
                }

                Pane pane2 = new Pane();
                pane2.setStyle("-fx-background-color: #565656");
                pane2.setPrefHeight(timeHeight);
                pane2.setPrefWidth(timeWidth);
                Label label2 = new Label(time);
                label2.setFont(Font.font(12));
                label2.setTextFill(Color.WHITE);
                pane2.getChildren().add(label2);
                label2.setAlignment(Pos.CENTER);
                timeBox.getChildren().add(pane2);



            }

        }



    }

    public ScrollPane getRootPane() {
        return rootPane;
    }

    public void setRootPane(ScrollPane rootPane) {
        this.rootPane = rootPane;
    }

    public HBox getSchedulingBasePane() {
        return schedulingBasePane;
    }

    public void setSchedulingBasePane(HBox schedulingBasePane) {
        this.schedulingBasePane = schedulingBasePane;
    }

    public VBox getTimeBox() {
        return timeBox;
    }

    public void setTimeBox(VBox timeBox) {
        this.timeBox = timeBox;
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