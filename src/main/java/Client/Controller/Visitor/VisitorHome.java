package Client.Controller.Visitor;

import Client.Controller.ControllerUtil;
import Client.Controller.LogIn;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class VisitorHome extends ControllerUtil implements Initializable {
    public AnchorPane loggedInVisitor;
    public Text userName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userName.setText(LogIn.currentLoggedInUser.getFirstName());
    }
}
