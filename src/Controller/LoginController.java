package Controller;

import Model.Employee;
import Model.LoginModel;
import Model.Queries;
import View.LoginView;

import java.sql.SQLException;

/**
 * Created by Brannon on 3/11/2017.
 */
public class LoginController {

    private LoginModel loginModel;
    private LoginView loginView;
    private String enteredPin;
    private String enteredUsername;
    private Employee employee;
    private Queries queries;


    public LoginController(LoginView loginView, LoginModel loginModel, Queries queries) {
        this.loginModel = loginModel;
        this.loginView = loginView;
        this.queries = queries;
        handles();
    }

    public void handles() {

        loginView.getLoginButton().setOnAction(e -> {
            System.out.println("Login Button Pressed");
            enteredUsername = loginView.getUsername().getText();
            boolean loginSuccessful;

            //Sets text to empty string if user hasn't entered anything (may be redundant, but im too lazy to check
            if (loginView.getUsername().getText() == null) {
                enteredUsername = "";
            } else {
                enteredUsername = loginView.getUsername().getText();
            }

            //sets empty text to -1 since "" can't be parsed to int
            if (loginView.getPin().getText().equals("")) {
                enteredPin = "-1";
            } else {
                enteredPin = loginView.getPin().getText();
            }

            loginSuccessful = loginModel.isLoginValid(enteredUsername, enteredPin);

            if(loginSuccessful){
                //Load the next screen and pass in employee object
                System.out.println("login sucessful");
                employee = queries.getLoggedInEmployee(enteredUsername);

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


}
