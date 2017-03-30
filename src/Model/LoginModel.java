package Model;

import MiscObjects.Employee;
import MiscObjects.Queries;

public class LoginModel {

    // The Model performs all the calculations needed
// and that is it. It doesn't know the View
// exists

    Queries queries = new Queries();

    public boolean isLoginValid(String username, String pin) {
        String inputtedUsername = username;
        int inputtedPin = Integer.valueOf(pin);
        boolean validInput = queries.LoginAttempt(inputtedUsername, inputtedPin);
        return validInput;
    }

    public Employee getLoggedInEmployee(String enteredUsername){
        return queries.getLoggedInEmployee(enteredUsername);
    }
}