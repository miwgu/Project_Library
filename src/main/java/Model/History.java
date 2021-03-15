package Model;

import java.io.Serializable;
import java.time.LocalDate;
import Model.UserEntities.User;
import java.util.List;

/**
 * Created by Toshiko Kuno
 * Date: 2020-12-03
 * Time: 16:12
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class History implements Serializable {
    private User user;
    private Book book;
    private LocalDate returnDate;
    private LocalDate lendOutDate;

    public History setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public History setLendOutDate(LocalDate lendOutDate) {
        this.lendOutDate = lendOutDate;
        return this;
    }

    public History setBook(Book book) {
        this.book = book;
        return this;
    }

    public History setUser(User user) {
        this.user = user;
        return this;
    }

    public LocalDate getLendOutDate() {
        return lendOutDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }
}
