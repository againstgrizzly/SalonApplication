package Controller;

import MiscObjects.DataHelper;
import MiscObjects.FirstTimeLoginHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.ResourceBundle;

public class FirstTimeSetupInformationScreenController implements Initializable {

    @FXML
    private AnchorPane firstTimeSetupAnchorPane;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField pinTextField;
    @FXML
    private PasswordField confirmPinTextField;
    @FXML
    private DatePicker dateOfBirthDatePicker;
    @FXML
    private TextField areaCodeTextField;
    @FXML
    private TextField phoneTextField1;
    @FXML
    private TextField phoneTextField2;
    @FXML
    private TextField emailAddressTextField;
    @FXML
    private JFXCheckBox newsAndUpdatedCheckBox;
    @FXML
    private ChoiceBox<?> securityQuestionComboBox1;
    @FXML
    private TextField answerOneTextField;
    @FXML
    private ChoiceBox<?> securityQuestionComboBox2;
    @FXML
    private TextField answerTwoTextField;
    @FXML
    private ChoiceBox<?> securityQuestionComboBox3;
    @FXML
    private TextField answerThreeTextField;
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;

    FirstTimeLoginHelper helper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textLimitter();


//        if (Files.notExists(Paths.get("./flags/flags.dat"))) {
//            new File("./flags").mkdir();
//            File temp = new File("./flags/flags.dat");
//            //FileWriter writer = new FileWriter(temp, true);
//        }
        DataHelper helper = new DataHelper();
        if(helper.firstName != null){firstNameTextField.setText(helper.firstName);}
        if(helper.lastName != null){lastNameTextField.setText(helper.lastName);}
        if(helper.userName != null){userNameTextField.setText(helper.userName);}
        if(helper.date != null) {dateOfBirthDatePicker.setValue(helper.date);}
        if (helper.areaCode != null) {areaCodeTextField.setText(helper.areaCode);}
        if(helper.firstPhone != null)phoneTextField1.setText(helper.firstPhone);
        if(helper.secondPhone != null)phoneTextField2.setText(helper.secondPhone);
        if (helper.email != null) {emailAddressTextField.setText(helper.email);}
        newsAndUpdatedCheckBox.setSelected(helper.sendNews);
    }

    @FXML
    void OnActionBackButton(ActionEvent event) throws IOException {
        persistance();
        ObjectMapper mapper = new ObjectMapper();
        helper = new FirstTimeLoginHelper();
        helper.setFirstName(firstNameTextField.getText());
        mapper.writeValue(new File("jsonHelper/firstTimeLoginHelper.json"), helper);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FirstTimeSetupWelcomeScreen.fxml"));
        AnchorPane rootGroup = fxmlLoader.load();
        firstTimeSetupAnchorPane.getChildren().setAll(rootGroup);

    }

    @FXML
    void OnActionNextButton(ActionEvent event) {

    }

    @FXML
    void firstTimeSetupFirstNameTextFieldOnKeyTyped(KeyEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FirstTimeSetupWelcomeScreen.fxml"));
    }

    void persistance(){
        DataHelper helper = new DataHelper();
        helper.firstName = firstNameTextField.getText();
        helper.lastName = lastNameTextField.getText();
        helper.userName = userNameTextField.getText();
        helper.areaCode = areaCodeTextField.getText();
        helper.firstPhone = phoneTextField1.getText();
        helper.secondPhone = phoneTextField2.getText();
        helper.email = emailAddressTextField.getText();
        helper.date = dateOfBirthDatePicker.getValue();
        helper.sendNews = newsAndUpdatedCheckBox.isSelected();
        System.out.println(helper.firstName);
    }

    void textLimitter(){
        int nameLimit = 15;
        int pinLimit = 4;
        int phoneLimit3 = 3;
        int phoneLimit4 = 4;
        int otherLimit = 50;

        //Limit the number of characters in a bunch of textfields
        firstNameTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (firstNameTextField.getText().length() >= nameLimit) {
                        firstNameTextField.setText(firstNameTextField.getText().substring(0, nameLimit));
                    }
                }
            }
        });

        lastNameTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (lastNameTextField.getText().length() >= nameLimit) {
                        lastNameTextField.setText(lastNameTextField.getText().substring(0, nameLimit));
                    }
                }
            }
        });

        userNameTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (userNameTextField.getText().length() >= nameLimit) {
                        userNameTextField.setText(userNameTextField.getText().substring(0, nameLimit));
                    }
                }
            }
        });

        pinTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (pinTextField.getText().length() >= pinLimit) {
                        pinTextField.setText(pinTextField.getText().substring(0, pinLimit));
                    }
                }
            }
        });

        confirmPinTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (confirmPinTextField.getText().length() >= pinLimit) {
                        confirmPinTextField.setText(confirmPinTextField.getText().substring(0, pinLimit));
                    }
                }
            }
        });

        areaCodeTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (areaCodeTextField.getText().length() >= phoneLimit3) {
                        areaCodeTextField.setText(areaCodeTextField.getText().substring(0, phoneLimit3));
                    }
                }
            }
        });

        phoneTextField1.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (phoneTextField1.getText().length() >= phoneLimit3) {
                        phoneTextField1.setText(phoneTextField1.getText().substring(0, phoneLimit3));
                    }
                }
            }
        });

        phoneTextField2.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (phoneTextField2.getText().length() >= phoneLimit4) {
                        phoneTextField2.setText(phoneTextField2.getText().substring(0, phoneLimit4));
                    }
                }
            }
        });

        emailAddressTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (emailAddressTextField.getText().length() >= otherLimit) {
                        emailAddressTextField.setText(emailAddressTextField.getText().substring(0, otherLimit));
                    }
                }
            }
        });

        answerOneTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (answerOneTextField.getText().length() >= otherLimit) {
                        answerOneTextField.setText(answerOneTextField.getText().substring(0, otherLimit));
                    }
                }
            }
        });

        answerTwoTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (answerTwoTextField.getText().length() >= otherLimit) {
                        answerTwoTextField.setText(answerTwoTextField.getText().substring(0, otherLimit));
                    }
                }
            }
        });

        answerThreeTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (answerThreeTextField.getText().length() >= otherLimit) {
                        answerThreeTextField.setText(answerThreeTextField.getText().substring(0, otherLimit));
                    }
                }
            }
        });

        //Limit the textfields to numeric input only
        pinTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    pinTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        confirmPinTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    confirmPinTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        areaCodeTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    areaCodeTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        phoneTextField1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    phoneTextField1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        phoneTextField2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    phoneTextField2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


    }



}
