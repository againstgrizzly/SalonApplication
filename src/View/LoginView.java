package View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


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

    }
}
