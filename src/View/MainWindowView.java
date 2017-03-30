package View;

import Controller.MainWindowController;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalDatePicker;
import sun.plugin.javascript.navig.Anchor;


/**
 * Created by Brannon on 3/12/2017.
 */
public class MainWindowView {

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
    private ScrollPane schedulingScreen;
    private AnchorPane topBar;
    private Label nameLabel;
    private Label dateLabel;
    private Label timeLabel;
    private Button hamburgerButton;
    private ImageView hamburgerButtonImage;
    private ToggleGroup toggleGroup;

    private Slider verticalSlider;
    private Slider horizontalSlider;

    private ScrollPane homeTabPane;
    private ScrollPane schedulingScrollPaneTabPane;
    private AnchorPane schedulingTabPane;

    private LocalDatePicker schedulingDatePicker;

    private String stylinTheme = "css/StylinThemeCalmPro.css";

    private boolean open = false;

    double buttonAndMenuCollapsedSize = 50.0;
    double innerImageSize = 30.0;


    public MainWindowView(Stage primaryStage) throws Exception {
        stage = primaryStage;
        rootPane = new AnchorPane();
        stage.setTitle("Stylin");
        Scene scene = new Scene(rootPane, 1366, 768);
        scene.getStylesheets().add(stylinTheme);
        stage.setScene(scene);
        rootPane.requestFocus();
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
        topBar.setStyle("-fx-background-color: #565656");
        /////////////////////////////////////


        //Home Screen
        homeScreen = new AnchorPane();


        //Scheduling Screen
        schedulingScreen = new ScrollPane();

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


        //Add topBar to mainPane
        mainPane = new AnchorPane();
        mainPane.getChildren().add(topBar);
        AnchorPane.setTopAnchor(topBar, 0.0);
        AnchorPane.setLeftAnchor(topBar, 0.0);
        AnchorPane.setRightAnchor(topBar, 0.0);

        //Create different screen container
        differentScreenContainer = new AnchorPane();
        differentScreenContainer.setStyle(" -fx-background-color: #ffae00");//orange


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


        stage.show();
    }

    public void addHomeScreenToScreenContainer() {
        differentScreenContainer.getChildren().add(homeScreen);
        AnchorPane.setTopAnchor(homeScreen, 0.0);
        AnchorPane.setBottomAnchor(homeScreen, 0.0);
        AnchorPane.setLeftAnchor(homeScreen, 0.0);
        AnchorPane.setRightAnchor(homeScreen, 0.0);
    }

    public void addSchedulingScreenToScreenContainer() {
        differentScreenContainer.getChildren().add(schedulingScreen);
        AnchorPane.setTopAnchor(schedulingScreen, 0.0);
        AnchorPane.setBottomAnchor(schedulingScreen, 0.0);
        AnchorPane.setLeftAnchor(schedulingScreen, 0.0);
        AnchorPane.setRightAnchor(schedulingScreen, 0.0);
        differentScreenContainer.getChildren().add(horizontalSlider);
        differentScreenContainer.getChildren().add(verticalSlider);
    }


    public void topBarContent(AnchorPane topBar, String firstName, String lastName) {

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

        //</editor-fold>


        leftMenu.getChildren().add(hamburgerButton);
        AnchorPane.setTopAnchor(hamburgerButton, 0.0);

        //Dont add datepicker to pane initially

        leftMenu.getChildren().add(buttonStackPane);
        AnchorPane.setBottomAnchor(buttonStackPane, 0.0);
        AnchorPane.setLeftAnchor(buttonStackPane, 0.0);
        AnchorPane.setRightAnchor(buttonStackPane, 0.0);

        buttonStackPane.getChildren().add(0, homeButton);
        buttonStackPane.getChildren().add(1, schedulingButton);
        buttonStackPane.getChildren().add(2, clientButton);
        buttonStackPane.getChildren().add(3, settingsButton);

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

    //This method handles changing the left pane to custom components for the currently selected
    //tab
    public void leftMenuPaneCustomComponents(MainWindowController.SelectedPane selected) {
        leftMenu.getChildren().clear();
        leftMenu.getChildren().add(hamburgerButton);
        leftMenu.getChildren().add(buttonStackPane);

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


    }

    public void schedulingLeftMenuInterface() {
        if(open){

            leftMenu.getChildren().add(schedulingDatePicker);
            AnchorPane.setTopAnchor(schedulingDatePicker, 100.0);
            AnchorPane.setLeftAnchor(schedulingDatePicker, 10.0);
            AnchorPane.setRightAnchor(schedulingDatePicker, 10.0);
        }else{
            if(leftMenu.getChildren().contains(schedulingDatePicker)){
                leftMenu.getChildren().remove(schedulingDatePicker);
            }
            //For now, do not add anything
        }
    }

    public void clientLeftMenuInterface() {

    }

    public void settingsLeftMenuInterface() {

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

    public ScrollPane getSchedulingScrollPaneTabPane() {
        return schedulingScrollPaneTabPane;
    }

    public void setSchedulingScrollPaneTabPane(ScrollPane schedulingScrollPaneTabPane) {
        this.schedulingScrollPaneTabPane = schedulingScrollPaneTabPane;
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

    public ScrollPane getSchedulingScreen() {
        return schedulingScreen;
    }

    public void setSchedulingScreen(ScrollPane schedulingScreen) {
        this.schedulingScreen = schedulingScreen;
    }

    public LocalDatePicker getSchedulingDatePicker() {
        return schedulingDatePicker;
    }

    public void setSchedulingDatePicker(LocalDatePicker schedulingDatePicker) {
        this.schedulingDatePicker = schedulingDatePicker;
    }
}
