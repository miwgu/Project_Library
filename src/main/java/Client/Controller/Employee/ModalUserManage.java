package Client.Controller.Employee;

import Client.BookUtil;
import Client.Controller.ControllerUtil;
import Client.Controller.SuccessModal;
import Client.UserUtil;
import Model.UserEntities.User;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModalUserManage extends ControllerUtil implements Initializable {
    private static Stage stage;
    public static Class<?> currentClass;
    private User user;
    public TextField firstName;
    public TextField lastName;
    public TextField passwd;
    public TextField ssn;
    public ToggleGroup userCat;
    public RadioButton employee;
    public RadioButton visitor;
    public TextField telephone;
    public TextField address;
    public TextField email;
    public TableView historyView;
    public TableColumn isbn;
    public TableColumn title;
    public TableColumn lendOutDate;
    public TableColumn returnedDate;
    public Text message;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = UserUtil.selectedUser;


        //Sätt alla user info i textfield
        ssn.setText(user.getsSN());
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        passwd.setText(user.getPasswd());
        telephone.setText(user.getTel());
        address.setText(user.getAddress());
        email.setText(user.getEmail());
        userCat.setUserData(user.isAdmin());
        if (user.isAdmin()) {
            employee.setSelected(true);
        } else {
            visitor.setSelected(true);
        }

        //Skriva ut historik
        BookUtil.printOutLendingHistory(historyView, title, isbn, returnedDate, lendOutDate, user.getsSN());
    }

    public static void displayUserManage(Class<?> c) {
        currentClass = c;
        Scene scene = ControllerUtil.loadModalScene(c, "/view/employee/modalUserManage.fxml");
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void closeModal() {
        stage.close();
    }

    public void actionUpdate() {
        try {
            message.setText("");
            user.setFirstName(firstName.getText());
            user.setLastName(lastName.getText());
            user.setAddress(address.getText());
            user.setEmail(email.getText());
            user.setPasswd(passwd.getText());
            user.setTel(telephone.getText());

            RadioButton userCategoryChoice = (RadioButton) userCat.getSelectedToggle();
            user.setAdmin(userCategoryChoice.getText().equalsIgnoreCase("Visitor") ? false : true);

            UserUtil.userDao.update(user);

            SuccessModal.message = "You've successfully updated user";
            SuccessModal.displaySuccessDisplay(getClass());
            closeModal();

        } catch (Exception e) {
            e.printStackTrace();
            message.setText("It didn't work. Please try again");
        }
    }

    public void actionRemove() {
        try {
            message.setText("");
            UserUtil.userDao.delete(user);
            SuccessModal.message = "You've successfully deleted user";
            SuccessModal.displaySuccessDisplay(getClass());
            closeModal();
        } catch (Exception e) {
            e.printStackTrace();
            message.setText("It didn't work. Please try again");
        }
    }
}
