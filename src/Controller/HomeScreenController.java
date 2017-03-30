package Controller;

import MiscObjects.Employee;
import Model.HomeScreenModel;
import View.HomeScreenView;

/**
 * Created by Brannon on 3/13/2017.
 */
public class HomeScreenController {

    HomeScreenModel homeScreenModel;
    HomeScreenView homeScreenView;
    Employee employee;

    public HomeScreenController(HomeScreenModel homeScreenModel, HomeScreenView homeScreenView, Employee employee){
        this.homeScreenModel = homeScreenModel;
        this.homeScreenView = homeScreenView;
        this.employee = employee;

    }

}
