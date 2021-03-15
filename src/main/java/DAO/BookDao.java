package DAO;

import Model.Book;
import Model.StorageUtil;
import Util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class BookDao implements Dao<Book> {

    //Spara data som hämtar från textfil i lista
    private List<Book> bookList = new ArrayList<>();
    private StorageUtil db;

    //Hämta data från textfil?
    public BookDao() {

        try {
            db = new StorageUtil("books");
            //saveAll();
            bookList = getAll(); //Overwrite current history list with the fetched deserialized data

            System.out.println("Loaded data: " + bookList);
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Book> getAll() throws IOException, ClassNotFoundException {
        return db.deserializeReadList();
    }

    @Override
    public void saveAll() throws IOException {
        db.serializeStoreList(bookList);
    }

    //Register book
    @Override
    public void save(Book book) throws IOException {
        bookList.add(book);
        saveAll();
    }

    //Update book
    @Override
    public void update(Book book) throws IOException {
        bookList.get(bookList.indexOf(book));
        saveAll();
    }

    //Remove book
    @Override
    public void delete(Book book) throws IOException {
        bookList.remove(bookList.indexOf(book));
        saveAll();
    }

    public Book getByIsbn(String isbn) {
        return bookList.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .filter(book -> !book.getIsCheckOut())
                .findAny()
                .orElse(null);
    }

    public Book getCheckedBookByIsbn(String isbn) {
        return bookList.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findAny()
                .orElse(null);
    }

    public Book getById(String id) {
        for (Object e : bookList) {
            if (e instanceof Book) {
                if(((Book) e).getId().equals(id)) return (Book) e;
            }
        }
        return null;
    }


   public List<Book> removeDublicateBook() {
       Map<String, Book> map = new HashMap<>();
       for (Book book : bookList) {
           map.put(book.getIsbn(), book);
       }
       //Map -> List
       List<Book> filteredBookList = new ArrayList<>(map.values());

       return filteredBookList;
    }


    //Boksök funktion
    public List<Book> searchBook(List<Book> bookList, String searchWord) {

        List<Book> hitSearchBookList = new ArrayList<>();
        searchWord = Util.removeWhiteSpace(searchWord);
            for (Book book : bookList) {
                String title = Util.removeWhiteSpace(book.getTitle());
                String isbn = book.getIsbn();
                String author = Util.removeWhiteSpace(book.getAuthor());
                if (title.contains(searchWord) || isbn.contains(searchWord) ||
                        author.contains(searchWord)) {
                    hitSearchBookList.add(book);
                }
            }
        return hitSearchBookList;
    }

    //Hämta ut tillgänliga böcker
    public List<Book> getAvailableBookList(String isbn) {
        return bookList.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .filter(book -> !book.getIsCheckOut())
                .collect(Collectors.toList());
    }
}
