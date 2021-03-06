
import Controller.AddAppointmentWindowController;
import Controller.LoginController;
import Controller.MainWindowController;
import MiscObjects.Employee;
import Model.*;
import Model.AddAppointmentWindowModel;
import View.AddAppointmentWindowView;
import View.EmployeeScheduleView;
import View.LoginView;
import View.MainWindowView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Date;

public class Main extends Application {

    private int screen = 0; //Change this to jump quickly to different screens

    Image logo = new Image(getClass().getResourceAsStream("res/stylinMenuLogo.png"));
    private String url = "jdbc:h2:" + // protocol
            System.getProperty("user.dir") + "/stylinDB";

    @Override
    public void start(Stage stage) throws Exception {

        System.out.println(url);

        stage.setTitle("Stylin");
        stage.getIcons().add(logo);


//        EmployeeScheduleView e = new EmployeeScheduleView(new Employee());
//
        LoginView loginView = new LoginView();
        LoginModel loginModel = new LoginModel();

        MainWindowView mainWindowView = new MainWindowView(stage, loginView);
        MainWindowModel mainWindowModel = new MainWindowModel();
        MainWindowController mainWindowController = new MainWindowController(mainWindowView, mainWindowModel);

        LoginController loginController = new LoginController(loginView, loginModel, mainWindowController);


//        AddAppointmentWindowView addAppointmentWindowView = new AddAppointmentWindowView(new Employee(), new Date());
//        AddAppointmentWindowModel addAppointmentWindowModel = new AddAppointmentWindowModel();
//        AddAppointmentWindowController addAppointmentWindowController = new AddAppointmentWindowController(de)


    }

    public static void main(String[] args) {
        launch(args);
    }
}
