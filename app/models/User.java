/**
 * Created by Evan on 3/23/16.
 */
package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model {

    @Id      //primary key
    public String id;

    public String userName;
    public String password;
    public String email;
    public Boolean isMod;
    public static int type;
    public Boolean isActive;
    public String gameCode;
    public static void changeModStatus(){

    }
    public static void changeType(){

    }
    public static void changeActiveStatus(){

    }
    public static void setGameCode(){

    }
    public static void resetPassword(){

    }
    public static void getType(){
        System.out.println("Type: " + type + ".");
    }
}
