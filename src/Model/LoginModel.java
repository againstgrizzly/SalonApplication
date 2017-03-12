package Model;

import java.sql.*;

public class LoginModel {

    // The Model performs all the calculations needed
// and that is it. It doesn't know the View
// exists


    public boolean isLoginValid(String username, String pin) {
        String inputtedUsername = username;
        int inputtedPin = Integer.valueOf(pin);
        boolean validInput = new Queries().LoginAttempt(inputtedUsername, inputtedPin);
        return validInput;
    }
}