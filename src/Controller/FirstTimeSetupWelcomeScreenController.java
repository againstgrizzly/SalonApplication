package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstTimeSetupWelcomeScreenController implements Initializable {

    @FXML private AnchorPane rootPane;
    @FXML private Button nextScreenFirstTimeSetupButton1;

    @Override public void initialize(URL location, ResourceBundle resources) {
        System.out.println("First Time Welcome Screen Initialized");
    }

    @FXML void OnActionNextScreenFirstTimeSetupButton1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FirstTimeSetupInformationScreen.fxml"));
        AnchorPane rootGroup = fxmlLoader.load();
        FirstTimeSetupInformationScreenController controller = fxmlLoader.getController();
        rootPane.getChildren().setAll(rootGroup);
        AnchorPane.setBottomAnchor(rootGroup, 0.0);
        AnchorPane.setLeftAnchor(rootGroup, 0.0);
        AnchorPane.setRightAnchor(rootGroup, 0.0);
        AnchorPane.setTopAnchor(rootGroup, 0.0);
    }

}
