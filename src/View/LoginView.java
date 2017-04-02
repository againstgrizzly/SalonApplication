package View;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
    private VBox root;

    public LoginView() throws Exception {
        root = new VBox();
        root.setPrefSize(300, 350);
        root.setStyle("-fx-background-color: #e9e9e9");

        loginSetup();
    }

    public void loginSetup(){
        username = new TextField();
        username.setPromptText("Please Enter Username");
        root.setMargin(username, new Insets(50, 40, 15, 40));
        username.setMinHeight(40);
        textLimite(username, 15);//limit username to 15 characters (random number I chose)

        pin = new PasswordField();
        pin.setPromptText("Please Enter PIN");
        root.setMargin(pin, new Insets(0, 40, 30, 40));
        pin.setMinHeight(40);
        textLimite(pin, 4);
        limitToNumeric(pin);

        loginButton = new Button();
        loginButton.getStylesheets().add(stylinTheme);
        loginButton.setText("Login");
        loginButton.setMaxWidth(200);
        loginButton.setMinHeight(50);
        root.setMargin(loginButton, new Insets(0, 0, 15, 50));

        forgotButton = new Button();
        forgotButton.getStylesheets().add(stylinTheme);
        forgotButton.setText("Forgot Username/PIN");
        forgotButton.setMaxWidth(200);
        forgotButton.setMinHeight(50);
        root.setMargin(forgotButton, new Insets(0, 0, 0, 50));

        root.getChildren().addAll(username, pin, loginButton, forgotButton);
    }

    //This method limits the number of characters and data that can
    //entered into various fields
    //For example, limiting the pin to 4 numbers
    //and restricting the length of the username to 15 characters (random number I chose)

    public void textLimite(TextField textField, int length){
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

    public VBox getRoot() {
        return root;
    }

    public void setRoot(VBox root) {
        this.root = root;
    }
}
