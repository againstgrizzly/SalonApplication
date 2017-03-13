
import Controller.LoginController;
import Controller.MainWindowController;
import Model.Employee;
import Model.LoginModel;
import Model.MainWindowModel;
import View.LoginView;
import View.MainWindowView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends Application {

    private int screen = 0; //Change this to jump quickly to different screens

    @Override
    public void start(Stage stage) throws Exception {

        System.out.println(System.getProperty("user.dir"));

        //this creates the flags.dat file and directory if it hasn't been created yet
        //I figure we could use this for a whole bunch of flag files for saving different
        //preferences and persistent data that wouldn't make sense to put into a database
        if (Files.notExists(Paths.get("./flags/flags.dat"))) {
            new File("./flags").mkdir();
            File temp = new File("./flags/flags.dat");
            //FileWriter writer = new FileWriter(temp, true);
        }
//        FXMLLoader welcomeScreen = new FXMLLoader(getClass().getResource("/fxml/FirstTimeSetupWelcomeScreen.fxml"));
//        AnchorPane rootGroup = welcomeScreen.load();
//        FirstTimeSetupWelcomeScreenController controller = welcomeScreen.getController();
//        Scene scene = new Scene(rootGroup, 1366, 768);
//        scene.getStylesheets().add("css/myCss.css");
//        scene.setFill(Color.TRANSPARENT);
//        stage.setScene(scene);
//        stage.show();

        //Load login screen


//        FXMLLoader mainWindow = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
//          AnchorPane rootGroup = mainWindow.load();
//        MainWindowController controller = mainWindow.getController();
//          Scene scene = new Scene(rootGroup, 1366, 768);
//          scene.getStylesheets().add("css/myCss.css"); //Comment this out to remove the CSS skinning
//        scene.setFill(Color.TRANSPARENT);
//        stage.setScene(scene);
//        stage.show();

        //Loads login

        if(screen == 0) {
            LoginView loginView = new LoginView(stage);
            LoginModel loginModel = new LoginModel();
            LoginController loginController = new LoginController(loginView, loginModel);
        }

        if(screen == 1){
            MainWindowView mainWindowView = new MainWindowView(stage);
            MainWindowModel mainWindowModel = new MainWindowModel();
            MainWindowController mainWindowController = new MainWindowController(mainWindowView, mainWindowModel, null);
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
