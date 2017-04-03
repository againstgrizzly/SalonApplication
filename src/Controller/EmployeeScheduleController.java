package Controller;

import MiscObjects.Employee;
import Model.EmployeeScheduleModel;
import View.EmployeeScheduleView;

/**
 * Created by Brannon on 4/3/2017.
 */
public class EmployeeScheduleController {

    EmployeeScheduleView employeeScheduleView;
    EmployeeScheduleModel employeeScheduleModel;

    public EmployeeScheduleController(EmployeeScheduleView employeeScheduleView, EmployeeScheduleModel employeeScheduleModel){
        this.employeeScheduleModel = employeeScheduleModel;
        this.employeeScheduleView = employeeScheduleView;


    }

}
