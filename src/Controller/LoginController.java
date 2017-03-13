package Controller;

import Model.Employee;
import Model.LoginModel;
import Model.MainWindowModel;
import View.LoginView;
import View.MainWindowView;

/**
 * Created by Brannon on 3/11/2017.
 */
public class LoginController {

    private LoginModel loginModel;
    private LoginView loginView;
    private String enteredPin;
    private String enteredUsername;
    private Employee employee;



    public LoginController(LoginView loginView, LoginModel loginModel) {
        this.loginModel = loginModel;
        this.loginView = loginView;
        handles();
    }

    public void handles() {

        loginView.getLoginButton().setOnAction(e -> {
            System.out.println("Login Button Pressed");
            enteredUsername = loginView.getUsername().getText();
            boolean loginSuccessful;
            if (loginView.getUsername().getText() == null) {
                enteredUsername = "";
            } else {
                enteredUsername = loginView.getUsername().getText();
            }

            if (loginView.getPin().getText().equals("")) {
                enteredPin = "-1";
            } else {
                enteredPin = loginView.getPin().getText();
            }

            loginSuccessful = loginModel.isLoginValid(enteredUsername, enteredPin);

            if(loginSuccessful){
                //Load the next screen and pass in employee object
                System.out.println("login sucessful");
                employee = loginModel.getLoggedInEmployee(enteredUsername);
                loadMainWindow();
            }

            else{
                //Display wrong password message
                System.out.println("login unsuccessful");
            }
        });


        loginView.getForgotButton().setOnAction(e -> {
            System.out.println("Forgot Button Pressed");

        });

    }

    public void loadMainWindow() {
        try {
            MainWindowView mainWindowView = new MainWindowView(loginView.getStage());
            MainWindowModel mainWindowModel = new MainWindowModel();
            MainWindowController mainWindowController = new MainWindowController(mainWindowView, mainWindowModel, employee);
        } catch (Exception e) {e.printStackTrace();}
    }


}