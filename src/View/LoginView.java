package View;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// This is the View
// Its only job is to display what the user sees
// It performs no calculations, but instead passes
// information entered by the user to whomever needs
// it.

public class LoginView {

    private String stylinTheme = "css/myCss.css";

    private Button forgotButton;
    private TextField username;
    private TextField pin;
    private Button loginButton;

    public LoginView(Stage primaryStage) throws Exception {

        VBox root = new VBox();
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(new Scene(root, 300, 363));
        root.requestFocus();

        username = new TextField();
        username.setPromptText("Please Enter Username");
        root.setMargin(username, new Insets(50, 40, 15, 40));
        username.setMinHeight(40);

        pin = new PasswordField();
        pin.setPromptText("Please Enter PIN");
        root.setMargin(pin, new Insets(0, 40, 30, 40));
        pin.setMinHeight(40);

        loginButton = new Button();
        loginButton.getStylesheets().clear();
        loginButton.getStylesheets().add(stylinTheme);
        loginButton.setText("Login");
        loginButton.setMaxWidth(200);
        loginButton.setMinHeight(50);
        root.setMargin(loginButton, new Insets(0, 0, 15, 50));

        forgotButton = new Button();
        forgotButton.getStylesheets().clear();
        forgotButton.getStylesheets().add(stylinTheme);
        forgotButton.setText("Forgot Username/PIN");
        forgotButton.setMaxWidth(200);
        forgotButton.setMinHeight(50);
        root.setMargin(forgotButton, new Insets(0, 0, 0, 50));

        root.getChildren().addAll(username, pin, loginButton, forgotButton);
        primaryStage.show();

        //Auxillary non-gui method calls
        textLimiter(); //Limits the text to certain lengths and input types
    }

    //This method limits the number of characters and data that can
    //entered into various fields
    //For example, limiting the pin to 4 numbers
    //and restricting the length of the username to 15 characters (random number I chose)
    public void textLimiter() {
        int usernameLimit = 15;
        int pinLimit = 4;

        //Length limit for username
        username.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (username.getText().length() >= usernameLimit) {
                        username.setText(username.getText().substring(0, usernameLimit));
                    }
                }
            }
        });

        //Length limit for pin
        pin.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (pin.getText().length() >= pinLimit) {
                        pin.setText(pin.getText().substring(0, pinLimit));
                    }
                }
            }
        });

        //Limit pin to numeric input only
        pin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    pin.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


    }

    public Button getForgotButton() {
        return forgotButton;
    }

    public void setForgotButton(Button forgotButton) {
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
}
