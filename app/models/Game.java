/**
 * @author      Mariah Flaim
 * @author      Evan Willner
 * @author      Elizabeth Dellea
 * @author      Nikhil Patel
 * @version     1.0
 * @since       2016-03-28
 **/

package models;

//import statements
import play.api.mvc.AnyContentAsRaw;
import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

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

    /**
     * searches a user in the User class database
     * @param userName - a user's username
     * @return - true
     */
    public static Boolean lookUpUser(String userName){
        return true;
    }

    /**
     * removes a user from the User class database
     * @param userName - a user's username
     * @return - void
     */
    public static void removeUser(String userName){

    }

    /**
     * resets all of the players in the User database
     * @param - none
     * @return - void
     */
    public static void resetPlayers(){

    }

    /**
     * sends a message to a certain type of player
     * @param type - status of player (zombie, human, moderator)
     * @param message - the message to be sent
     * @return - void
     */
    public static void addMessages(int type, String message){

    }

    /**
     * displays all messages of a certain type of player
     * @param type - status of player (zombie, human, moderator)
     * @return - void
     */
    public static void getMessages(int type){

    }

    /**
     * sends an alert to a certain type of user
     * @param type - status of player (zombie, human, moderator)
     * @return - void
     */
    public static void alertUsers(int type){

    }

    /**
     * adds a user to a game
     * @param userToAdd - the user to add to a game
     * @return - void
     */
    public static void joinGame(User userToAdd){

    }

}
