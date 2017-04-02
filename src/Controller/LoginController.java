package Controller;

import MiscObjects.Employee;
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

    //This will load the screen behind the login window initially
    private MainWindowController mainWindowController;



    public LoginController(LoginView loginView, LoginModel loginModel, MainWindowController mainWindowController) {
        this.loginModel = loginModel;
        this.loginView = loginView;
        this.mainWindowController = mainWindowController;

        handles();
    }

    public void handles() {

        loginView.getLoginButton().setOnAction(e -> {
            System.out.println("Login Button Pressed");
            enteredUsername = loginView.getUsername().getText();
            boolean loginSuccessful;

            //Username
            if (loginView.getUsername().getText() == null) {
                enteredUsername = "";
            } else {
                enteredUsername = loginView.getUsername().getText();
            }

            //Pin
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
                mainWindowController.logMeIn(employee);

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


    public LoginModel getLoginModel() {
        return loginModel;
    }

    public void setLoginModel(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public String getEnteredPin() {
        return enteredPin;
    }

    public void setEnteredPin(String enteredPin) {
        this.enteredPin = enteredPin;
    }

    public String getEnteredUsername() {
        return enteredUsername;
    }

    public void setEnteredUsername(String enteredUsername) {
        this.enteredUsername = enteredUsername;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

}
