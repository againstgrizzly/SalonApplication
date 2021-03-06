package Controller;

import MiscObjects.Employee;
import Model.AddAppointmentWindowModel;
import Model.HomeScreenModel;
import Model.MainWindowModel;
import Model.SchedulingScreenModel;
import View.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import jfxtras.scene.control.LocalDatePicker;

import static Controller.MainWindowController.SelectedPane.CLIENT;
import static Controller.MainWindowController.SelectedPane.HOME;
import static Controller.MainWindowController.SelectedPane.SCHEDULING;

/**
 * Created by Brannon on 3/12/2017.
 */
public class MainWindowController {

    private MainWindowModel mainWindowModel;
    private MainWindowView mainWindowView;
    private Employee employee;
    private LoginController loginController;

    public enum SelectedPane{HOME, SCHEDULING, CLIENT, SETTINGS}
    private SelectedPane selectedPane;

    public MainWindowController(MainWindowView mainWindowView, MainWindowModel mainWindowModel) {
        //System.out.println(employee.getF_name());
        this.mainWindowModel = mainWindowModel;
        this.mainWindowView = mainWindowView;
        this.loginController = loginController;
        //this.employee = employee;


        selectedPane = HOME;

        //This passes reference for the pane displayed when the home button is clicked
        //to the HomeScreenView so that we can add the homeScreenView components to it
        //Further, it also creates our Model-View-Controller system for the
        //Home Screen
        mainWindowView.getSchedulingScreen();

        //Creates the homescreen tabs content
        homeContent(mainWindowView.getHomeScreen(), employee);

        //Creates the schedulingscreen tabs content
        schedulingContent(mainWindowView.getSchedulingScreen());

        //Create the client screen tab content
        employeeClientContent(mainWindowView.getClientScreen());

        //Implements Listeners for Home, Scheduling, Client, Settings, and Hamburger buttons
        handles();
    }

    //Home Content
    public void homeContent(AnchorPane homeScreenBasePane, Employee employee){
        HomeScreenView homeScreenView = new HomeScreenView(homeScreenBasePane, "Ian");
        HomeScreenModel homeScreenModel = new HomeScreenModel();
        HomeScreenController homeScreenController = new HomeScreenController(homeScreenModel, homeScreenView, employee);
    }

    //Scheduling content
    public void schedulingContent(AnchorPane schedulingScreenBasePane){
        SchedulingScreenView schedulingScreenView = new SchedulingScreenView(schedulingScreenBasePane, mainWindowView.getVerticalSlider(), mainWindowView.getHorizontalSlider(), mainWindowView.getSchedulingDatePicker());
        SchedulingScreenModel schedulingScreenModel = new SchedulingScreenModel();
        SchedulingScreenController schedulingScreenController = new SchedulingScreenController(schedulingScreenModel, schedulingScreenView);
    }

    //Employee/Client Content
    void employeeClientContent(AnchorPane basePane){
        ClientScreenView clientScreenView = new ClientScreenView(basePane);
    }


    public void handles() {
        mainWindowView.getHamburgerButton().setOnAction(e -> {mainWindowView.hamburgerOpenCloseOperation(selectedPane);});

        mainWindowView.getHomeButton().setOnAction(e -> {
            System.out.println("Home Button Pressed");

            //If the current screen is not the home screen
            //Make the current screen the home screen\
            //Else, do nothing
            if (!mainWindowView.getDifferentScreenContainer().getChildren().contains(mainWindowView.getHomeScreen())) {
                mainWindowView.getDifferentScreenContainer().getChildren().clear();
                mainWindowView.addHomeScreenToScreenContainer();
                selectedPane = HOME;

                //Set the custom components of the left menu pane (hamburger)
                mainWindowView.leftMenuPaneCustomComponents(selectedPane);
            }

        });

        mainWindowView.getSchedulingButton().setOnAction(e -> {
            //If the current screen is not the scheduling screen
            //Make the current screen the scheduling screen\
            //Else, do nothing
            System.out.println("Scheduling Button Pressed");
            if(!mainWindowView.getDifferentScreenContainer().getChildren().contains(mainWindowView.getSchedulingScreen())){
                mainWindowView.addSchedulingScreenToScreenContainer();
                selectedPane = SCHEDULING;

                //Set the custom components of the left menu pane (hamburger)
                mainWindowView.leftMenuPaneCustomComponents(selectedPane);
            }

        });

        mainWindowView.getClientButton().setOnAction(e -> {
            System.out.println("Client Button Pressed");
            //If the current screen is not the scheduling screen
            //Make the current screen the scheduling screen\
            //Else, do nothing
            if(!mainWindowView.getDifferentScreenContainer().getChildren().contains(mainWindowView.getClientScreen())){
                mainWindowView.addClientScreenToScreenContainer();
                selectedPane = CLIENT;

                //Set the custom components of the left menu pane (hamburger)
                mainWindowView.leftMenuPaneCustomComponents(selectedPane);
            }

        });

        mainWindowView.getSettingsButton().setOnAction(e -> {
            System.out.println("Settings Button Pressed");

        });

        mainWindowView.getNewAppointmentButton().setOnAction(e ->{
            //Load add appointment window
            System.out.println("Add New Appointment");
            mainWindowView.loadAddAppointmentWindowView();
        });

    }

    public void logMeIn(Employee employee){
        System.out.print(employee);
        mainWindowView.logMeIn(employee);

    }


}
