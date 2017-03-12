package Model;

import java.sql.*;

public class LoginModel {

    // The Model performs all the calculations needed
// and that is it. It doesn't know the View
// exists

    private Queries queries;

    public LoginModel(Queries queries){
        this.queries = queries;
    }


    public boolean isLoginValid(String username, String pin) {
        String inputtedUsername = username;
        int inputtedPin = Integer.valueOf(pin);
        boolean validInput = queries.LoginAttempt(inputtedUsername, inputtedPin);
        return validInput;
    }
}