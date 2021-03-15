package Client.Controller;

import Client.UserUtil;
import Model.UserEntities.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class LogIn extends ControllerUtil implements Initializable {
    public AnchorPane loginPane;
    public TextField socialId;
    public PasswordField psw;
    public Button logInBtn;
    public Text message;
    public static User currentLoggedInUser;
    public Button top;
    public Button search;
    public Button LogIn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Disable login knappen om man inte skriver personnummer eller löseord
        logInBtn.disableProperty().bind(socialId.textProperty().isEmpty().or(psw.textProperty().isEmpty()));
    }

    public void logIn(ActionEvent e) throws IOException, ClassNotFoundException {
        //Hämta user list
        List<User> userList = UserUtil.userDao.getAll();

        User tempUser = userList.stream()
                .filter(user -> user.getsSN().equalsIgnoreCase(socialId.getText())
                        && user.getPasswd().equals(psw.getText()))
                .findFirst()
                .orElse(null);

        if (tempUser != null) {
            if (tempUser.isAdmin()) {
                currentLoggedInUser = tempUser;
                loadNewScreen("employee/employeeHome", (Node)e.getSource() );
            } else {
                currentLoggedInUser = tempUser;
                loadNewScreen("visitor/visitorHome", (Node)e.getSource() );
            }

        } else {
            message.setText("Vi hittar inte dig i systemet.  Försök logga in igen.");
            socialId.setText("");
            psw.setText("");
        }


    }

    //Ta bort felmeddelande så fort användare skriver något
    public void onChangeText() {
        message.setText("");
    }
}
