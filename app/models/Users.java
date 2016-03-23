package models;

/**
 * Created by Beth on 3/23/2016.
 */

import play.db.ebean.Model;
import java.persistence.Entity;
import java.persistence.Id;
import java.util.ArrayList;


@Entity
public class Users {
    @Id
    public String id;
    public ArrayList registeredUsers;


    public static boolean addUser(String name, String password, String email, boolean isMod) {
        return true;
    }

    public static boolean verifyUser(String name, String password){
        return true;
    }

    public static boolean deleteUser(String name){
        return true;
    }
}