package Client.Controller;

import Client.BookUtil;
import Model.Book;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Modal implements Initializable {

    private static Stage stage;
    private Book book;
    public TextField title;
    public TextField isbn;
    public TextField author;
    public TextField category;
    public TextField language;
    public TextArea description;
    public TextField publisher;
    public TextField edition;
    public TextField releaseDate;
    public TextField numberOfPages;
    public TextField available;


    public static void displayBook(Class<?> currentClass) {
        Scene scene = ControllerUtil.loadModalScene(currentClass, "/view/modal.fxml");
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void closeModal() {
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        book = BookUtil.selectedBook;
        //Sätta alla bok info i textfield
        title.setText(book.getTitle());
        isbn.setText(book.getIsbn());
        author.setText(book.getAuthor());
        category.setText(book.getCategory().getCategory());
        language.setText(book.getLanguage().name());
        description.setText(book.getDescription());
        publisher.setText(book.getPublisher());
        edition.setText(book.getEdition());
        releaseDate.setText(String.valueOf(book.getReleaseDate()));
        numberOfPages.setText(book.getNumberOfPages());

        //Hämta hur många tillgängliga böcker finns i bibliotek
        List<Book> availableBookList = BookUtil.bookDao.getAvailableBookList(book.getIsbn());
        available.setText(String.valueOf(availableBookList.size()));
    }
}
