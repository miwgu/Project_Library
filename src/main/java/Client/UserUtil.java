package Client;

import Client.Controller.Employee.ModalUserManage;
import DAO.UserDao;
import Model.UserEntities.Employee;
import Model.UserEntities.User;
import Model.UserEntities.Visitor;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;


public class UserUtil {
    public static UserDao userDao = new UserDao();
    public static User selectedUser;

    public static void registerUser(String firstName, String lastName, String sSN, String passwd, String tel, String address, String email, String userCategory) throws IOException {
        User user;
        if (userCategory.equalsIgnoreCase("Visitor")) {
            user = new Visitor(firstName, lastName, sSN, address, email, passwd, tel, false);
        } else {
            user = new Employee(firstName, lastName, sSN, address, email, passwd, tel, true);
        }

        userDao.save(user);
    }

    public static void printOutUserManage(String searchWord, TableView searchView, TableColumn<User, String> ssn, TableColumn<User, String> firstName, TableColumn<User, String> lastName, TableColumn<User, String> userCategory, Text message, Class<?> currentClass) {
        ObservableList<User> userManageData = searchView.getItems();
        userManageData.clear();
        List<User> userList;

        try {
            //Första gången kommer till book manage sidan
            if (searchWord.equals("")) {
                message.setText("");
                userList = userDao.getAll();
                if (userList.equals(null))
                    message.setText("Det finns inga data");
            } else {
                //userManageData.clear();
                message.setText("");
                userList = userDao.searchUser(searchWord);
                if (userList.size() == 0 || userList.equals(null)) {
                    searchView.setVisible(false);
                    message.setText("Din sökning gav inga träffar. Försök igen.");
                } else {
                    message.setText("");
                    searchView.setVisible(true);
                }
            }

            for (User user : userList) {
                ssn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getsSN()));
                firstName.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getFirstName()));
                lastName.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getLastName()));
                userCategory.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().isAdmin() ? "Employee" : "Visitor"));
                userManageData.add(user);
            }
            searchView.setItems(userManageData);

            //Open modal window
            searchView.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) ->
            {
                selectedUser = userDao.getById(newVal.toString());
                ModalUserManage.displayUserManage(currentClass);
            });
        } catch (Exception e) {
            message.setText("Det finns inga data");
        }
    }
}
