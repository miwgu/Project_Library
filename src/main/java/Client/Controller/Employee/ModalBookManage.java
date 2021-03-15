package Client.Controller.Employee;

import Client.BookUtil;
import Client.Controller.ControllerUtil;
import Client.Controller.SuccessModal;
import Model.Book;
import Model.Category;
import Model.Language;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class ModalBookManage extends ControllerUtil implements Initializable {
    private static Stage stage;
    public TextField id;
    public Button updateBtn;
    public Button removeBtn;
    public Text message;
    private Book book;
    public TextField title;
    public TextField isbn;
    public TextField author;
    public ComboBox category;
    public ComboBox language;
    public TextArea description;
    public TextField publisher;
    public TextField edition;
    public DatePicker releaseDate;
    public TextField numberOfPages;
    public static Class<?> currentClass;


    public static void displayBookManage(Class<?> c) {
        currentClass = c;
        Scene scene = loadModalScene(c, "/view/employee/modalBookManage.fxml");
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void closeModal() {
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*-------------Category-------------------*/
        //Hämta alla category enum
        Category[] categoryArr = Category.values();
        //Sätta alla category namn i dropdown menu
        for (Category categoryItem : categoryArr) {
            category.getItems().add(categoryItem.getCategory());
        }

        /*-------------Languages-------------------*/
        //Hämta alla language enum
        Language[] languageArr = Language.values();
        //Sätta alla language namn i dropdown menu
        for (Language languageItem : languageArr) {
            language.getItems().add(languageItem);
        }

        book = BookUtil.selectedBook;
        //Sätta alla bok info i textfield
        id.setText(book.getId());
        title.setText(book.getTitle());
        isbn.setText(book.getIsbn());
        author.setText(book.getAuthor());
        category.setValue(book.getCategory().getCategory());
        language.setValue(book.getLanguage().name());
        description.setText(book.getDescription());
        publisher.setText(book.getPublisher());
        edition.setText(book.getEdition());
        releaseDate.setValue(book.getReleaseDate());
        numberOfPages.setText(book.getNumberOfPages());

        if (book.getIsCheckOut()) {
            removeBtn.setVisible(false);
        }
    }

    public void actionUpdate(){
        try {
            message.setText("");
            book.setTitle(title.getText())
                    .setIsbn(isbn.getText())
                    .setAuthor(author.getText())
                    .setEdition(edition.getText())
                    .setNumberOfPages(numberOfPages.getText())
                    .setDescription(description.getText())
                    .setPublisher(publisher.getText())
                    .setReleaseDate(releaseDate.getValue());

            String cat = category.getValue().toString();
            String lang = language.getValue().toString();
            if (cat.length() != 0) {
                book.setCategory(Category.getByStringCategoryName(cat));
            }
            if (lang.length() != 0) {
                book.setLanguage(Language.valueOf(lang));
            }

            BookUtil.bookDao.update(book);

            SuccessModal.message = "You've successfully updated book";
            SuccessModal.displaySuccessDisplay(getClass());
            closeModal();

        } catch (Exception e) {
            e.printStackTrace();
            message.setText("It didn't work. Please try again");
        }
    }

    public void actionRemove(){
        try {
            message.setText("");
            BookUtil.bookDao.delete(book);
            SuccessModal.message = "You've successfully deleted book";
            SuccessModal.displaySuccessDisplay(getClass());
            closeModal();
        } catch (Exception e) {
            e.printStackTrace();
            message.setText("It didn't work. Please try again");
        }
    }
}
