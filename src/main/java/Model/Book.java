package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


public class Book implements Serializable {
    protected final String id = UUID.randomUUID().toString().replace("-", ""); //Create uniq ID
    protected String isbn;
    protected String title;
    protected String description;
    protected String author;
    protected Category category;// enum
    protected String publisher;
    protected String edition;
    protected LocalDate releaseDate;
    protected Language language;//enum
    protected String numberOfPages;
    protected boolean isCheckOut = false;

    // We can use fluent builder (do not make constructor)


    public String getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Book setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Book setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public String getEdition() {
        return edition;
    }

    public Book setEdition(String edition) {
        this.edition = edition;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Book setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public Book setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public Book setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public Book setIsCheckOut(boolean isCheckOut) {
        this.isCheckOut = isCheckOut;
        return this;
    }

    public boolean getIsCheckOut() {
        return isCheckOut;
    }


    @Override
    public String toString() {
        return id;
    }

}