/**
 * Created by Evan on 3/23/16.
 */
package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User extends Model {

    @Id      //primary key
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;

    public String userName;
    public String password;
    public String email;
    public Boolean isMod;
    public int type;
    public Boolean isActive;
    public String gameCode;

    public User(){}
    public static void changeModStatus(){

    }
    public static void changType(){

    }
    public static void changeActiveStatus(){

    }
    public static void setGameCode(){

    }
    public static void resetPassword(){

    }
    public static void getType(){

    }

}
