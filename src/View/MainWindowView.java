package View;

import Controller.AddAppointmentWindowController;
import Controller.MainWindowController;
import MiscObjects.Employee;
import Model.AddAppointmentWindowModel;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.scene.control.LocalDatePicker;
import sun.plugin.javascript.navig.Anchor;

import java.time.LocalDate;


/**
 * Created by Brannon on 3/12/2017.
 */
public class MainWindowView {

    private Employee employee;
    private GaussianBlur gaussianBlur;

    private Stage stage;
    private AnchorPane rootPane;
    private AnchorPane differentScreenContainer;
    private BorderPane dividerPane;
    private AnchorPane leftMenu;////////LEft menu
    private AnchorPane mainPane;
    private TilePane buttonStackPane;
    private ToggleButton homeButton;
    private ImageView homeButtonImage;
    private ToggleButton schedulingButton;
    private ImageView schedulingButtonImage;
    private ToggleButton clientButton;
    private ImageView clientButtonImage;
    private ToggleButton settingsButton;
    private ImageView settingsButtonImage;
    private AnchorPane homeScreen;
    private AnchorPane schedulingScreen;
    private AnchorPane clientScreen;
    private AnchorPane topBar;
    private Label nameLabel;
    private Label dateLabel;
    private Label timeLabel;
    private Button hamburgerButton;
    private ImageView hamburgerButtonImage;
    private ToggleGroup toggleGroup;
    private AnchorPane blockerPane; //this pane blocks the user from clicking main gui elements when the login screen is up
    private Label newAppointmentLabel;
    private Button newAppointmentButton;

    private ImageView plusImage;

    private AnchorPane loginPane;

    private Slider verticalSlider;
    private Slider horizontalSlider;

    private ScrollPane homeTabPane;
    private AnchorPane schedulingTabPane;

    private LocalDatePicker schedulingDatePicker;

    private String stylinTheme = "css/StylinThemeCalmPro.css";

    private boolean open = false;

    double buttonAndMenuCollapsedSize = 50.0;
    double innerImageSize = 30.0;


    public MainWindowView(Stage stage, LoginView loginView) throws Exception {
        rootPane = new AnchorPane();//This is the base pane for everything
        stage.setTitle("Stylin");
        this.stage = stage;
        Scene scene = new Scene(rootPane, 1366, 768);
        scene.getStylesheets().add(stylinTheme);
        stage.setScene(scene);
        rootPane.requestFocus();
        loadMainWindowGui();

        //Blocks the main gui during login
        blockerPane = new AnchorPane();
        AnchorPane.setBottomAnchor(blockerPane, 0.0);
        AnchorPane.setLeftAnchor(blockerPane, 0.0);
        AnchorPane.setRightAnchor(blockerPane, 0.0);
        AnchorPane.setTopAnchor(blockerPane, 0.0);
        rootPane.getChildren().add(blockerPane);

        //load login gui
        loadLoginGui(loginView);
        gaussianBlur = new GaussianBlur(100);
        dividerPane.setEffect(gaussianBlur);


        stage.show();
    }

    public void loadLoginGui(LoginView loginView) {
        loginPane = new AnchorPane();
        loginPane.setPrefSize(loginView.getRoot().getPrefWidth(), loginView.getRoot().getPrefHeight());
        System.out.println(loginView.getRoot().getPrefWidth());
        loginPane.getChildren().add(loginView.getRoot());

        loginPane.setLayoutX(rootPane.getWidth() / 2 - loginView.getRoot().getPrefWidth() / 2);
        loginPane.setLayoutY(rootPane.getHeight() / 2 - loginView.getRoot().getPrefHeight() / 2);


        rootPane.widthProperty().addListener(e -> {
            loginPane.setLayoutX(rootPane.getWidth() / 2 - loginPane.getWidth() / 2);
            System.out.println(rootPane.getWidth() / 2 - loginPane.getWidth() / 2);
        });

        rootPane.heightProperty().addListener(e -> {
            loginPane.setLayoutY((rootPane.getHeight() / 2 - loginPane.getHeight() / 2));
        });

        rootPane.getChildren().add(loginPane);
    }

    public void loadMainWindowGui() {
        mainPane = new AnchorPane();
        mainPane.setStyle("-fx-background-color: #00c712;");

        dividerPane = new BorderPane();

        //LeftBorderPane aka the Left menu controlled by the hamburger button
        leftMenuPane();

        //Center Border Pane/////////////////////////////////////////////////////////////////
        topBar = new AnchorPane();
        topBar.setMinHeight(buttonAndMenuCollapsedSize);
        topBar.setMaxHeight(buttonAndMenuCollapsedSize);
        topBar.setPrefHeight(buttonAndMenuCollapsedSize);
        topBar.setStyle("-fx-background-color: #55606e");
        /////////////////////////////////////


        //Home Screen
        homeScreen = new AnchorPane();

        //Scheduling Screen///////////////////////////////
        schedulingScreen = new AnchorPane();

        verticalSlider = new Slider();
        verticalSlider.setOrientation(Orientation.VERTICAL);
        AnchorPane.setBottomAnchor(verticalSlider, 50.0);
        AnchorPane.setRightAnchor(verticalSlider, 30.0);
        verticalSlider.setMax(4000.0);
        verticalSlider.setMin(200);

        horizontalSlider = new Slider();
        horizontalSlider.setOrientation(Orientation.HORIZONTAL);
        AnchorPane.setBottomAnchor(horizontalSlider, 30.0);
        AnchorPane.setRightAnchor(horizontalSlider, 50.0);
        horizontalSlider.setMax(4000.0);
        horizontalSlider.setMin(600);

        //Client Screen
        clientScreen = new AnchorPane();
////////////////////////////////////////////////////////////////////////////

        //Add topBar to mainPane
        mainPane = new AnchorPane();
        mainPane.getChildren().add(topBar);
        AnchorPane.setTopAnchor(topBar, 0.0);
        AnchorPane.setLeftAnchor(topBar, 0.0);
        AnchorPane.setRightAnchor(topBar, 0.0);

        //Create different screen container
        differentScreenContainer = new AnchorPane();
        differentScreenContainer.setStyle(" -fx-background-color: ##d2d4d8");//orange


        //add the home screen as initial default screen
        addHomeScreenToScreenContainer();


        //Adds the screen container to the mainPane
        //The mainPane has the screenContainer and the topBar
        //the screenContainer sits below the topBar
        mainPane.getChildren().add(differentScreenContainer);
        AnchorPane.setTopAnchor(differentScreenContainer, buttonAndMenuCollapsedSize);
        AnchorPane.setBottomAnchor(differentScreenContainer, 0.0);
        AnchorPane.setRightAnchor(differentScreenContainer, 0.0);
        AnchorPane.setLeftAnchor(differentScreenContainer, 0.0);


        //This sets the mainPane to the center of
        //BorderPane dividerPane
        dividerPane.setCenter(mainPane);

    }

    public void addHomeScreenToScreenContainer() {
        differentScreenContainer.getChildren().add(homeScreen);
        AnchorPane.setTopAnchor(homeScreen, 0.0);
        AnchorPane.setBottomAnchor(homeScreen, 0.0);
        AnchorPane.setLeftAnchor(homeScreen, 0.0);
        AnchorPane.setRightAnchor(homeScreen, 0.0);
    }

    public void addSchedulingScreenToScreenContainer() {
        differentScreenContainer.getChildren().clear();
        differentScreenContainer.getChildren().add(schedulingScreen);
        AnchorPane.setTopAnchor(schedulingScreen, 0.0);
        AnchorPane.setBottomAnchor(schedulingScreen, 0.0);
        AnchorPane.setLeftAnchor(schedulingScreen, 0.0);
        AnchorPane.setRightAnchor(schedulingScreen, 0.0);
        differentScreenContainer.getChildren().add(horizontalSlider);
        differentScreenContainer.getChildren().add(verticalSlider);
    }

    public void addClientScreenToScreenContainer(){
        differentScreenContainer.getChildren().clear();
        differentScreenContainer.getChildren().add(clientScreen);
        AnchorPane.setTopAnchor(clientScreen, 0.0);
        AnchorPane.setBottomAnchor(clientScreen, 0.0);
        AnchorPane.setLeftAnchor(clientScreen, 0.0);
        AnchorPane.setRightAnchor(clientScreen, 0.0);
    }


    public void topBarContent(String firstName, String lastName) {

        //Add user name to Top Bar
        nameLabel = new Label();
        nameLabel.setText(firstName + " " + lastName);
        nameLabel.setFont(Font.font(24.0));
        nameLabel.setTextFill(Color.WHITE);

        topBar.getChildren().add(nameLabel);
        AnchorPane.setBottomAnchor(nameLabel, 0.0);
        AnchorPane.setTopAnchor(nameLabel, 0.0);
        AnchorPane.setLeftAnchor(nameLabel, 35.0);


    }


    public void hamburgerOpenCloseOperation(MainWindowController.SelectedPane selectedPane) {
        if (open) {
            leftMenu.setPrefWidth(50.0);
            leftMenu.setMaxWidth(50.0);
            leftMenu.setMinWidth(50.0);
            open = false;
            leftMenuPaneCustomComponents(selectedPane);

        } else {
            leftMenu.setPrefWidth(260.0);
            leftMenu.setMaxWidth(260.0);
            leftMenu.setMinWidth(260.0);
            open = true;
            leftMenuPaneCustomComponents(selectedPane);
        }
        System.out.println("Is open: " + open);
    }

    public void leftMenuPane() {


        //Left Border Pane/////////////////////////////////////////////
        leftMenu = new AnchorPane();
        leftMenu.setMaxWidth(buttonAndMenuCollapsedSize);
        leftMenu.setStyle("-fx-background-color: #e9e9e9;");

        schedulingDatePicker = new LocalDatePicker();
        schedulingDatePicker.setPrefWidth(240.0);
        schedulingDatePicker.setLocalDate(LocalDate.now());

        buttonStackPane = new TilePane();
        buttonStackPane.setStyle("-fx-background-color: #e9e9e9;");
        buttonStackPane.setTileAlignment(Pos.CENTER);
        buttonStackPane.setAlignment(Pos.BOTTOM_LEFT);
        buttonStackPane.setPrefHeight(buttonAndMenuCollapsedSize);

        toggleGroup = new ToggleGroup();

        //<editor-fold desc="Home, Scheduling, Client, Settings, Hamburger button instantiations">
        homeButton = new ToggleButton();
        homeButtonImage = new ImageView();
        homeButtonImage.setImage(new Image("res/whiteHomeIcon.png"));
        homeButtonImage.setFitHeight(innerImageSize);
        homeButtonImage.setFitWidth(innerImageSize);
        homeButton.setGraphic(homeButtonImage);
        homeButton.setPrefSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);
        homeButton.setMaxSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);
        homeButton.setMinSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);
        homeButton.setSelected(true);

        homeButton.setToggleGroup(toggleGroup);

        schedulingButton = new ToggleButton();
        schedulingButtonImage = new ImageView();
        schedulingButtonImage.setImage(new Image("res/whiteSchedulingIcon.png"));
        schedulingButtonImage.setFitHeight(innerImageSize);
        schedulingButtonImage.setFitWidth(innerImageSize);
        schedulingButton.setGraphic(schedulingButtonImage);
        schedulingButton.setPrefSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);
        schedulingButton.setMaxSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);
        schedulingButton.setMinSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);
        schedulingButton.setToggleGroup(toggleGroup);


        clientButton = new ToggleButton();
        clientButtonImage = new ImageView();
        clientButtonImage.setImage(new Image("res/whiteClientIcon.png"));
        clientButtonImage.setFitHeight(innerImageSize);
        clientButtonImage.setFitWidth(innerImageSize);
        clientButton.setGraphic(clientButtonImage);
        clientButton.setPrefSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);
        clientButton.setMaxSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);
        clientButton.setMinSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);
        clientButton.setToggleGroup(toggleGroup);

        settingsButton = new ToggleButton();
        settingsButtonImage = new ImageView();
        settingsButtonImage.setImage(new Image("res/whiteSettingsIcon.png"));
        settingsButtonImage.setFitHeight(innerImageSize);
        settingsButtonImage.setFitWidth(innerImageSize);
        settingsButton.setGraphic(settingsButtonImage);
        settingsButton.setPrefSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);
        settingsButton.setMaxSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);
        settingsButton.setMinSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);
        settingsButton.setToggleGroup(toggleGroup);

        hamburgerButton = new Button();
        hamburgerButtonImage = new ImageView();
        hamburgerButtonImage.setImage(new Image("res/hamburgerIcon.png"));
        hamburgerButtonImage.setFitHeight(innerImageSize);
        hamburgerButtonImage.setFitWidth(innerImageSize);
        hamburgerButton.setGraphic(hamburgerButtonImage);
        hamburgerButton.setPrefSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);
        hamburgerButton.setMaxSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);
        hamburgerButton.setMinSize(buttonAndMenuCollapsedSize, buttonAndMenuCollapsedSize);

        newAppointmentButton = new Button();
        plusImage = new ImageView();
        plusImage.setImage(new Image("res/addAppointmentIcon.png"));
        plusImage.setFitHeight(innerImageSize);
        plusImage.setFitWidth(innerImageSize);
        newAppointmentButton.setPrefHeight(buttonAndMenuCollapsedSize);
        newAppointmentLabel = new Label("Add Appointment");
        newAppointmentButton.setGraphic(plusImage);
        leftMenu.getChildren().add(newAppointmentButton);
        AnchorPane.setLeftAnchor(newAppointmentButton, 0.0);
        AnchorPane.setRightAnchor(newAppointmentButton, 0.0);
        AnchorPane.setTopAnchor(newAppointmentButton, 300.0);


        //</editor-fold>


        leftMenu.getChildren().add(hamburgerButton);
        AnchorPane.setTopAnchor(hamburgerButton, 0.0);

        //Dont add datepicker to pane initially

        buttonStackPane.getChildren().add(0, homeButton);
        buttonStackPane.getChildren().add(1, schedulingButton);
        buttonStackPane.getChildren().add(2, clientButton);
        buttonStackPane.getChildren().add(3, settingsButton);

        leftMenu.getChildren().add(buttonStackPane);
        AnchorPane.setBottomAnchor(buttonStackPane, 0.0);
        AnchorPane.setLeftAnchor(buttonStackPane, 0.0);
        AnchorPane.setRightAnchor(buttonStackPane, 0.0);


        leftMenu.setPrefSize(200.0, 300.0);
        dividerPane.setLeft(leftMenu);
        dividerPane.setRight(mainPane);
        dividerPane.getLeft().setStyle("-fx-background-color: #e9e9e9;");
        rootPane.getChildren().add(dividerPane);
        AnchorPane.setBottomAnchor(dividerPane, 0.0);
        AnchorPane.setTopAnchor(dividerPane, 0.0);
        AnchorPane.setLeftAnchor(dividerPane, 0.0);
        AnchorPane.setRightAnchor(dividerPane, 0.0);
        ////////////////////////////////////////////////////////////////////////////////////
    }

    public void logMeIn(Employee employee) {
        this.employee = employee;
        topBarContent(employee.getF_name(), employee.getL_name());
        System.out.println("Logging in");
        Timeline timeline = new Timeline();

        KeyValue blurValue = new KeyValue(gaussianBlur.radiusProperty(), 0.0);
        KeyFrame blurFrame = new KeyFrame(Duration.millis(1000), blurValue);

        KeyValue opacityValue = new KeyValue(loginPane.opacityProperty(), 0.0);
        KeyFrame opacityFrame = new KeyFrame(Duration.millis(1000), opacityValue);

        timeline.getKeyFrames().addAll(blurFrame, opacityFrame);


        timeline.setOnFinished(e -> {
            dividerPane.setEffect(null);
            rootPane.getChildren().remove(loginPane);
            rootPane.getChildren().remove(blockerPane);
        });
        timeline.play();

    }


    //This method handles changing the left pane to custom components for the currently selected
    //tab
    public void leftMenuPaneCustomComponents(MainWindowController.SelectedPane selected) {
        leftMenu.getChildren().clear();
        leftMenu.getChildren().add(hamburgerButton);
        leftMenu.getChildren().add(buttonStackPane);
        leftMenu.getChildren().add(newAppointmentButton);

        switch (selected) {
            case HOME:
                homeLeftMenuInterface();
                break;
            case SCHEDULING:
                schedulingLeftMenuInterface();
                break;
            case CLIENT:
                clientLeftMenuInterface();
                break;
            case SETTINGS:
                settingsLeftMenuInterface();
                break;
            default:
                System.out.println("DEFAULT");
                break;
        }
    }

    public void homeLeftMenuInterface() {

        if (open) {//if the left scheduling pane is open add the datepicker to the pane

            leftMenu.getChildren().add(schedulingDatePicker); //add the date picker to left pane
            AnchorPane.setTopAnchor(schedulingDatePicker, 100.0); //positioning
            AnchorPane.setLeftAnchor(schedulingDatePicker, 10.0);//positioning
            AnchorPane.setRightAnchor(schedulingDatePicker, 10.0);//positioning
        } else {//else (pane is closed) remove the scheduling date picker
            if (leftMenu.getChildren().contains(schedulingDatePicker)) {
                leftMenu.getChildren().remove(schedulingDatePicker);

            }
        }


    }

    //This method handles the adding and removing of components on the left
    //menu when on the scheduling tab when we open and close the menu. Right it only has the Date Picker
    //but if we decide to add more stuff this will handle it
    public void schedulingLeftMenuInterface() {

        if (open) {//if the left scheduling pane is open add the datepicker to the pane

            leftMenu.getChildren().add(schedulingDatePicker); //add the date picker to left pane
            AnchorPane.setTopAnchor(schedulingDatePicker, 100.0); //positioning
            AnchorPane.setLeftAnchor(schedulingDatePicker, 10.0);//positioning
            AnchorPane.setRightAnchor(schedulingDatePicker, 10.0);//positioning
        } else {//else (pane is closed) remove the scheduling date picker
            if (leftMenu.getChildren().contains(schedulingDatePicker)) {
                leftMenu.getChildren().remove(schedulingDatePicker);

            }


            //For now, do not add anything
        }
    }

    public void clientLeftMenuInterface() {

        if (open) {//if the left scheduling pane is open add the datepicker to the pane

            leftMenu.getChildren().add(schedulingDatePicker); //add the date picker to left pane
            AnchorPane.setTopAnchor(schedulingDatePicker, 100.0); //positioning
            AnchorPane.setLeftAnchor(schedulingDatePicker, 10.0);//positioning
            AnchorPane.setRightAnchor(schedulingDatePicker, 10.0);//positioning
        } else {//else (pane is closed) remove the scheduling date picker
            if (leftMenu.getChildren().contains(schedulingDatePicker)) {
                leftMenu.getChildren().remove(schedulingDatePicker);

            }
        }

    }

    public void settingsLeftMenuInterface() {
        if (open) {//if the left scheduling pane is open add the datepicker to the pane

            leftMenu.getChildren().add(schedulingDatePicker); //add the date picker to left pane
            AnchorPane.setTopAnchor(schedulingDatePicker, 100.0); //positioning
            AnchorPane.setLeftAnchor(schedulingDatePicker, 10.0);//positioning
            AnchorPane.setRightAnchor(schedulingDatePicker, 10.0);//positioning
        } else {//else (pane is closed) remove the scheduling date picker
            if (leftMenu.getChildren().contains(schedulingDatePicker)) {
                leftMenu.getChildren().remove(schedulingDatePicker);

            }
        }
    }

    public void loadAddAppointmentWindowView(){
        java.util.Date date = java.sql.Date.valueOf(schedulingDatePicker.getLocalDate());

        AddAppointmentWindowView  addAppointmentWindowView = new AddAppointmentWindowView(employee, date);
        AddAppointmentWindowModel addAppointmentWindowModel = new AddAppointmentWindowModel();
        AddAppointmentWindowController addAppointmentWindowController = new AddAppointmentWindowController(addAppointmentWindowModel, addAppointmentWindowView, employee);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public AnchorPane getRootPane() {
        return rootPane;
    }

    public void setRootPane(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    public BorderPane getDividerPane() {
        return dividerPane;
    }

    public void setDividerPane(BorderPane dividerPane) {
        this.dividerPane = dividerPane;
    }

    public AnchorPane getLeftMenu() {
        return leftMenu;
    }

    public void setLeftMenu(AnchorPane leftMenu) {
        this.leftMenu = leftMenu;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(AnchorPane mainPane) {
        this.mainPane = mainPane;
    }

    public TilePane getButtonStackPane() {
        return buttonStackPane;
    }

    public void setButtonStackPane(TilePane buttonStackPane) {
        this.buttonStackPane = buttonStackPane;
    }

    public ToggleButton getHomeButton() {
        return homeButton;
    }

    public void setHomeButton(ToggleButton homeButton) {
        this.homeButton = homeButton;
    }

    public ImageView getHomeButtonImage() {
        return homeButtonImage;
    }

    public void setHomeButtonImage(ImageView homeButtonImage) {
        this.homeButtonImage = homeButtonImage;
    }

    public ToggleButton getSchedulingButton() {
        return schedulingButton;
    }

    public void setSchedulingButton(ToggleButton schedulingButton) {
        this.schedulingButton = schedulingButton;
    }

    public ImageView getSchedulingButtonImage() {
        return schedulingButtonImage;
    }

    public void setSchedulingButtonImage(ImageView schedulingButtonImage) {
        this.schedulingButtonImage = schedulingButtonImage;
    }

    public ToggleButton getClientButton() {
        return clientButton;
    }

    public void setClientButton(ToggleButton clientButton) {
        this.clientButton = clientButton;
    }

    public ImageView getClientButtonImage() {
        return clientButtonImage;
    }

    public void setClientButtonImage(ImageView clientButtonImage) {
        this.clientButtonImage = clientButtonImage;
    }

    public ToggleButton getSettingsButton() {
        return settingsButton;
    }

    public void setSettingsButton(ToggleButton settingsButton) {
        this.settingsButton = settingsButton;
    }

    public ImageView getSettingsButtonImage() {
        return settingsButtonImage;
    }

    public void setSettingsButtonImage(ImageView settingsButtonImage) {
        this.settingsButtonImage = settingsButtonImage;
    }

    public AnchorPane getTopBar() {
        return topBar;
    }

    public void setTopBar(AnchorPane topBar) {
        this.topBar = topBar;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        this.nameLabel = nameLabel;
    }

    public Label getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(Label dateLabel) {
        this.dateLabel = dateLabel;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    public Button getHamburgerButton() {
        return hamburgerButton;
    }

    public void setHamburgerButton(Button hamburgerButton) {
        this.hamburgerButton = hamburgerButton;
    }

    public ImageView getHamburgerButtonImage() {
        return hamburgerButtonImage;
    }

    public void setHamburgerButtonImage(ImageView hamburgerButtonImage) {
        this.hamburgerButtonImage = hamburgerButtonImage;
    }

    public AnchorPane getDifferentScreenContainer() {
        return differentScreenContainer;
    }

    public void setDifferentScreenContainer(AnchorPane differentScreenContainer) {
        this.differentScreenContainer = differentScreenContainer;
    }

    public AnchorPane getHomeScreen() {
        return homeScreen;
    }

    public void setHomeScreen(AnchorPane homeScreen) {
        this.homeScreen = homeScreen;
    }

    public Slider getVerticalSlider() {
        return verticalSlider;
    }

    public void setVerticalSlider(Slider verticalSlider) {
        this.verticalSlider = verticalSlider;
    }

    public Slider getHorizontalSlider() {
        return horizontalSlider;
    }

    public void setHorizontalSlider(Slider horizontalSlider) {
        this.horizontalSlider = horizontalSlider;
    }

    public ScrollPane getHomeTabPane() {
        return homeTabPane;
    }

    public void setHomeTabPane(ScrollPane homeTabPane) {
        this.homeTabPane = homeTabPane;
    }


    public AnchorPane getSchedulingTabPane() {
        return schedulingTabPane;
    }

    public void setSchedulingTabPane(AnchorPane schedulingTabPane) {
        this.schedulingTabPane = schedulingTabPane;
    }

    public String getStylinTheme() {
        return stylinTheme;
    }

    public void setStylinTheme(String stylinTheme) {
        this.stylinTheme = stylinTheme;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean openClose) {
        this.open = openClose;
    }

    public AnchorPane getSchedulingScreen() {
        return schedulingScreen;
    }

    public void setSchedulingScreen(AnchorPane schedulingScreen) {
        this.schedulingScreen = schedulingScreen;
    }

    public LocalDatePicker getSchedulingDatePicker() {
        return schedulingDatePicker;
    }

    public void setSchedulingDatePicker(LocalDatePicker schedulingDatePicker) {
        this.schedulingDatePicker = schedulingDatePicker;
    }

    public Button getNewAppointmentButton() {
        return newAppointmentButton;
    }

    public void setNewAppointmentButton(Button newAppointmentButton) {
        this.newAppointmentButton = newAppointmentButton;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public GaussianBlur getGaussianBlur() {
        return gaussianBlur;
    }

    public void setGaussianBlur(GaussianBlur gaussianBlur) {
        this.gaussianBlur = gaussianBlur;
    }

    public AnchorPane getClientScreen() {
        return clientScreen;
    }

    public void setClientScreen(AnchorPane clientScreen) {
        this.clientScreen = clientScreen;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }

    public AnchorPane getBlockerPane() {
        return blockerPane;
    }

    public void setBlockerPane(AnchorPane blockerPane) {
        this.blockerPane = blockerPane;
    }

    public Label getNewAppointmentLabel() {
        return newAppointmentLabel;
    }

    public void setNewAppointmentLabel(Label newAppointmentLabel) {
        this.newAppointmentLabel = newAppointmentLabel;
    }

    public ImageView getPlusImage() {
        return plusImage;
    }

    public void setPlusImage(ImageView plusImage) {
        this.plusImage = plusImage;
    }

    public AnchorPane getLoginPane() {
        return loginPane;
    }

    public void setLoginPane(AnchorPane loginPane) {
        this.loginPane = loginPane;
    }

    public double getButtonAndMenuCollapsedSize() {
        return buttonAndMenuCollapsedSize;
    }

    public void setButtonAndMenuCollapsedSize(double buttonAndMenuCollapsedSize) {
        this.buttonAndMenuCollapsedSize = buttonAndMenuCollapsedSize;
    }

    public double getInnerImageSize() {
        return innerImageSize;
    }

    public void setInnerImageSize(double innerImageSize) {
        this.innerImageSize = innerImageSize;
    }
}
