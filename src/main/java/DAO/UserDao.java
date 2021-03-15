package DAO;

import Model.StorageUtil;
import Model.UserEntities.Employee;
import Model.UserEntities.User;
import Model.UserEntities.Visitor;
import Util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UserDao implements Dao<User> {

    //Spara data som hämtar från textfil i lista
    private List<User> userList = new ArrayList<>();
    private StorageUtil db;

    //Hämta data från textfil?
    public UserDao() {

        /*------------------------TEST----------------------------------*/
//        userList.add(new Employee("Toshiko", "Kuno", "8811072886", "Enskede", "kuno@gmail.com", "1111", "11111111", true));
//        userList.add(new Employee("Miwa", "G", "0000000000", "", "miwa@gmail.com", "0000", "11111111", false));
//        userList.add(new Visitor("Yohannes", "Y", "1111111111", "", "yohannes@gmail.com", "1111", "11111111", false));
//        userList.add(new Visitor("Maxim", "M", "2222222222", "", "maxim@gmail.com", "2222", "11111111", true));
        /*------------------------------------------*/

        try {
            db = new StorageUtil("users");
            //saveAll();
            userList = getAll(); //Overwrite current history list with the fetched deserialized data
            System.out.println("Loaded data: " + userList);
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public List<User> getAll() throws IOException, ClassNotFoundException {
        return db.deserializeReadList();
    }

    @Override
    public void saveAll() throws IOException {
        db.serializeStoreList(userList);
    }

    //Register user
    @Override
    public void save(User user) throws IOException {
        userList.add(user);
        saveAll();
    }

    //Update user
    @Override
    public void update(User user) throws IOException {
        userList.get(userList.indexOf(user));
        saveAll();
    }

    //Remove user
    @Override
    public void delete(User user) throws IOException {
        userList.remove(userList.indexOf(user));
        saveAll();
    }

    public User getById(String id) {
        for (Object e : userList) {
            if (e instanceof User) {
                if (((User) e).getsSN().equals(id)) return (User) e;
            }
        }
        return null;
    }

    public List<User> searchUser(String searchWord) {
        List<User> foundUsers = new ArrayList<>();
        searchWord = Util.removeWhiteSpace(searchWord);
        for (User user : userList) {
            if (user.getsSN().contains(searchWord) ||
                    user.getFirstName().toLowerCase().contains(searchWord) ||
                    user.getLastName().toLowerCase().contains(searchWord))
                foundUsers.add(user);
        }
        return foundUsers;
    }
}
