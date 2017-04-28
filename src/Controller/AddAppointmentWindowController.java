package Controller;

import MiscObjects.Employee;
import MiscObjects.Service;
import Model.AddAppointmentWindowModel;
import MiscObjects.Client;
import View.AddAppointmentWindowView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import java.awt.*;
import java.awt.Button;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Brannon on 4/4/2017.
 */

public class AddAppointmentWindowController {

    AddAppointmentWindowModel model;
    AddAppointmentWindowView view;
    Employee employee;
    Client selectedClient;

    public AddAppointmentWindowController(AddAppointmentWindowModel model, AddAppointmentWindowView view, Employee employee) {
        this.view = view;
        this.model = model;
        this.employee = employee;

        // populateServicesVBox(employee);
        handles();
    }

    public AddAppointmentWindowController(AddAppointmentWindowModel model, AddAppointmentWindowView view, Employee employee, Client client) {
        this.view = view;
        this.model = model;
        this.employee = employee;
        client = selectedClient;

       // view.getClientLabel().setText(client.getF_name() +" " + client.getL_name());
        //view.getPhoneNumberLabel().setText(String.valueOf(client.getPhone()));

        // populateServicesVBox(employee);
        handles();
    }

    public void populateServicesVBox(Employee employee) {
        try {
            List<Service> services;
            services = model.getServicesForThisEmployee(employee);

            List<String> serviceStringList = new ArrayList<>();

            for (Service service : services) {
                serviceStringList.add(service.getName());
            }
            //view.setUpServicesList(serviceStringList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handles() {
        view.getSelectClientButton().setOnAction(event -> {
            view.loadSelectClientWindow();
            handles();
            view.getClientButton().setOnAction(event1 -> {
                selectedClient = view.getListView().getSelectionModel().getSelectedItem();
                view.getClientLabel().setText(selectedClient.getF_name() + " " + selectedClient.getL_name());
                view.getClientNotesTextField().setText(selectedClient.getColor_formula());
                view.getPhoneNumberLabel().setText("Contact Number: " + selectedClient.getPhone());
                handles();
                view.getStage().close();
            });
            view.getAddClientButton().setOnAction(event1 -> {
                view.addNewClientWindow();
                handles();
            });
        });


        view.getConfirm().setOnAction(event -> {
            view.getStage().close();
        });

        view.getCancel().setOnAction(event -> {
            view.getStage().close();
        });
    }

}