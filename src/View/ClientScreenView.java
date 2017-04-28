package View;

import MiscObjects.Client;
import MiscObjects.Employee;
import MiscObjects.Queries;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brannon on 4/28/2017.
 */
public class ClientScreenView {

    private AnchorPane root;
    private TabPane tabPane;
    private Tab employeesTab;
    private Tab clientsTab;
    private Label employeesLabel;
    private Label clientsLabel;
    private Button editEmployeeButton;
    private Button editClientButton;
    private Button addEmployeeButton;
    private Button addClientButton;

    private TableView<Employee> employeeTableView;
    private ObservableList<Employee> observableEmployees;
    private List<Employee> employees;


    private TableView<Client> clientTableView;

    private AnchorPane employeesAnchorPane;

    public ClientScreenView(AnchorPane root) {
        this.root = root; //this is the base pane

        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        employeesTabSetup();
        clientsTabSetup();

        AnchorPane.setBottomAnchor(tabPane, 0.0);
        AnchorPane.setTopAnchor(tabPane, 0.0);
        AnchorPane.setRightAnchor(tabPane, 0.0);
        AnchorPane.setLeftAnchor(tabPane, 0.0);


        root.getChildren().add(tabPane);
    }

    void employeesTabSetup() {
        employeesTab = new Tab("Employees");
        employeesTab.setClosable(false);

        employeesAnchorPane = new AnchorPane();
        employeesAnchorPane.setStyle("-fx-background-color: blue");

        addEmployeeButton = new Button("Add New Employee");

        addEmployeeButton.setOnMouseClicked(e -> {
            //load add employee window here
            System.out.println("Add Employee Button Clicked");
            CreateEmployeeView createEmployeeView = new CreateEmployeeView(this);


        });

        employeesAnchorPane.getChildren().add(addEmployeeButton);
        AnchorPane.setTopAnchor(addEmployeeButton, 15.0);
        AnchorPane.setRightAnchor(addEmployeeButton, 15.0);

        employeeTableView = new TableView<>();
        employees = new Queries().getAllEmployees();
        observableEmployees = FXCollections.observableList(employees);

        employeeTableView.itemsProperty().setValue(observableEmployees);

        TableColumn <Employee, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("f_name")
        );

        TableColumn <Employee, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("l_name")
        );

        TableColumn <Employee, String> username = new TableColumn<>("Username");
        username.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("username")
        );

        TableColumn <Employee, Number> phone = new TableColumn<>("Phone");
        phone.setCellValueFactory(
                new PropertyValueFactory<Employee, Number>("phone")
        );

        TableColumn <Employee, String> email = new TableColumn<>("Email");
        email.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("email")
        );

        TableColumn <Employee, String> dateOfBirth = new TableColumn<>("Date of Birth");
        dateOfBirth.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("date_of_birth")
        );


        employeeTableView.getColumns().addAll(firstNameCol, lastNameCol, username, phone, email);


        employeesAnchorPane.getChildren().add(employeeTableView);
        AnchorPane.setBottomAnchor(employeeTableView,  0.0);
        AnchorPane.setTopAnchor(employeeTableView, 50.0);
        AnchorPane.setRightAnchor(employeeTableView, 0.0);
        AnchorPane.setLeftAnchor(employeeTableView, 0.0);



        employeesTab.setContent(employeesAnchorPane);
        tabPane.getTabs().add(employeesTab);

    }

    void clientsTabSetup() {
        clientsTab = new Tab("Clients");
        clientsTab.setClosable(false);

        tabPane.getTabs().add(clientsTab);

    }

    public AnchorPane getRoot() {
        return root;
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public Tab getEmployeesTab() {
        return employeesTab;
    }

    public void setEmployeesTab(Tab employeesTab) {
        this.employeesTab = employeesTab;
    }

    public Tab getClientsTab() {
        return clientsTab;
    }

    public void setClientsTab(Tab clientsTab) {
        this.clientsTab = clientsTab;
    }

    public Label getEmployeesLabel() {
        return employeesLabel;
    }

    public void setEmployeesLabel(Label employeesLabel) {
        this.employeesLabel = employeesLabel;
    }

    public Label getClientsLabel() {
        return clientsLabel;
    }

    public void setClientsLabel(Label clientsLabel) {
        this.clientsLabel = clientsLabel;
    }

    public Button getEditEmployeeButton() {
        return editEmployeeButton;
    }

    public void setEditEmployeeButton(Button editEmployeeButton) {
        this.editEmployeeButton = editEmployeeButton;
    }

    public Button getEditClientButton() {
        return editClientButton;
    }

    public void setEditClientButton(Button editClientButton) {
        this.editClientButton = editClientButton;
    }

    public Button getAddEmployeeButton() {
        return addEmployeeButton;
    }

    public void setAddEmployeeButton(Button addEmployeeButton) {
        this.addEmployeeButton = addEmployeeButton;
    }

    public Button getAddClientButton() {
        return addClientButton;
    }

    public void setAddClientButton(Button addClientButton) {
        this.addClientButton = addClientButton;
    }

    public TableView<Employee> getEmployeeTableView() {
        return employeeTableView;
    }

    public void setEmployeeTableView(TableView<Employee> employeeTableView) {
        this.employeeTableView = employeeTableView;
    }

    public ObservableList<Employee> getObservableEmployees() {
        return observableEmployees;
    }

    public void setObservableEmployees(ObservableList<Employee> observableEmployees) {
        this.observableEmployees = observableEmployees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public TableView<Client> getClientTableView() {
        return clientTableView;
    }

    public void setClientTableView(TableView<Client> clientTableView) {
        this.clientTableView = clientTableView;
    }

    public AnchorPane getEmployeesAnchorPane() {
        return employeesAnchorPane;
    }

    public void setEmployeesAnchorPane(AnchorPane employeesAnchorPane) {
        this.employeesAnchorPane = employeesAnchorPane;
    }
}
