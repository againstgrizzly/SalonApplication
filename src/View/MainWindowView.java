package View;

import Controller.SchedulingTabController;
import Model.SchedulingTabModel;
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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sun.applet.Main;
import sun.awt.image.ShortComponentRaster;
import sun.plugin.javascript.navig.Anchor;

/**
 * Created by Brannon on 3/12/2017.
 */
public class MainWindowView {

    private Stage stage;
    private AnchorPane rootPane;
    private BorderPane dividerPane;
    private AnchorPane leftMenu;
    private AnchorPane mainPane;
    private TilePane buttonStackPane;
    private Button homeButton;
    private ImageView homeButtonImage;
    private Button schedulingButton;
    private ImageView schedulingButtonImage;
    private Button clientButton;
    private ImageView clientButtonImage;
    private Button settingsButton;
    private ImageView settingsButtonImage;
    private AnchorPane tabPaneHolder;
    private TabPane tabPane;
    private Tab homeTab;
    private Tab schedulingTab;
    private AnchorPane topBar;
    private Label nameLabel;
    private Label dateLabel;
    private Label timeLabel;
    private Button hamburgerButton;
    private ImageView hamburgerButtonImage;

    private Slider verticalSlider;
    private Slider horizontalSlider;

    private ScrollPane homeTabPane;
    private ScrollPane schedulingScrollPaneTabPane;
    private AnchorPane schedulingTabPane;

    private String stylinTheme = "css/myCss.css";

    private boolean openClose = true;


    public MainWindowView(Stage primaryStage) throws Exception {
        stage = primaryStage;
        rootPane = new AnchorPane();
        stage.setTitle("Stylin");
        stage.setScene(new Scene(rootPane, 1366, 768));
        rootPane.requestFocus();
        mainPane = new AnchorPane();
        mainPane.setStyle("-fx-background-color: #00c712;");

        dividerPane = new BorderPane();

        //Left Border Pane/////////////////////////////////////////////
        leftMenu = new AnchorPane();
        leftMenu.setMaxWidth(200.0);

        buttonStackPane = new TilePane();
        buttonStackPane.setStyle("-fx-background-color: #000000;");
        buttonStackPane.setTileAlignment(Pos.CENTER);
        buttonStackPane.setAlignment(Pos.BOTTOM_LEFT);
        buttonStackPane.setPrefHeight(50.0);

        homeButton = new Button();
        homeButton.getStylesheets().add(stylinTheme);
        homeButtonImage = new ImageView();
        homeButtonImage.setImage(new Image("res/whiteHomeIcon.png"));
        homeButtonImage.setFitHeight(30.0);
        homeButtonImage.setFitWidth(30.0);
        homeButton.setGraphic(homeButtonImage);
        homeButton.setPrefSize(50.0, 50.0);
        homeButton.setMaxSize(50.0, 50.0);
        homeButton.setMinSize(50.0, 50.0);

        schedulingButton = new Button();
        schedulingButton.getStylesheets().add(stylinTheme);
        schedulingButtonImage = new ImageView();
        schedulingButtonImage.setImage(new Image("res/whiteSchedulingIcon.png"));
        schedulingButtonImage.setFitHeight(30.0);
        schedulingButtonImage.setFitWidth(30.0);
        schedulingButton.setGraphic(schedulingButtonImage);
        schedulingButton.setPrefSize(50.0, 50.0);
        schedulingButton.setMaxSize(50.0, 50.0);
        schedulingButton.setMinSize(50.0, 50.0);

        clientButton = new Button();
        clientButton.getStylesheets().add(stylinTheme);
        clientButtonImage = new ImageView();
        clientButtonImage.setImage(new Image("res/whiteClientIcon.png"));
        clientButtonImage.setFitHeight(30.0);
        clientButtonImage.setFitWidth(30.0);
        clientButton.setGraphic(clientButtonImage);
        clientButton.setPrefSize(50.0, 50.0);
        clientButton.setMaxSize(50.0, 50.0);
        clientButton.setMinSize(50.0, 50.0);

        settingsButton = new Button();
        settingsButton.getStylesheets().add(stylinTheme);
        settingsButtonImage = new ImageView();
        settingsButtonImage.setImage(new Image("res/whiteSettingsIcon.png"));
        settingsButtonImage.setFitHeight(30.0);
        settingsButtonImage.setFitWidth(30.0);
        settingsButton.setGraphic(settingsButtonImage);
        settingsButton.setPrefSize(50.0, 50.0);
        settingsButton.setMaxSize(50.0, 50.0);
        settingsButton.setMinSize(50.0, 50.0);

        hamburgerButton = new Button();
        hamburgerButton.getStylesheets().add(stylinTheme);
        hamburgerButtonImage = new ImageView();
        hamburgerButtonImage.setImage(new Image("res/hamburgerIcon.png"));
        hamburgerButtonImage.setFitHeight(30.0);
        hamburgerButtonImage.setFitWidth(30.0);
        hamburgerButton.setGraphic(hamburgerButtonImage);
        hamburgerButton.setPrefSize(50.0, 50.0);
        hamburgerButton.setMaxSize(50.0, 50.0);
        hamburgerButton.setMinSize(50.0, 50.0);

        leftMenu.getChildren().add(hamburgerButton);
        AnchorPane.setTopAnchor(hamburgerButton, 0.0);

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
        dividerPane.getLeft().setStyle("-fx-background-color: #00c7d4;");
        rootPane.getChildren().add(dividerPane);
        AnchorPane.setBottomAnchor(dividerPane, 0.0);
        AnchorPane.setTopAnchor(dividerPane, 0.0);
        AnchorPane.setLeftAnchor(dividerPane, 0.0);
        AnchorPane.setRightAnchor(dividerPane, 0.0);

        ////////////////////////////////////////////////////////////////////////////////////

        //Center Border Pane/////////////////////////////////////////////////////////////////
        topBar = new AnchorPane();
        topBar.setMinHeight(50.0);
        topBar.setMaxHeight(50.0);
        topBar.setPrefHeight(50.0);
        topBar.setStyle("-fx-background-color: #00c7d4;");
        /////////////////////////////////////

        tabPane = new TabPane();
        homeTab = new Tab();
        homeTab.setClosable(false);
        homeTab.setText("Home");
        tabPane.getTabs().add(homeTab);

        schedulingTabPane = new AnchorPane();

        schedulingTab = new Tab();
        schedulingScrollPaneTabPane = new ScrollPane();
        schedulingTabContent(schedulingScrollPaneTabPane); ///////////////////

        schedulingTabPane.getChildren().add(schedulingScrollPaneTabPane);
        AnchorPane.setBottomAnchor(schedulingScrollPaneTabPane, 0.0);
        AnchorPane.setLeftAnchor(schedulingScrollPaneTabPane, 0.0);
        AnchorPane.setRightAnchor(schedulingScrollPaneTabPane, 0.0);
        AnchorPane.setTopAnchor(schedulingScrollPaneTabPane, 0.0);

        verticalSlider = new Slider();
        verticalSlider.setOrientation(Orientation.VERTICAL);
        schedulingTabPane.getChildren().add(verticalSlider);
        AnchorPane.setBottomAnchor(verticalSlider, 50.0);
        AnchorPane.setRightAnchor(verticalSlider, 30.0);


        horizontalSlider = new Slider();
        horizontalSlider.setOrientation(Orientation.HORIZONTAL);
        schedulingTabPane.getChildren().add(horizontalSlider);
        AnchorPane.setBottomAnchor(horizontalSlider, 30.0);
        AnchorPane.setRightAnchor(horizontalSlider, 50.0);



        schedulingTab.setContent(schedulingTabPane);
        schedulingTab.setText("Scheduling");
        tabPane.getTabs().add(schedulingTab);


        //Add Content to homeTab and Scheduling Tab etc.

        AnchorPane tabPaneHolder = new AnchorPane();
        tabPaneHolder.setStyle("-fx-background-color: #ffff00;");


        mainPane = new AnchorPane();
        mainPane.getChildren().add(topBar);
        AnchorPane.setTopAnchor(topBar, 0.0);
        AnchorPane.setLeftAnchor(topBar, 0.0);
        AnchorPane.setRightAnchor(topBar, 0.0);

        mainPane.getChildren().add(tabPane);
        AnchorPane.setTopAnchor(tabPane, 50.0);
        AnchorPane.setBottomAnchor(tabPane, 0.0);
        AnchorPane.setLeftAnchor(tabPane, 0.0);
        AnchorPane.setRightAnchor(tabPane, 0.0);

        dividerPane.setCenter(mainPane);


        stage.show();
    }

    public void homeTabContent(ScrollPane homeTabPane){

    }

    public void topBarContent(AnchorPane topBar, String firstName){
        nameLabel = new Label();
        nameLabel.setText("Welcome, " + firstName);
        nameLabel.setFont(Font.font(24.0));
        nameLabel.setTextFill(Color.WHITE);

        topBar.getChildren().add(nameLabel);
        AnchorPane.setBottomAnchor(nameLabel, 0.0);
        AnchorPane.setTopAnchor(nameLabel, 0.0);
        AnchorPane.setLeftAnchor(nameLabel, 35.0);


    }

    public void schedulingTabContent(ScrollPane schedulingTabPane){
        SchedulingTabView schedulingTabView = new SchedulingTabView(schedulingTabPane, verticalSlider, horizontalSlider);
        SchedulingTabModel schedulingTabModel = new SchedulingTabModel();
        SchedulingTabController schedulingTabController = new SchedulingTabController(schedulingTabModel, schedulingTabView);
    }

    public void hamburgerOpenCloseOperation(){

        if(openClose) {
            leftMenu.setPrefWidth(50.0);
            leftMenu.setMaxWidth(50.0);
            leftMenu.setMinWidth(50.0);
            openClose = false;
        }else{
            leftMenu.setPrefWidth(200.0);
            leftMenu.setMaxWidth(200.0);
            leftMenu.setMinWidth(200.0);
            openClose = true;
        }

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

    public Button getHomeButton() {
        return homeButton;
    }

    public void setHomeButton(Button homeButton) {
        this.homeButton = homeButton;
    }

    public ImageView getHomeButtonImage() {
        return homeButtonImage;
    }

    public void setHomeButtonImage(ImageView homeButtonImage) {
        this.homeButtonImage = homeButtonImage;
    }

    public Button getSchedulingButton() {
        return schedulingButton;
    }

    public void setSchedulingButton(Button schedulingButton) {
        this.schedulingButton = schedulingButton;
    }

    public ImageView getSchedulingButtonImage() {
        return schedulingButtonImage;
    }

    public void setSchedulingButtonImage(ImageView schedulingButtonImage) {
        this.schedulingButtonImage = schedulingButtonImage;
    }

    public Button getClientButton() {
        return clientButton;
    }

    public void setClientButton(Button clientButton) {
        this.clientButton = clientButton;
    }

    public ImageView getClientButtonImage() {
        return clientButtonImage;
    }

    public void setClientButtonImage(ImageView clientButtonImage) {
        this.clientButtonImage = clientButtonImage;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public void setSettingsButton(Button settingsButton) {
        this.settingsButton = settingsButton;
    }

    public ImageView getSettingsButtonImage() {
        return settingsButtonImage;
    }

    public void setSettingsButtonImage(ImageView settingsButtonImage) {
        this.settingsButtonImage = settingsButtonImage;
    }

    public AnchorPane getTabPaneHolder() {
        return tabPaneHolder;
    }

    public void setTabPaneHolder(AnchorPane tabPaneHolder) {
        this.tabPaneHolder = tabPaneHolder;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public Tab getHomeTab() {
        return homeTab;
    }

    public void setHomeTab(Tab homeTab) {
        this.homeTab = homeTab;
    }

    public Tab getSchedulingTab() {
        return schedulingTab;
    }

    public void setSchedulingTab(Tab schedulingTab) {
        this.schedulingTab = schedulingTab;
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
}
