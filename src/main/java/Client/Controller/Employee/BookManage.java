package Client.Controller.Employee;

import Client.BookUtil;
import Client.Controller.ControllerUtil;
import Model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookManage extends ControllerUtil implements Initializable {
    public TextField searchT;
    public Button searchBtn;
    public Text message;
    public TableView searchView;
    public TableColumn<Book, String> id;
    public TableColumn<Book, String> isbn;
    public TableColumn<Book, String> title;
    public TableColumn<Book, String> author;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchT.setPromptText("Skriv sök ord här ....");
        //Sätt disable på sök knappen om man inte skriver något
        searchBtn.disableProperty().bind(searchT.textProperty().isEmpty());
        try {
            String searchWord = searchT.getText();
            BookUtil.printOutBookManage(searchWord, searchView,  id,  isbn, title, author, message, getClass());
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void searchAction() throws IOException, ClassNotFoundException {
        String searchWord = searchT.getText();
        BookUtil.printOutBookManage(searchWord, searchView,  id,  isbn, title, author, message, getClass());
    }

    public void closeOldBookManageSceneAndOpenNewScene(ActionEvent e) throws IOException {
        Stage stage = (Stage)primaryStage.getScene().getWindow();
        stage.close();
        loadNewScreen("employee/searchBookManage", (Node)e.getSource());
    }

}
