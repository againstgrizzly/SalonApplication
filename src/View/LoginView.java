package View;

import aurelienribon.tweenengine.Tween;
import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.transform.Translate;
import javafx.util.Duration;


// This is the View
// Its only job is to display what the user sees
// It performs no calculations, but instead passes
// information entered by the user to whomever needs
// it.

public class LoginView {

    private String stylinTheme = "css/StylinThemeCalmPro.css";
    private Button forgotButton;
    private TextField username;
    private TextField pin;
    private Button loginButton;
    private AnchorPane root;

    private Button resetUsername;
    private Button resetPin;
    private Button backButton;

    private TextField firstname;
    private TextField lastname;
    private Button verifyButton;

    private Label firstLabel;
    private Label lastLabel;
    private Button resetButton;
    private TextField dateOfBirth;
    private Label DOBLabel;


    public LoginView() throws Exception {
        root = new AnchorPane();
        root.setPrefSize(300, 350);
        root.setStyle("-fx-background-color: #e9e9e9");


        loginSetup();
    }

    public void loginSetup(){
        username = new TextField();
        username.setPromptText("Please Enter Username");
        AnchorPane.setTopAnchor(username,50.0);
        AnchorPane.setRightAnchor(username,40.0);
        AnchorPane.setLeftAnchor(username,40.0);
        username.setMinHeight(40);
        textLimiter(username, 15);//limit username to 15 characters (random number I chose)

        pin = new PasswordField();
        pin.setPromptText("Please Enter PIN");
        AnchorPane.setTopAnchor(pin,100.0);
        AnchorPane.setRightAnchor(pin,40.0);
        AnchorPane.setLeftAnchor(pin,40.0);
        pin.setMinHeight(40);
        textLimiter(pin, 4);
        limitToNumeric(pin);

        loginButton = new Button();
        loginButton.getStylesheets().add(stylinTheme);
        loginButton.setText("Login");
        loginButton.setMaxWidth(200);
        loginButton.setMinHeight(50);
        AnchorPane.setTopAnchor(loginButton,175.0);
        AnchorPane.setRightAnchor(loginButton,40.0);
        AnchorPane.setLeftAnchor(loginButton,40.0);

        forgotButton = new Button();
        forgotButton.getStylesheets().add(stylinTheme);
        forgotButton.setText("Forgot Username/PIN");
        forgotButton.setMaxWidth(200);
        forgotButton.setMinHeight(50);
        AnchorPane.setTopAnchor(forgotButton,250.0);
        AnchorPane.setRightAnchor(forgotButton,40.0);
        AnchorPane.setLeftAnchor(forgotButton,40.0);

        root.getChildren().addAll(username, pin, loginButton, forgotButton);


    }

    public void loadResetGui(){
        resetUsername = new Button();
        resetUsername.getStylesheets().clear();
        resetUsername.getStylesheets().add(stylinTheme);
        resetUsername.setText("Forgot Username");
        resetUsername.setMaxWidth(200);
        resetUsername.setMinHeight(50);
        AnchorPane.setTopAnchor(resetUsername, 50.0);
        AnchorPane.setRightAnchor(resetUsername, 40.0);
        AnchorPane.setLeftAnchor(resetUsername, 40.0);

        resetPin = new Button();
        resetPin.getStylesheets().clear();
        resetPin.getStylesheets().add(stylinTheme);
        resetPin.setText("Forgot PIN");
        resetPin.setMaxWidth(200);
        resetPin.setMinHeight(50);
        AnchorPane.setTopAnchor(resetPin, 130.0);
        AnchorPane.setRightAnchor(resetPin, 40.0);
        AnchorPane.setLeftAnchor(resetPin, 40.0);

        backButton = new Button();
        backButton.getStylesheets().clear();
        backButton.getStylesheets().add(stylinTheme);
        backButton.setText("Back");
        backButton.setMaxWidth(200);
        backButton.setMinHeight(50);
        AnchorPane.setTopAnchor(backButton, 210.0);
        AnchorPane.setRightAnchor(backButton, 40.0);
        AnchorPane.setLeftAnchor(backButton, 40.0);

        root.getChildren().addAll(resetUsername, resetPin, backButton);

    }
    public void loadForgotUsername(){
        firstLabel = new Label();
        firstLabel.setText("Please Enter Your First Name Below:");
        AnchorPane.setTopAnchor(firstLabel, 20.0);
        AnchorPane.setRightAnchor(firstLabel, 40.0);
        AnchorPane.setLeftAnchor(firstLabel, 40.0);

        firstname = new TextField();
        firstname.setMinHeight(40);
        AnchorPane.setTopAnchor(firstname, 40.0);
        AnchorPane.setRightAnchor(firstname, 40.0);
        AnchorPane.setLeftAnchor(firstname, 40.0);
        textLimiter(firstname, 20);

        lastLabel = new Label();
        lastLabel.setText("Please Enter Your Last Name Below:");
        AnchorPane.setTopAnchor(lastLabel, 100.0);
        AnchorPane.setRightAnchor(lastLabel, 40.0);
        AnchorPane.setLeftAnchor(lastLabel, 40.0);

        lastname = new TextField();
        lastname.setMinHeight(40);
        AnchorPane.setTopAnchor(lastname, 120.0);
        AnchorPane.setRightAnchor(lastname, 40.0);
        AnchorPane.setLeftAnchor(lastname, 40.0);
        textLimiter(lastname, 20);

        DOBLabel = new Label();
        DOBLabel.setText("Please Enter Date of Birth: (yyyy-mm-dd)");
        AnchorPane.setTopAnchor(DOBLabel, 180.0);
        AnchorPane.setRightAnchor(DOBLabel, 40.0);
        AnchorPane.setLeftAnchor(DOBLabel, 40.0);

        dateOfBirth = new TextField();
        dateOfBirth.setMinHeight(40);
        AnchorPane.setTopAnchor(dateOfBirth, 200.0);
        AnchorPane.setRightAnchor(dateOfBirth, 40.0);
        AnchorPane.setLeftAnchor(dateOfBirth, 40.0);
        textLimiter(dateOfBirth, 10);

        verifyButton = new Button();
        verifyButton.getStylesheets().clear();
        verifyButton.getStylesheets().add(stylinTheme);
        verifyButton.setText("Verify");
        verifyButton.setMaxWidth(200);
        verifyButton.setMinHeight(50);
        AnchorPane.setTopAnchor(verifyButton, 260.0);
        AnchorPane.setRightAnchor(verifyButton, 40.0);
        AnchorPane.setLeftAnchor(verifyButton, 40.0);

        backButton = new Button();
        backButton.getStylesheets().clear();
        backButton.getStylesheets().add(stylinTheme);
        backButton.setText("Back");
        backButton.setMaxWidth(200);
        backButton.setMinHeight(50);
        AnchorPane.setTopAnchor(backButton, 320.0);
        AnchorPane.setRightAnchor(backButton, 40.0);
        AnchorPane.setLeftAnchor(backButton, 40.0);

        root.getChildren().addAll(firstLabel, firstname, lastLabel, lastname, DOBLabel, dateOfBirth, verifyButton, backButton);

    }

    public void loadResetPin(){
        firstLabel = new Label();
        firstLabel.setText("Please Enter Your Username");
        AnchorPane.setTopAnchor(firstLabel, 20.0);
        AnchorPane.setRightAnchor(firstLabel, 40.0);
        AnchorPane.setLeftAnchor(firstLabel, 40.0);

        username = new TextField();
        username.setMinHeight(40);
        AnchorPane.setTopAnchor(username, 40.0);
        AnchorPane.setRightAnchor(username, 40.0);
        AnchorPane.setLeftAnchor(username, 40.0);
        textLimiter(username, 15);

        DOBLabel = new Label();
        DOBLabel.setText("Please Enter Date of Birth: (yyyy-mm-dd)");
        AnchorPane.setTopAnchor(DOBLabel, 80.0);
        AnchorPane.setRightAnchor(DOBLabel, 40.0);
        AnchorPane.setLeftAnchor(DOBLabel, 40.0);

        dateOfBirth = new TextField();
        dateOfBirth.setMinHeight(40);
        AnchorPane.setTopAnchor(dateOfBirth, 100.0);
        AnchorPane.setRightAnchor(dateOfBirth, 40.0);
        AnchorPane.setLeftAnchor(dateOfBirth, 40.0);
        textLimiter(dateOfBirth, 10);


        verifyButton = new Button();
        verifyButton.getStylesheets().clear();
        verifyButton.getStylesheets().add(stylinTheme);
        verifyButton.setText("Verify");
        verifyButton.setMaxWidth(200);
        verifyButton.setMinHeight(50);
        AnchorPane.setTopAnchor(verifyButton, 160.0);
        AnchorPane.setRightAnchor(verifyButton, 40.0);
        AnchorPane.setLeftAnchor(verifyButton, 40.0);

        backButton = new Button();
        backButton.getStylesheets().clear();
        backButton.getStylesheets().add(stylinTheme);
        backButton.setText("Back");
        backButton.setMaxWidth(200);
        backButton.setMinHeight(50);
        AnchorPane.setTopAnchor(backButton, 250.0);
        AnchorPane.setRightAnchor(backButton, 40.0);
        AnchorPane.setLeftAnchor(backButton, 40.0);

        root.getChildren().addAll(firstLabel, username, DOBLabel, dateOfBirth, verifyButton, backButton);
        dateOfBirthNumeric();
    }

    public void loadPINReset(){
        firstLabel = new Label();
        firstLabel.setText("Please Enter Your New 4-Digit PIN");
        AnchorPane.setTopAnchor(firstLabel, 40.0);
        AnchorPane.setRightAnchor(firstLabel, 40.0);
        AnchorPane.setLeftAnchor(firstLabel, 40.0);

        pin = new PasswordField();
        pin.setMinHeight(40);
        AnchorPane.setTopAnchor(pin, 60.0);
        AnchorPane.setRightAnchor(pin, 40.0);
        AnchorPane.setLeftAnchor(pin, 40.0);
        textLimiter(pin, 4);
        limitToNumeric(pin);

        lastLabel = new Label();
        lastLabel.setText("Please Re-Enter Your Username: ");
        AnchorPane.setTopAnchor(lastLabel, 100.0);
        AnchorPane.setRightAnchor(lastLabel, 40.0);
        AnchorPane.setLeftAnchor(lastLabel, 40.0);

        username = new TextField();
        username.setMinHeight(40);
        AnchorPane.setTopAnchor(username, 120.0);
        AnchorPane.setRightAnchor(username, 40.0);
        AnchorPane.setLeftAnchor(username, 40.0);
        textLimiter(username, 15);

        resetButton = new Button();
        resetButton.setText("Reset");
        resetButton.setMaxWidth(200);
        resetButton.setMinHeight(50);
        AnchorPane.setTopAnchor(resetButton, 180.0);
        AnchorPane.setRightAnchor(resetButton, 40.0);
        AnchorPane.setLeftAnchor(resetButton, 40.0);

        root.getChildren().addAll(firstLabel, pin, lastLabel, username, resetButton);

    }

    public void loadDisplayUsername(){}
    public void incorrectLoginSlide(){

//        AnchorPane anchorPane = new AnchorPane();
//        anchorPane.setStyle("-fx-background-color: pink");
//        Label label = new Label("Wrong username or pin");
//        label.setAlignment(Pos.CENTER);
//        label.setFont(Font.font(24.0));
//        AnchorPane.setLeftAnchor(label, 0.0);
//        AnchorPane.setRightAnchor(label, 0.0);
//
//        anchorPane.getChildren().add(label);
//
//        GaussianBlur errorBlur = new GaussianBlur(0);
//        root.setEffect(errorBlur);
//
//        root.getChildren().add(anchorPane);
//        AnchorPane.setLeftAnchor(anchorPane, 15.0);
//        AnchorPane.setRightAnchor(anchorPane, 15.0);
//        AnchorPane.setTopAnchor(anchorPane, 15.0);
//        AnchorPane.setBottomAnchor(anchorPane, 15.0);
//
//        Timeline timeline = new Timeline();
//
//        anchorPane.setOpacity(0.0);
//
//        KeyValue blurValue = new KeyValue(errorBlur.radiusProperty(), 100.0);
//        KeyFrame blurFrame = new KeyFrame(Duration.millis(5000), blurValue);
//
//        KeyValue opacityValue = new KeyValue(anchorPane.opacityProperty(), 100.0);
//        KeyFrame opacityFrame = new KeyFrame(Duration.millis(5000), opacityValue);
//
//        timeline.getKeyFrames().addAll(opacityFrame, blurFrame);
//
//        timeline.setOnFinished(e -> {
//
//        });
//
//        timeline.play();


    }

    //This method limits the number of characters and data that can
    //entered into various fields
    //For example, limiting the pin to 4 numbers
    //and restricting the length of the username to 15 characters (random number I chose)

    public void textLimiter(TextField textField, int length){
        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (textField.getText().length() >= length) {
                        textField.setText(textField.getText().substring(0, length));
                        textField.selectEnd();
                    }
                }
            }
        });

        textField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.TAB) {
                System.out.println("Tab pressed");
                event.consume();
            }
        });
    }

    public void dateOfBirthNumeric(){
        int dateLimit = 10;
        dateOfBirth.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (dateOfBirth.getText().length() >= dateLimit) {
                        dateOfBirth.setText(dateOfBirth.getText().substring(0, dateLimit));
                    }
                }
            }
        });

    }

    public void limitToNumeric(TextField textField) {

        //Limit pin to numeric input only
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public Button getResetButton(){return resetButton;}

    public void setResetButton(Button resetButton){this.resetButton = resetButton;}

    public TextField getDateOfBirth(){return dateOfBirth;}

    public void setDateOfBirth(TextField dateOfBirth){ this.dateOfBirth = dateOfBirth;
    }
    public Button getForgotButton() {
        return forgotButton;
    }

    public void setForgotButton(JFXButton forgotButton) {
        this.forgotButton = forgotButton;
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public TextField getPin() {
        return pin;
    }

    public void setPin(TextField pin) {
        this.pin = pin;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }

    public Button getBackButton(){ return backButton; }

    public void setBackButton(Button backButton){this.backButton = backButton;}

    public Button getResetUsername(){ return resetUsername; }

    public void setResetUsername(Button resetUsername){this.resetUsername = resetUsername;}

    public Button getResetPin(){ return resetPin; }

    public void setResetPin(Button resetPin){this.resetPin = resetPin;}

    public Button getVerifyButton(){ return verifyButton; }

    public void setVerifyButton(Button verifyButton){this.verifyButton = verifyButton;}

    public TextField getFirstname(){ return firstname; }

    public void setFirstname(TextField firstname){this.firstname = firstname;}

    public TextField getLastname(){ return lastname; }

    public void setLastname(TextField lastname){this.lastname = lastname;}

    public String getStylinTheme() {
        return stylinTheme;
    }

    public void setStylinTheme(String stylinTheme) {
        this.stylinTheme = stylinTheme;
    }

    public void setForgotButton(Button forgotButton) {
        this.forgotButton = forgotButton;
    }

    public AnchorPane getRoot() {
        return root;
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }
}
