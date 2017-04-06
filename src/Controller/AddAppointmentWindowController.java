package Controller;

import MiscObjects.Employee;
import Model.AddAppointmentWindowModel;
import View.AddAppointmentWindowView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brannon on 4/4/2017.
 */

public class AddAppointmentWindowController {

    AddAppointmentWindowModel model;
    AddAppointmentWindowView view;
    Employee employee;

    public AddAppointmentWindowController(AddAppointmentWindowModel model, AddAppointmentWindowView view, Employee employee){
        this.view = view;
        this.model = model;
        this.employee = employee;

        populateServicesVbox(employee);

    }

    public void populateServicesVbox(Employee employee){
        List<String> services;
        //services = model.getEmployeeServices(employee);
        //view.setUpServicesList(services);

    }

}
