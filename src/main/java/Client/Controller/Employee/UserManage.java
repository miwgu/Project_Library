package Client.Controller.Employee;

import Client.BookUtil;
import Client.Controller.ControllerUtil;
import Client.UserUtil;
import Model.UserEntities.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserManage extends ControllerUtil implements Initializable {
    public Text message;
    public TableView searchView;
    public TextField searchT;
    public Button searchBtn;
    public TableColumn<User, String> ssn;
    public TableColumn<User, String> firstName;
    public TableColumn<User, String> lastName;
    public TableColumn<User, String> userCategory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchT.setPromptText("Skriv sök ord här ....");
        //Sätt disable på sök knappen om man inte skriver något
        searchBtn.disableProperty().bind(searchT.textProperty().isEmpty());
        String searchWord = searchT.getText();
        UserUtil.printOutUserManage(searchWord, searchView, ssn, firstName, lastName, userCategory, message, getClass());
    }

    public void searchAction() {
        String searchWord = searchT.getText();
        UserUtil.printOutUserManage(searchWord, searchView, ssn, firstName, lastName, userCategory, message, getClass());
    }

    public void closeOldUserManageSceneAndOpenNewScene(ActionEvent e) throws IOException {
        Stage stage = (Stage) primaryStage.getScene().getWindow();
        stage.close();
        loadNewScreen("employee/searchUserManage", (Node) e.getSource());
    }
}
