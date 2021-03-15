package Model.UserEntities;


import java.io.Serializable;

/**
   * Created by Toshiko Kuno
   * Date: 2020-11-30
   * Time: 15:52
   * Project: IntelliJ IDEA
   * Copyright: MIT
   */ 
    
    
    
    public class Visitor extends User {

    public Visitor(String firstName, String lastName, String sSN, String address, String email, String passwd, String tel, boolean isAdmin) {
        super(firstName, lastName, sSN, address, email, passwd, tel, isAdmin);
    }


}
