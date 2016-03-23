package models;
import play.api.mvc.AnyContentAsRaw;
import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
/*
 * Created by mariahflaim on 3/23/16.
 */
@Entity
public class Game {
    @Id
    public String id;
    public String gameCode;
    public ArrayList humanUsers;
    public ArrayList zombieUsers;
    public ArrayList deletedUsers;
    public ArrayList moderatorMessages;
    public ArrayList humanMessages;
    public ArrayList zombieMessages;

    public static Boolean lookUpUser(String userName){
        return true;
    }
    public static void removeUser(String userName){

    }
    public static void resetPlayers(){

    }
    public static void addMessages(int type, String message){

    }
    public static void getMessages(int type){

    }
    public static void alertUsers(int type){

    }
    //should take 
    public static void joinGame(User userToAdd){

    }

}
