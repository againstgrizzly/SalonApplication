package Controller;

import MiscObjects.Employee;
import Model.*;
import View.LoginView;
import View.MainWindowView;
import javafx.event.ActionEvent;

/**
 * Created by Brannon on 3/11/2017.
 */
public class LoginController {

    private LoginModel loginModel;
    private LoginView loginView;
    private String enteredPin;
    private String enteredUsername;
    private String enteredBirthDate;
    private Employee employee;

    private String lastname;
    private String firstname;
    private String newPin;


    private DisplayUsernameModel displayUsernameModel;
    private DisplayPinModel displayPinModel;


    //This will load the screen behind the login window initially
    private MainWindowController mainWindowController;
    private MainWindowView mainWindowView;


    public LoginController(LoginView loginView, LoginModel loginModel, MainWindowController mainWindowController) {
        this.loginModel = loginModel;
        this.loginView = loginView;
        this.mainWindowController = mainWindowController;

        displayUsernameModel = new DisplayUsernameModel();
        displayPinModel = new DisplayPinModel();

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

            if (loginSuccessful) {
                //Load the next screen and pass in employee object
                System.out.println("login successful");
                try {
                    employee = loginModel.getLoggedInEmployee(enteredUsername);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                mainWindowController.logMeIn(employee);

            } else {
                //Display wrong password message
                loginView.incorrectLoginSlide();
                System.out.println("login unsuccessful");
            }
        });


        loginView.getForgotButton().setOnAction((ActionEvent e) -> {
            System.out.println("Forgot Button Pressed");
            try {
                loginView.getRoot().getChildren().clear();
                loginView.loadResetGui();
                handles();
                loginView.getBackButton().setOnAction(event -> {
                    System.out.println("Back Button Pressed");
                    try {
                        loginView.getRoot().getChildren().clear();
                        loginView.loginSetup();
                        handles();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
                loginView.getResetUsername().setOnAction(event -> {
                    loginView.getRoot().getChildren().clear();
                    loginView.loadForgotUsername();
                    handles();
                    loginView.getVerifyButton().setOnAction(event1 -> {

                        boolean infoValid = false;
                        System.out.println("Verify Button Pressed");
                        firstname = loginView.getFirstname().getText();
                        lastname = loginView.getLastname().getText();
                        enteredBirthDate = loginView.getDateOfBirth().getText();
                        if (firstname == null) {
                            System.out.println("Missing Field FirstName");
                        }
                        if (lastname == null) {
                            System.out.println("Missing Field LastName");
                        }
                        if (enteredBirthDate == null) {
                            System.out.println("Missing Field Birth Date");
                        } else {
                            System.out.println(firstname + " " + lastname + " " + enteredBirthDate);
                        }
                        try {
                            infoValid = displayUsernameModel.isInfoValid(firstname, lastname, enteredBirthDate);
                            if (infoValid) {
                                System.out.println("Employee Found");
                                System.out.println("Your Username is: " + firstname.toLowerCase().substring(0, 1) + lastname.toLowerCase());
                                loginView.getRoot().getChildren().clear();
                                loginView.loginSetup();
                                handles();
                            } else {
                                System.out.println("Employee Not Found");

                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    });
                    loginView.getBackButton().setOnAction(event1 -> {
                        loginView.getRoot().getChildren().clear();
                        loginView.loginSetup();
                        handles();
                    });
                });
                loginView.getResetPin().setOnAction(event -> {
                    loginView.getRoot().getChildren().clear();
                    loginView.loadResetPin();
                    handles();
                    loginView.getVerifyButton().setOnAction(event1 -> {
                        System.out.println("Verify Button Pressed");
                        boolean infoValid;
                        enteredUsername = loginView.getUsername().getText();
                        enteredBirthDate = loginView.getDateOfBirth().getText();
                        if (enteredUsername == null) {
                            enteredUsername = "";
                        } else {
                            System.out.println(enteredUsername);
                        }
                        if (enteredBirthDate == null) {
                            enteredBirthDate = "";
                        } else {
                            System.out.println(enteredBirthDate);

                        }

                        infoValid = displayPinModel.isInfoValid(enteredUsername, enteredBirthDate);
                        try {
                            if (infoValid) {
                                System.out.println("Employee Found");
                                loginView.getRoot().getChildren().clear();
                                loginView.loadPINReset();
                                loginView.getResetButton().setOnAction(event2 -> {
                                    System.out.println("Reset Pin Pressed");
                                    newPin = loginView.getPin().getText();
                                    enteredUsername = loginView.getUsername().getText();
                                    if (newPin == null || enteredUsername == null) {
                                        newPin = "";
                                        enteredUsername = "";
                                    } else {
                                        newPin = loginView.getPin().getText();
                                        enteredUsername = loginView.getUsername().getText();
                                        displayPinModel.resetPin(newPin, enteredUsername);
                                        System.out.println(newPin);
                                        loginView.getRoot().getChildren().clear();
                                        loginView.loginSetup();
                                        handles();
                                    }
                                });
                            } else {
                                System.out.println("Employee Not Found");

                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    });

                    loginView.getBackButton().setOnAction(event1 -> {
                        loginView.getRoot().getChildren().clear();
                        loginView.loginSetup();
                        handles();
                    });
                });
            } catch (Exception e1) {
                e1.printStackTrace();
            }
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
