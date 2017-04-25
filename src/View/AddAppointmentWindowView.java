package View;

import MiscObjects.Appointment;
import Model.AddAppointmentWindowModel;
import MiscObjects.Client;
import MiscObjects.Employee;
import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Brannon on 4/3/2017.
 */
public class AddAppointmentWindowView {

    private MainWindowView mainWindowView;
    AddAppointmentWindowModel addAppointmentWindowModel;
    private Stage stage;
    private Scene scene;

    Client selectedClient;
    private VBox vBox;

    private Date date;
    private Employee employee;

    private AnchorPane root;

    private AnchorPane topPane;
    private Label topPaneLabel;

    private Button changeEmployeeButton;
    private ImageView changeEmployeeImage;

    private AnchorPane appointmentPane;
    private Label clientLabel;
    private Label phoneNumberLabel;
    private Button selectClientButton;

    private Label serviceLabel;
    private ScrollPane servicesScrollPane;
    private List<String> servicesList;

    private Label startTimeLabel;
    private ChoiceBox<String> startTimeChoiceBox;
    private Label endTimeLabel;
    private ChoiceBox<String> endTimeChoiceBox;

    private CheckBox recurring;

    private DatePicker datePicker;

    private GaussianBlur gaussianBlur;
    private Label clientNotesLabel;
    private TextArea clientNotesTextField;
    private Label appointmentNotesLabel;
    private TextArea appointmentNotesTextField;

    private Button confirm;
    private Button cancel;
    private AnchorPane blockerPane;
    private BorderPane dividerPane;

    private String stylinTheme = "css/StylinThemeCalmPro.css";

    private AnchorPane mainWindowPane;

    private Label firstName;
    private Label lastName;
    private Label phone;
    private TextField firstNameTextField;
    private TextField lastNameTextField;
    private TextField phoneTextField;
    private Label addressLabel;
    private Label cityLabel;
    private Label stateLabel;
    private Label emailLabel;
    private TextField addressTextField;
    private TextField cityTextField;
    private TextField stateTextField;
    private TextField emailTextField;
    private Label postalCodeLabel;
    private TextField postalCodeTextField;
    private Button confirmClient;

    private ScrollPane selectClientPane;
    private Button addClientButton;
    private Button clientButton;
    private ListView<Client> listView;
    private ObservableList<Client> clientList;
    private List<Client> clients;
    private List<String> namesList;

    public ObservableList<Client> getClientList() {
        return clientList;
    }

    public void setClientList(ObservableList<Client> clientList) {
        this.clientList = clientList;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<String> getNamesList() {
        return namesList;
    }

    public void setNamesList(List<String> namesList) {
        this.namesList = namesList;
    }

    public AddAppointmentWindowView(Employee employee, Date date) {
        stage = new Stage();
        stage.setTitle("Edit/New Appointment");
        root = new AnchorPane();
        stage.setResizable(false);
        scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        this.employee = employee; //employee selected from scheduling pane;
        this.date = date;
        this.mainWindowPane = mainWindowPane; //Add root pane to this
        root.setStyle("-fx-background-color: #e9e9e9");
        stage.initModality(Modality.APPLICATION_MODAL);
        addAppointmentWindowModel = new AddAppointmentWindowModel();
        //topPaneSetup();
        appointmentPaneSetup();
        topPaneSetup();
        stage.show();

    }

    public void appointmentPaneSetup() {
        gaussianBlur = new GaussianBlur(100);

        dividerPane = new BorderPane();
        AnchorPane.setBottomAnchor(dividerPane, 0.0);
        AnchorPane.setTopAnchor(dividerPane, 0.0);
        AnchorPane.setLeftAnchor(dividerPane, 0.0);
        AnchorPane.setRightAnchor(dividerPane, 0.0);
        dividerPane.setEffect(gaussianBlur);
        root.getChildren().add(dividerPane);

        clientLabel = new Label("Add A Client");
        clientLabel.setFont(Font.font(27.0));
        root.getChildren().add(clientLabel);
        AnchorPane.setTopAnchor(clientLabel, 60.0);
        AnchorPane.setLeftAnchor(clientLabel, 15.0);

        phoneNumberLabel = new Label();
        phoneNumberLabel.setFont(Font.font(14.0));
        root.getChildren().add(phoneNumberLabel);
        AnchorPane.setLeftAnchor(phoneNumberLabel, 15.0);
        AnchorPane.setTopAnchor(phoneNumberLabel, 102.0);

        selectClientButton = new Button();
        selectClientButton.setText("Add Client");
        selectClientButton.setPrefWidth(191);
        selectClientButton.setPrefHeight(35);
        selectClientButton.setFont(Font.font(14));
        selectClientButton.getStylesheets().add(stylinTheme);
        root.getChildren().add(selectClientButton);
        AnchorPane.setLeftAnchor(selectClientButton, 15.0);
        AnchorPane.setTopAnchor(selectClientButton, 134.0);

        serviceLabel = new Label("Services");
        serviceLabel.setFont(Font.font(27.0));
        root.getChildren().add(serviceLabel);
        AnchorPane.setLeftAnchor(serviceLabel, 15.0);
        AnchorPane.setTopAnchor(serviceLabel, 215.0);

        servicesScrollPane = new ScrollPane();

        servicesScrollPane.setContent(vBox);

        root.setStyle("-fx-background-color: #e9e9e9");

        root.getChildren().add(servicesScrollPane);
        AnchorPane.setBottomAnchor(servicesScrollPane, 15.0);
        AnchorPane.setLeftAnchor(servicesScrollPane, 15.0);
        AnchorPane.setTopAnchor(servicesScrollPane, 250.0);
        AnchorPane.setRightAnchor(servicesScrollPane, 513.0);

        clientNotesLabel = new Label("Client notes");
        clientNotesLabel.setFont(Font.font(27.0));
        root.getChildren().add(clientNotesLabel);
        AnchorPane.setLeftAnchor(clientNotesLabel, 337.0);
        AnchorPane.setTopAnchor(clientNotesLabel, 335.0);

        clientNotesTextField = new TextArea();
        clientNotesTextField.setPromptText("Enter client notes here");
        clientNotesTextField.setPrefHeight(145.0);
        root.getChildren().add(clientNotesTextField);
        AnchorPane.setTopAnchor(clientNotesTextField, 395.0);
        AnchorPane.setRightAnchor(clientNotesTextField, 15.0);
        AnchorPane.setLeftAnchor(clientNotesTextField, 337.0);

        appointmentNotesLabel = new Label("Appointment Notes");
        appointmentNotesLabel.setFont(Font.font(27.0));
        root.getChildren().add(appointmentNotesLabel);
        AnchorPane.setTopAnchor(appointmentNotesLabel, 140.0);
        AnchorPane.setLeftAnchor(appointmentNotesLabel, 337.0);

        appointmentNotesTextField = new TextArea();
        appointmentNotesTextField.setPromptText("Enter appointment notes here");
        appointmentNotesTextField.setPrefHeight(145.0);
        root.getChildren().add(appointmentNotesTextField);
        AnchorPane.setTopAnchor(appointmentNotesTextField, 180.0);
        AnchorPane.setRightAnchor(appointmentNotesTextField, 15.0);
        AnchorPane.setLeftAnchor(appointmentNotesTextField, 337.0);

        confirm = new Button();
        confirm.getStylesheets().add(stylinTheme);
        confirm.setText("Confirm Appointment");
        confirm.setPrefWidth(150);
        confirm.setPrefHeight(35);
        confirm.setFont(Font.font(14));
        root.getChildren().add(confirm);
        AnchorPane.setBottomAnchor(confirm, 15.0);
        AnchorPane.setRightAnchor(confirm,15.0);

        cancel = new Button();
        cancel.getStylesheets().add(stylinTheme);
        cancel.setText("Cancel Appointment");
        cancel.setPrefWidth(150.0);
        cancel.setPrefHeight(35);
        cancel.setFont(Font.font(14));
        root.getChildren().add(cancel);
        AnchorPane.setBottomAnchor(cancel, 15.0);
        AnchorPane.setRightAnchor(cancel,175.0);
        choiceBoxSetup();
        root.requestFocus();
        stage.show();
    }


    public void setUpServicesList(List<String> services){
        vBox = new VBox();

        for(String service : services){
            JFXCheckBox checkBox = new JFXCheckBox(service);
            checkBox.setCheckedColor(Color.rgb(36, 175, 178));
            checkBox.setFont(Font.font(14.0));
            vBox.getChildren().add(checkBox);
            System.out.println(service);
            VBox.setVgrow(servicesScrollPane, Priority.ALWAYS);
        }
    }

    public void choiceBoxSetup(){

        startTimeLabel = new Label("Start Time");
        startTimeLabel.setFont(Font.font(14.0));
        root.getChildren().add(startTimeLabel);
        AnchorPane.setTopAnchor(startTimeLabel, 60.0);
        AnchorPane.setRightAnchor(startTimeLabel, 187.0);

        String hours[] = {"12", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "10", "11", "12", "1", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "11"};

        String minutes[] = {"00", "15", "30", "45"};

        List<String> timesList = new ArrayList<>();

        for(int i = 0; i < hours.length; i++){
            for(int j = 0; j < minutes.length; j++){
                if(i < hours.length/2) {
                    timesList.add(hours[i] + ":" + minutes[j] + " AM");
                }else{
                    timesList.add(hours[i] + ":" + minutes[j] + " PM");

                }
            }
        }

        Label dateLabel = new Label("Date");
        dateLabel.setFont(Font.font(14.0));
        root.getChildren().add(dateLabel);
        AnchorPane.setLeftAnchor(dateLabel, 339.);
        AnchorPane.setTopAnchor(dateLabel, 60.0);

        datePicker = new DatePicker();
        datePicker.setValue(new java.sql.Date(date.getTime()).toLocalDate());
        datePicker.setPrefHeight(30.0);
        datePicker.setPrefWidth(174.0);
        root.getChildren().add(datePicker);
        AnchorPane.setTopAnchor(datePicker, 85.0);
        AnchorPane.setLeftAnchor(datePicker, 337.0);

        startTimeChoiceBox = new ChoiceBox<>(
                FXCollections.observableArrayList(timesList));
        startTimeChoiceBox.setPrefWidth(100.0);

        endTimeChoiceBox = new ChoiceBox<String>(
                FXCollections.observableArrayList(timesList));
        endTimeChoiceBox.setPrefHeight(100.0);

        root.getChildren().add(startTimeChoiceBox);
        AnchorPane.setTopAnchor(startTimeChoiceBox, 85.0);
        AnchorPane.setRightAnchor(startTimeChoiceBox, 150.0);
        AnchorPane.setBottomAnchor(startTimeChoiceBox, 485.0);




        endTimeLabel = new Label("End Time");
        endTimeLabel.setFont(Font.font(14.0));
        root.getChildren().add(endTimeLabel);
        AnchorPane.setTopAnchor(endTimeLabel, 60.0);
        AnchorPane.setRightAnchor(endTimeLabel, 57.0);


        root.getChildren().add(endTimeChoiceBox);
        AnchorPane.setTopAnchor(endTimeChoiceBox, 85.0);
        AnchorPane.setRightAnchor(endTimeChoiceBox, 15.0);
        AnchorPane.setBottomAnchor(endTimeChoiceBox, 485.0);



    }

    public void topPaneSetup() {
        topPane = new AnchorPane();
        topPane.setStyle("-fx-background-color: #343a41");
        AnchorPane.setBottomAnchor(topPane, 550.0);
        AnchorPane.setRightAnchor(topPane, 0.0);
        AnchorPane.setLeftAnchor(topPane, 0.0);
        AnchorPane.setTopAnchor(topPane, 0.0);

        topPaneLabel = new Label();
        String text = "Scheduling with " + employee.getF_name() + " " + employee.getL_name() + " on " + datePicker.getValue().toString();
        topPaneLabel.setText(text);
        topPaneLabel.setFont(Font.font(24.0));
        topPaneLabel.setTextFill(Color.WHITE);
        topPane.getChildren().add(topPaneLabel);
        AnchorPane.setBottomAnchor(topPaneLabel, 0.0);
        AnchorPane.setTopAnchor(topPaneLabel, 0.0);
        AnchorPane.setLeftAnchor(topPaneLabel, 25.0);

        changeEmployeeButton = new Button();
        changeEmployeeImage = new ImageView(new Image("res/changeStylist.png"));
        changeEmployeeImage.setFitWidth(30.0);
        changeEmployeeImage.setFitHeight(30.0);
        changeEmployeeButton.setPrefSize(40.0, 40.0);
        changeEmployeeButton.setGraphic(changeEmployeeImage);
        topPane.getChildren().add(changeEmployeeButton);
        AnchorPane.setRightAnchor(changeEmployeeButton, 5.0);
        AnchorPane.setBottomAnchor(changeEmployeeButton, 5.0);
        AnchorPane.setTopAnchor(changeEmployeeButton, 5.0);





        root.getChildren().add(topPane);
    }

    public void loadSelectClientWindow() {
        stage = new Stage();
        root = new AnchorPane();
        scene = new Scene(root, 600, 400);
        stage.setTitle("Client Search");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);



        namesList = new ArrayList<>();

        for(Client client : addAppointmentWindowModel.getAllClients()){
            namesList.add(client.getF_name() + " " + client.getL_name());
        }


        clients = addAppointmentWindowModel.getAllClients();

        clientList = FXCollections.observableList(clients);

        listView = new ListView<>(clientList);


        selectClientPane = new ScrollPane();
        selectClientPane.setFitToHeight(true);
        selectClientPane.setFitToWidth(true);
        selectClientPane.setContent(listView);
        AnchorPane.setTopAnchor(selectClientPane, 10.0);
        AnchorPane.setBottomAnchor(selectClientPane, 150.0);
        AnchorPane.setRightAnchor(selectClientPane, 50.0);
        AnchorPane.setLeftAnchor(selectClientPane, 50.0);


        addClientButton = new Button();
        addClientButton.setText("Add New Client");
        addClientButton.setMinHeight(40.0);
        addClientButton.setMinWidth(120.0);
        addClientButton.getStylesheets().add(stylinTheme);
        AnchorPane.setTopAnchor(addClientButton, 320.0);
        AnchorPane.setBottomAnchor(addClientButton, 20.0);
        AnchorPane.setLeftAnchor(addClientButton, 360.0);

        clientButton = new Button();
        clientButton.setText("Confirm Client");
        clientButton.setMinHeight(40.0);
        clientButton.setMinWidth(120.0);
        clientButton.getStylesheets().add(stylinTheme);
        AnchorPane.setTopAnchor(clientButton, 320.0);
        AnchorPane.setBottomAnchor(clientButton, 20.0);
        AnchorPane.setLeftAnchor(clientButton, 100.0);

        root.getChildren().addAll(selectClientPane, clientButton, addClientButton);


        stage.show();
    }

    public void addNewClientWindow() {
        stage = new Stage();
        root = new AnchorPane();
        scene = new Scene(root, 500, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);

        firstName = new Label();
        firstName.setText("Please Enter First Name");
        AnchorPane.setTopAnchor(firstName, 20.0);
        AnchorPane.setLeftAnchor(firstName, 10.0);

        firstNameTextField = new TextField();
        firstNameTextField.setMaxWidth(140);
        AnchorPane.setTopAnchor(firstNameTextField, 40.0);
        AnchorPane.setLeftAnchor(firstNameTextField, 10.0);

        lastName = new Label();
        lastName.setText("Please Enter Last Name");
        AnchorPane.setTopAnchor(lastName, 80.0);
        AnchorPane.setLeftAnchor(lastName, 10.0);

        lastNameTextField = new TextField();
        lastNameTextField.setMaxWidth(140);
        AnchorPane.setTopAnchor(lastNameTextField, 100.0);
        AnchorPane.setLeftAnchor(lastNameTextField, 10.0);

        phone = new Label();
        phone.setText("Please Enter Phone Number");
        AnchorPane.setTopAnchor(phone, 140.0);
        AnchorPane.setLeftAnchor(phone, 10.0);

        phoneTextField = new TextField();
        phoneTextField.setMaxWidth(140);
        AnchorPane.setTopAnchor(phoneTextField, 160.0);
        AnchorPane.setLeftAnchor(phoneTextField, 10.0);

        emailLabel = new Label();
        emailLabel.setText("Please Enter Email");
        AnchorPane.setTopAnchor(emailLabel, 200.0);
        AnchorPane.setLeftAnchor(emailLabel, 10.0);

        emailTextField = new TextField();
        AnchorPane.setTopAnchor(emailTextField, 220.0);
        AnchorPane.setLeftAnchor(emailTextField, 10.0);

        addressLabel = new Label();
        addressLabel.setText("Please Enter Street Address");
        AnchorPane.setTopAnchor(addressLabel, 20.0);
        AnchorPane.setLeftAnchor(addressLabel, 300.0);

        addressTextField = new TextField();
        AnchorPane.setTopAnchor(addressTextField, 40.0);
        AnchorPane.setLeftAnchor(addressTextField, 300.0);

        cityLabel = new Label();
        cityLabel.setText("Please Enter City");
        AnchorPane.setTopAnchor(cityLabel, 80.0);
        AnchorPane.setLeftAnchor(cityLabel, 300.0);

        cityTextField = new TextField();
        AnchorPane.setTopAnchor(cityTextField, 100.0);
        AnchorPane.setLeftAnchor(cityTextField, 300.0);

        stateLabel = new Label();
        stateLabel.setText("Please Enter State");
        AnchorPane.setTopAnchor(stateLabel, 140.0);
        AnchorPane.setLeftAnchor(stateLabel, 300.0);

        stateTextField = new TextField();
        AnchorPane.setTopAnchor(stateTextField, 160.0);
        AnchorPane.setLeftAnchor(stateTextField, 300.0);

        postalCodeLabel = new Label();
        postalCodeLabel.setText("Please Enter Zip Code");
        AnchorPane.setTopAnchor(postalCodeLabel, 200.0);
        AnchorPane.setLeftAnchor(postalCodeLabel, 300.0);

        postalCodeTextField = new TextField();
        AnchorPane.setTopAnchor(postalCodeTextField, 220.0);
        AnchorPane.setLeftAnchor(postalCodeTextField, 300.0);

        confirmClient = new Button();
        confirmClient.setText("Confirm New Client");
        confirmClient.getStylesheets().add(stylinTheme);
        confirmClient.setMinHeight(40.0);
        confirmClient.setMinWidth(120.0);
        AnchorPane.setTopAnchor(confirmClient,280.0);
        AnchorPane.setLeftAnchor(confirmClient, 200.0);

        root.getChildren().addAll(firstName, firstNameTextField, lastName, lastNameTextField, phone, phoneTextField, emailLabel, emailTextField,
                addressLabel, addressTextField, cityLabel, cityTextField, stateLabel, stateTextField, postalCodeLabel, postalCodeTextField, confirmClient);
        stage.show();
    }

    public ListView<Client> getListView() {
        return listView;
    }

    public void setListView(ListView<Client> listView) {
        this.listView = listView;
    }

    public Label getFirstName() {
        return firstName;
    }

    public void setFirstName(Label firstName) {
        this.firstName = firstName;
    }

    public Label getLastName() {
        return lastName;
    }

    public void setLastName(Label lastName) {
        this.lastName = lastName;
    }

    public Label getPhone() {
        return phone;
    }

    public void setPhone(Label phone) {
        this.phone = phone;
    }

    public TextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public void setFirstNameTextField(TextField firstNameTextField) {
        this.firstNameTextField = firstNameTextField;
    }

    public TextField getLastNameTextField() {
        return lastNameTextField;
    }

    public void setLastNameTextField(TextField lastNameTextField) {
        this.lastNameTextField = lastNameTextField;
    }

    public TextField getPhoneTextField() {
        return phoneTextField;
    }

    public void setPhoneTextField(TextField phoneTextField) {
        this.phoneTextField = phoneTextField;
    }

    public Label getAddressLabel() {
        return addressLabel;
    }

    public void setAddressLabel(Label addressLabel) {
        this.addressLabel = addressLabel;
    }

    public Label getCityLabel() {
        return cityLabel;
    }

    public void setCityLabel(Label cityLabel) {
        this.cityLabel = cityLabel;
    }

    public Label getStateLabel() {
        return stateLabel;
    }

    public void setStateLabel(Label stateLabel) {
        this.stateLabel = stateLabel;
    }

    public Label getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(Label emailLabel) {
        this.emailLabel = emailLabel;
    }

    public TextField getAddressTextField() {
        return addressTextField;
    }

    public void setAddressTextField(TextField addressTextField) {
        this.addressTextField = addressTextField;
    }

    public TextField getCityTextField() {
        return cityTextField;
    }

    public void setCityTextField(TextField cityTextField) {
        this.cityTextField = cityTextField;
    }

    public TextField getStateTextField() {
        return stateTextField;
    }

    public void setStateTextField(TextField stateTextField) {
        this.stateTextField = stateTextField;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(TextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    public Label getPostalCodeLabel() {
        return postalCodeLabel;
    }

    public void setPostalCodeLabel(Label postalCodeLabel) {
        this.postalCodeLabel = postalCodeLabel;
    }

    public TextField getPostalCodeTextField() {
        return postalCodeTextField;
    }

    public void setPostalCodeTextField(TextField postalCodeTextField) {
        this.postalCodeTextField = postalCodeTextField;
    }

    public Button getConfirmClient() {
        return confirmClient;
    }

    public void setConfirmClient(Button confirmClient) {
        this.confirmClient = confirmClient;
    }

    public ScrollPane getSelectClientPane() {
        return selectClientPane;
    }

    public void setSelectClientPane(ScrollPane selectClientPane) {
        this.selectClientPane = selectClientPane;
    }

    public Button getAddClientButton() {
        return addClientButton;
    }

    public void setAddClientButton(Button addClientButton) {
        this.addClientButton = addClientButton;
    }

    public Button getClientButton() {
        return clientButton;
    }

    public void setClientButton(Button clientButton) {
        this.clientButton = clientButton;
    }

    public Button getConfirm() {
        return confirm;
    }

    public void setConfirm(Button confirm) {
        this.confirm = confirm;
    }

    public Button getCancel() {
        return cancel;
    }

    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public AnchorPane getRoot(){return root;}
    public void setRoot(AnchorPane root){this.root = root;}

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public AnchorPane getAppointmentPane() {
        return appointmentPane;
    }

    public void setAppointmentPane(AnchorPane appointmentPane) {
        this.appointmentPane = appointmentPane;
    }

    public Label getClientLabel() {
        return clientLabel;
    }

    public void setClientLabel(Label clientLabel) {
        this.clientLabel = clientLabel;
    }

    public Label getPhoneNumberLabel() {
        return phoneNumberLabel;
    }

    public void setPhoneNumberLabel(Label phoneNumberLabel) {
        this.phoneNumberLabel = phoneNumberLabel;
    }

    public Button getSelectClientButton() {
        return selectClientButton;
    }

    public void setSelectClientButton(Button selectClientButton) {
        this.selectClientButton = selectClientButton;
    }

    public Label getServiceLabel() {
        return serviceLabel;
    }

    public void setServiceLabel(Label serviceLabel) {
        this.serviceLabel = serviceLabel;
    }

    public List<String> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<String> servicesList) {
        this.servicesList = servicesList;
    }

    public Label getStartTimeLabel() {
        return startTimeLabel;
    }

    public void setStartTimeLabel(Label startTimeLabel) {
        this.startTimeLabel = startTimeLabel;
    }

    public ChoiceBox<String> getStartTimeChoiceBox() {
        return startTimeChoiceBox;
    }

    public void setStartTimeChoiceBox(ChoiceBox<String> startTimeChoiceBox) {
        this.startTimeChoiceBox = startTimeChoiceBox;
    }

    public Label getEndTimeLabel() {
        return endTimeLabel;
    }

    public void setEndTimeLabel(Label endTimeLabel) {
        this.endTimeLabel = endTimeLabel;
    }


    public TextArea getClientNotesTextField() {
        return clientNotesTextField;
    }

    public void setClientNotesTextField(TextArea clientNotesTextField) {
        this.clientNotesTextField = clientNotesTextField;
    }

    public MainWindowView getMainWindowView() {
        return mainWindowView;
    }

    public void setMainWindowView(MainWindowView mainWindowView) {
        this.mainWindowView = mainWindowView;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public Button getChangeEmployeeButton() {
        return changeEmployeeButton;
    }

    public void setChangeEmployeeButton(Button changeEmployeeButton) {
        this.changeEmployeeButton = changeEmployeeButton;
    }

    public ImageView getChangeEmployeeImage() {
        return changeEmployeeImage;
    }

    public void setChangeEmployeeImage(ImageView changeEmployeeImage) {
        this.changeEmployeeImage = changeEmployeeImage;
    }

    public ScrollPane getServicesScrollPane() {
        return servicesScrollPane;
    }

    public void setServicesScrollPane(ScrollPane servicesScrollPane) {
        this.servicesScrollPane = servicesScrollPane;
    }

    public ChoiceBox<String> getEndTimeChoiceBox() {
        return endTimeChoiceBox;
    }

    public void setEndTimeChoiceBox(ChoiceBox<String> endTimeChoiceBox) {
        this.endTimeChoiceBox = endTimeChoiceBox;
    }

    public CheckBox getRecurring() {
        return recurring;
    }

    public void setRecurring(CheckBox recurring) {
        this.recurring = recurring;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

    public GaussianBlur getGaussianBlur() {
        return gaussianBlur;
    }

    public void setGaussianBlur(GaussianBlur gaussianBlur) {
        this.gaussianBlur = gaussianBlur;
    }

    public Label getClientNotesLabel() {
        return clientNotesLabel;
    }

    public void setClientNotesLabel(Label clientNotesLabel) {
        this.clientNotesLabel = clientNotesLabel;
    }

    public Label getAppointmentNotesLabel() {
        return appointmentNotesLabel;
    }

    public void setAppointmentNotesLabel(Label appointmentNotesLabel) {
        this.appointmentNotesLabel = appointmentNotesLabel;
    }

    public TextArea getAppointmentNotesTextField() {
        return appointmentNotesTextField;
    }

    public void setAppointmentNotesTextField(TextArea appointmentNotesTextField) {
        this.appointmentNotesTextField = appointmentNotesTextField;
    }

    public AnchorPane getBlockerPane() {
        return blockerPane;
    }

    public void setBlockerPane(AnchorPane blockerPane) {
        this.blockerPane = blockerPane;
    }

    public BorderPane getDividerPane() {
        return dividerPane;
    }

    public void setDividerPane(BorderPane dividerPane) {
        this.dividerPane = dividerPane;
    }

    public String getStylinTheme() {
        return stylinTheme;
    }

    public void setStylinTheme(String stylinTheme) {
        this.stylinTheme = stylinTheme;
    }

    public AnchorPane getMainWindowPane() {
        return mainWindowPane;
    }

    public void setMainWindowPane(AnchorPane mainWindowPane) {
        this.mainWindowPane = mainWindowPane;
    }
}
