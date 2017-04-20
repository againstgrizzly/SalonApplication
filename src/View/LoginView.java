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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    private AnchorPane incorrectInputPane;
    private Label incorrectInputLabel;
    private ImageView errorImageView;

    public LoginView() throws Exception {
        root = new AnchorPane();
        root.setPrefSize(300, 350);
        root.setStyle("-fx-background-color: #e9e9e9");


        loginSetup();
        errorPaneSetup();
    }

    public void loginSetup() {
        username = new TextField();
        username.setPromptText("Please Enter Username");
        AnchorPane.setTopAnchor(username, 50.0);
        AnchorPane.setRightAnchor(username, 40.0);
        AnchorPane.setLeftAnchor(username, 40.0);
        username.setMinHeight(40);
        textLimite(username, 15);//limit username to 15 characters (random number I chose)

        pin = new PasswordField();
        pin.setPromptText("Please Enter PIN");
        AnchorPane.setTopAnchor(pin, 100.0);
        AnchorPane.setRightAnchor(pin, 40.0);
        AnchorPane.setLeftAnchor(pin, 40.0);
        pin.setMinHeight(40);
        textLimite(pin, 4);
        limitToNumeric(pin);

        loginButton = new Button();
        loginButton.getStylesheets().add(stylinTheme);
        loginButton.setText("Login");
        loginButton.setMaxWidth(200);
        loginButton.setMinHeight(50);
        AnchorPane.setTopAnchor(loginButton, 175.0);
        AnchorPane.setRightAnchor(loginButton, 40.0);
        AnchorPane.setLeftAnchor(loginButton, 40.0);

        forgotButton = new Button();
        forgotButton.getStylesheets().add(stylinTheme);
        forgotButton.setText("Forgot Username/PIN");
        forgotButton.setMaxWidth(200);
        forgotButton.setMinHeight(50);
        AnchorPane.setTopAnchor(forgotButton, 250.0);
        AnchorPane.setRightAnchor(forgotButton, 40.0);
        AnchorPane.setLeftAnchor(forgotButton, 40.0);

        root.getChildren().addAll(username, pin, loginButton, forgotButton);
    }

    public void errorPaneSetup() {
        //Error Pane
        incorrectInputPane = new AnchorPane();
        incorrectInputPane.setStyle("-fx-background-color: #343a41");

        incorrectInputLabel = new Label("Incorrect Username/Pin");
        incorrectInputLabel.setAlignment(Pos.CENTER);
        incorrectInputLabel.setTextFill(Color.WHITE);
        incorrectInputLabel.setFont(Font.font(20));
        AnchorPane.setTopAnchor(incorrectInputLabel, 50.0);
        AnchorPane.setRightAnchor(incorrectInputLabel, 0.0);
        AnchorPane.setLeftAnchor(incorrectInputLabel, 0.0);

        incorrectInputPane.getChildren().add(incorrectInputLabel);

        errorImageView = new ImageView(new Image("res/error.png"));
        errorImageView.setX(300 / 2 - 65);
        errorImageView.setY(100);
        incorrectInputPane.getChildren().add(errorImageView);


        AnchorPane.setLeftAnchor(incorrectInputPane, 15.0);
        AnchorPane.setRightAnchor(incorrectInputPane, 15.0);
        AnchorPane.setTopAnchor(incorrectInputPane, 15.0);
        AnchorPane.setBottomAnchor(incorrectInputPane, 15.0);

    }

    public void incorrectLoginSlide() {

        root.getChildren().add(incorrectInputPane);

        incorrectInputPane.setOnMouseClicked(e ->{
            root.getChildren().remove(incorrectInputPane);
        });

    }

    //This method limits the number of characters and data that can
    //entered into various fields
    //For example, limiting the pin to 4 numbers
    //and restricting the length of the username to 15 characters (random number I chose)

    public void textLimite(TextField textField, int length) {
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
