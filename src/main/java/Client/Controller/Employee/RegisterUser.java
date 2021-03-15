package Client.Controller.Employee;

import Client.Controller.ControllerUtil;
import Client.Controller.SuccessModal;
import Client.UserUtil;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class RegisterUser extends ControllerUtil implements Initializable {
    public AnchorPane registerUserPane;
    public ToggleGroup userCat;
    public TextField firstNameT;
    public TextField lastNameT;
    public TextField ssnT;
    public TextField passwdT;
    public TextField telT;
    public TextField addressT;
    public TextField emailT;
    public Text message;
    public Button regiBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Disable login knappen om man inte fylla i alla info
        regiBtn.disableProperty().bind(firstNameT.textProperty().isEmpty()
                .or(lastNameT.textProperty().isEmpty())
                .or(ssnT.textProperty().isEmpty())
                .or(passwdT.textProperty().isEmpty())
                .or(telT.textProperty().isEmpty())
                .or(addressT.textProperty().isEmpty())
                .or(emailT.textProperty().isEmpty())
                .or(userCat.selectedToggleProperty().isNull()));
    }

    public void actionRegister() {
        RadioButton userCategoryChoice = (RadioButton) userCat.getSelectedToggle();

        try {
            message.setText("");
            if (!ssnT.getText().matches("[0-9]{10}") && !emailT.getText().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
                message.setText("Kontrollera personnummer och Email");
            } else if (!ssnT.getText().matches("[0-9]{10}")) {
                message.setText("Kontrollera personnummer");
            } else if (!emailT.getText().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
                message.setText("Kontrollera email");
            } else {

                message.setText("");

                UserUtil.registerUser(
                        firstNameT.getText(),
                        lastNameT.getText(),
                        ssnT.getText(),
                        passwdT.getText(),
                        telT.getText(),
                        addressT.getText(),
                        emailT.getText(),
                        userCategoryChoice.getText()
                );

                SuccessModal.message = "You've successfully created user data";
                SuccessModal.displaySuccessDisplay(getClass());

                firstNameT.setText("");
                lastNameT.setText("");
                ssnT.setText("");
                passwdT.setText("");
                telT.setText("");
                addressT.setText("");
                emailT.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
            message.setText("Det gick något fel. Försök igen");
        }

    }

}
