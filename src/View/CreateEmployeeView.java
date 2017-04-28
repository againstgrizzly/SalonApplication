package View;


import MiscObjects.Employee;
import MiscObjects.Queries;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.sql.Date;

/**
 * Created by Brannon on 4/28/2017.
 */
public class CreateEmployeeView {

    private VBox basePane;
    private Label firstNameLabel;
    private TextField firstNameTextField;
    private Label lastNameLabel;
    private TextField lastNameTextField;
    private Label userNameLabel;
    private TextField userNameTextField;
    private Label phoneLabel;
    private TextField phoneTextField;
    private Label emailLabel;
    private TextField emailTextField;
    private Label passwordLabel;
    private TextField passworldTextField;
    private Label dateOfBirthLabel;
    private DatePicker datePicker;
    private Button createEmployeeButton;
    private Employee newEmployee;
    private int userType;

    private JFXRadioButton receptionistRadioButton;
    private JFXRadioButton adminRadioButton;
    private JFXRadioButton stylistRadioButton;
    private ToggleGroup group;
    private HBox radioButtonHolder;


    private ClientScreenView clientScreenView;

    public CreateEmployeeView(ClientScreenView clientScreenView) {
        this.clientScreenView = clientScreenView;

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        basePane = new VBox();
        basePane.setPrefSize(350, 400);

        Scene scene = new Scene(basePane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Add An Employee");
        stage.show();


        firstNameLabel = new Label("First Name");
        firstNameTextField = new TextField();
        firstNameTextField.setPromptText("Enter First Name");

        lastNameLabel = new Label("Last Name");
        lastNameTextField = new TextField();
        lastNameTextField.setPromptText("Enter Last Name");

        userNameLabel = new Label("Username");
        userNameTextField = new TextField();
        userNameTextField.setPromptText("Enter Username");

        phoneLabel = new Label("Phone");
        phoneTextField = new TextField();
        phoneTextField.setPromptText("Enter Phone Number");

        emailLabel = new Label("Email");
        emailTextField = new TextField();
        emailTextField.setPromptText("Enter Email");

        passwordLabel = new Label("Password");
        passworldTextField = new TextField();
        passworldTextField.setPromptText("Enter Password");

        receptionistRadioButton = new JFXRadioButton("Receptionist");
        receptionistRadioButton.setToggleGroup(group);
        adminRadioButton = new JFXRadioButton("Administrator");
        adminRadioButton.setToggleGroup(group);

        stylistRadioButton = new JFXRadioButton("Stylist");
        stylistRadioButton.setToggleGroup(group);


        radioButtonHolder = new HBox();
        radioButtonHolder.getChildren().addAll(adminRadioButton, receptionistRadioButton, stylistRadioButton);


        dateOfBirthLabel = new Label("Date of Birth");
        datePicker = new DatePicker();

        createEmployeeButton = new Button("Create Employee");

        basePane.getChildren().addAll(firstNameLabel, firstNameTextField, lastNameLabel, lastNameTextField,
                userNameLabel, userNameTextField, phoneLabel, phoneTextField, emailLabel, emailTextField, radioButtonHolder, passwordLabel, passworldTextField,
                dateOfBirthLabel, datePicker, createEmployeeButton);

        createEmployeeButton.setOnMouseClicked(e -> {

            if (receptionistRadioButton.isSelected()) {
                userType = 2;
            }

            if (adminRadioButton.isSelected()) {
                userType = 0;
            }

            if (stylistRadioButton.isSelected()) {
                userType = 1;
            }

            newEmployee = new Employee();
            newEmployee.setF_name(firstNameTextField.getText());
            newEmployee.setL_name(lastNameTextField.getText());
            newEmployee.setUsername(userNameTextField.getText());
            newEmployee.setPhone(Long.valueOf(phoneTextField.getText()));
            newEmployee.setEmail(emailTextField.getText());
            newEmployee.setDate_of_birth(Date.valueOf(datePicker.getValue()));

            clientScreenView.getObservableEmployees().add(newEmployee);
            new Queries().InsertEmployee(newEmployee, passworldTextField.getText(), userType);
        });

    }

    public VBox getBasePane() {
        return basePane;
    }

    public void setBasePane(VBox basePane) {
        this.basePane = basePane;
    }

    public Label getFirstNameLabel() {
        return firstNameLabel;
    }

    public void setFirstNameLabel(Label firstNameLabel) {
        this.firstNameLabel = firstNameLabel;
    }

    public TextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public void setFirstNameTextField(TextField firstNameTextField) {
        this.firstNameTextField = firstNameTextField;
    }

    public Label getLastNameLabel() {
        return lastNameLabel;
    }

    public void setLastNameLabel(Label lastNameLabel) {
        this.lastNameLabel = lastNameLabel;
    }

    public TextField getLastNameTextField() {
        return lastNameTextField;
    }

    public void setLastNameTextField(TextField lastNameTextField) {
        this.lastNameTextField = lastNameTextField;
    }

    public Label getUserNameLabel() {
        return userNameLabel;
    }

    public void setUserNameLabel(Label userNameLabel) {
        this.userNameLabel = userNameLabel;
    }

    public TextField getUserNameTextField() {
        return userNameTextField;
    }

    public void setUserNameTextField(TextField userNameTextField) {
        this.userNameTextField = userNameTextField;
    }

    public Label getPhoneLabel() {
        return phoneLabel;
    }

    public void setPhoneLabel(Label phoneLabel) {
        this.phoneLabel = phoneLabel;
    }

    public TextField getPhoneTextField() {
        return phoneTextField;
    }

    public void setPhoneTextField(TextField phoneTextField) {
        this.phoneTextField = phoneTextField;
    }

    public Label getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(Label emailLabel) {
        this.emailLabel = emailLabel;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(TextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    public Label getDateOfBirthLabel() {
        return dateOfBirthLabel;
    }

    public void setDateOfBirthLabel(Label dateOfBirthLabel) {
        this.dateOfBirthLabel = dateOfBirthLabel;
    }

    public Button getCreateEmployeeButton() {
        return createEmployeeButton;
    }

    public void setCreateEmployeeButton(Button createEmployeeButton) {
        this.createEmployeeButton = createEmployeeButton;
    }

    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }

    public ClientScreenView getClientScreenView() {
        return clientScreenView;
    }

    public void setClientScreenView(ClientScreenView clientScreenView) {
        this.clientScreenView = clientScreenView;
    }
}
