package Controller;

import Model.Employee;
import Model.MainWindowModel;
import View.MainWindowView;

import java.util.List;

/**
 * Created by Brannon on 3/12/2017.
 */
public class MainWindowController {

    private MainWindowModel mainWindowModel;
    private MainWindowView mainWindowView;
    private Employee employee;

    public MainWindowController(MainWindowView mainWindowView, MainWindowModel mainWindowModel, Employee employee){
        System.out.println(employee.getF_name());
        this.mainWindowModel = mainWindowModel;
        this.mainWindowView = mainWindowView;
        this.employee = employee;
        handles();
    }

    public void handles(){

        mainWindowView.getHamburgerButton().setOnAction(e -> {
            mainWindowView.hamburgerOpenCloseOperation();
        });

        mainWindowView.getHomeButton().setOnAction(e -> {
            System.out.println("Home Button Pressed");
            mainWindowView.getTabPane().getSelectionModel().select(mainWindowView.getHomeTab());
        });

        mainWindowView.getSchedulingButton().setOnAction(e -> {
            System.out.println("Scheduling Button Pressed");
            mainWindowView.getTabPane().getSelectionModel().select(mainWindowView.getSchedulingTab());

        });

        mainWindowView.getClientButton().setOnAction(e -> {
            System.out.println("Client Button Pressed");

        });

        mainWindowView.getSettingsButton().setOnAction(e -> {
            System.out.println("Settings Button Pressed");

        });

        mainWindowView.topBarContent(mainWindowView.getTopBar(), employee.getF_name());

    }



}
