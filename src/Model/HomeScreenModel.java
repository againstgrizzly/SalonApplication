package Model;

import MiscObjects.Appointment;
import MiscObjects.Employee;
import Controller.HomeScreenController;
import MiscObjects.Queries;
import View.HomeScreenView;

import java.sql.*;

/**
 * Created by Brannon on 3/13/2017.
 */
public class HomeScreenModel {
    Queries queries = new Queries();

    public String getLoggedInEmployeesAppointments(){
        String apptString = queries.getEmployeeAppointments().toString();

        return apptString;
    }
}
