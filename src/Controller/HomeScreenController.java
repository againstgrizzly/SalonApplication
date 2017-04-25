package Controller;

import MiscObjects.Employee;
import Model.AddAppointmentWindowModel;
import Model.HomeScreenModel;
import View.AddAppointmentWindowView;
import View.HomeScreenView;
import View.LoginView;
import View.MainWindowView;
import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by Brannon on 3/13/2017.
 */
public class HomeScreenController {

    private AnchorPane root;
    private AnchorPane mainWindowPane;

    HomeScreenModel homeScreenModel;
    HomeScreenView homeScreenView;
    Employee employee;
    Date date;
    Employee phone;

    private AnchorPane topPane;
    private Label topPaneLabel;
    private Stage stage;
    private Scene scene;
    private String stylinTheme = "css/StylinThemeCalmPro.css";

    public HomeScreenController(HomeScreenModel homeScreenModel, HomeScreenView homeScreenView, Employee employee) {
        this.homeScreenModel = homeScreenModel;
        this.homeScreenView = homeScreenView;
        this.employee = employee;


        handles();
    }

    public void handles() {



    }


    public AnchorPane getRoot() {
        return root;
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }

    public AnchorPane getMainWindowPane() {
        return mainWindowPane;
    }

    public void setMainWindowPane(AnchorPane mainWindowPane) {
        this.mainWindowPane = mainWindowPane;
    }

    public HomeScreenModel getHomeScreenModel() {
        return homeScreenModel;
    }

    public void setHomeScreenModel(HomeScreenModel homeScreenModel) {
        this.homeScreenModel = homeScreenModel;
    }

    public HomeScreenView getHomeScreenView() {
        return homeScreenView;
    }

    public void setHomeScreenView(HomeScreenView homeScreenView) {
        this.homeScreenView = homeScreenView;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getPhone() {
        return phone;
    }

    public void setPhone(Employee phone) {
        this.phone = phone;
    }

    public AnchorPane getTopPane() {
        return topPane;
    }

    public void setTopPane(AnchorPane topPane) {
        this.topPane = topPane;
    }

    public Label getTopPaneLabel() {
        return topPaneLabel;
    }

    public void setTopPaneLabel(Label topPaneLabel) {
        this.topPaneLabel = topPaneLabel;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}

