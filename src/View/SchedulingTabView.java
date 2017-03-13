package View;


import Model.Employee;
import Model.Queries;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class SchedulingTabView {


    ScrollPane rootPane;
    HBox hbox;
    GridPane timeSlots;
    Slider verticalSlider;
    Slider horizontalSlider;
    int currentGridPaneColumn = 1;
    int gridDepth = 0;
    List<Employee> employeeList;
    List<EmployeeAgenda> employeeAgendas = new ArrayList<>();


    public SchedulingTabView(ScrollPane rootPane, Slider verticalSlider, Slider horizontalSlider) {
        this.rootPane = rootPane;
        this.verticalSlider = verticalSlider;
        this.horizontalSlider = horizontalSlider;
        rootPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        rootPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        hbox = new HBox();
        timeSlots = new GridPane();
        for(int i = 0; i < 57; i++){
            timeSlots.getRowConstraints().add(i ,new RowConstraints(50.0));
        }
        times(timeSlots);
        addEmployees();

        rootPane.setContent(timeSlots);
    }

    public void addEmployees(){
        employeeList = new Queries().getAllEmployees();

        for(Employee employee : employeeList){
            agendaCreation(employee);
        }

    }

    public void times(GridPane timeSlots) {
        int hourArray[] = {7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8};
        String minutesArray[] = {"00", "15", "30", "45"};
        int tracker = 0;

        for (int i = 0; i < hourArray.length+1; i++) {

            if(i == 0){
                Pane pane = new Pane();
                pane.setStyle("-fx-background-color: #3d3d3d");
                Label label = new Label("Time");
                label.setTextFill(Color.WHITE);
                label.setFont(Font.font(14.0));
                pane.getChildren().add(label);
                timeSlots.addRow(tracker, pane);
                tracker++;
                gridDepth++;

            }else {

                for (String m : minutesArray) {
                    Pane pane = new Pane();
                    pane.setStyle("-fx-background-color: #3d3d3d");
                    Label label = new Label();
                    label.setTextFill(Color.WHITE);
                    label.setFont(Font.font(14.0));
                    label.setText(hourArray[i - 1] + ":" + m);
                    pane.getChildren().add(label);
                    timeSlots.addRow(tracker, pane);
                    tracker++;
                    gridDepth++;
                }

            }




        }

    }

    public void agendaCreation(Employee employee){


    }

    public ScrollPane getRootPane() {
        return rootPane;
    }

    public void setRootPane(ScrollPane rootPane) {
        this.rootPane = rootPane;
    }

    public HBox getHbox() {
        return hbox;
    }

    public void setHbox(HBox hbox) {
        this.hbox = hbox;
    }

    public GridPane getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(GridPane timeSlots) {
        this.timeSlots = timeSlots;
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
}

class EmployeeAgenda{

    Employee employee;
    List<Pane> paneList;

    public EmployeeAgenda(Employee employee){
        this.employee = employee;
        paneList = new ArrayList<>();
        for(int i = 0; i < 56; i++){
            Pane pane = new Pane();
            pane.setStyle( "-fx-background-color: #ff0073");
            pane.setOnMouseEntered(e -> {pane.setStyle( "-fx-background-color: #fa5073");});
            pane.setOnMouseExited(e -> {pane.setStyle( "-fx-background-color: #ff0073");});
            paneList.add(pane);
        }
    }

    public List<Pane> getPaneList(){
        return paneList;
    }

}