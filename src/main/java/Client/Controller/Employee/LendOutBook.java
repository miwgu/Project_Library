package Client.Controller.Employee;

import Client.BookUtil;
import Client.Controller.ControllerUtil;
import Client.Controller.SuccessModal;
import Client.UserUtil;
import Model.History;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class LendOutBook extends ControllerUtil implements Initializable {

    public AnchorPane lendOutBookPane;
    public TextField ssn;
    public TextField isbn;
    public Button regiBtn;
    public Text message;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Disable login knappen om man inte skriver personnummer eller ISBN
        regiBtn.disableProperty().bind(ssn.textProperty().isEmpty().or(isbn.textProperty().isEmpty()));
    }

    public void lendOutAction() {
        try {

            if (BookUtil.bookDao.getByIsbn(isbn.getText()) == null && UserUtil.userDao.getById(ssn.getText()) == null)
                message.setText("Felaktig inmatning. Försök mata in igen.");
            else if (UserUtil.userDao.getById(ssn.getText()) == null)
                message.setText("Vi hittar inte personnummer. Försök mata in igen.");
            else if (BookUtil.bookDao.getByIsbn(isbn.getText()) == null)
                message.setText("Vi hittar inte den bok. Försök mata in igen.");
            else {
               History history = BookUtil.registerLendOutBook(ssn.getText(), isbn.getText());

               message.setText("");
               isbn.setText("");
               ssn.setText("");

                SuccessModal.message = "Name: " + history.getUser().getFirstName() + " " + history.getUser().getLastName()
                                        + "\nTitle: " + history.getBook().getTitle() +
                                        "\nReturn Date: " + history.getLendOutDate().plusDays(14).toString(); //Återlämnings datum borde om 14 dagar.
                SuccessModal.displaySuccessDisplay(getClass());
            }

        } catch (Exception e) {
            e.printStackTrace();
            message.setText("Det gick något fel. Försök igen");
        }
    }
}
