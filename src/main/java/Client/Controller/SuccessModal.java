package Client.Controller;

import Client.Controller.Employee.BookManage;
import Client.Controller.Employee.RegisterBook;
import Client.Controller.Employee.UserManage;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class SuccessModal extends ControllerUtil implements Initializable {
    public Text successMsg;
    private static Stage stage;
    public static String message;
    private static String currentClassName;
    BookManage bm = new BookManage();
    UserManage um = new UserManage();

    public static void displaySuccessDisplay(Class<?> currentClass) {
        String s = currentClass.getName();
        currentClassName = s.substring(s.lastIndexOf('.') + 1);
        Scene scene = loadModalScene(currentClass, "/view/success.fxml");
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void closeModal(ActionEvent e) throws IOException {
        stage.close();
        //Stänga den gamla window och öppna ett nytt window som updaterat data
        if (currentClassName.equalsIgnoreCase("ModalBookManage"))
            bm.closeOldBookManageSceneAndOpenNewScene(e);
        else if (currentClassName.equalsIgnoreCase("ModalUserManage"))
            um.closeOldUserManageSceneAndOpenNewScene(e);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        successMsg.setText(message);
    }
}
