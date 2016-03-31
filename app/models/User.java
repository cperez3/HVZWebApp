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
    public static int type;
    public Boolean isActive;
    public String gameCode;

    public User(){}

    /**
     * changes a user to or from a moderator
     * @param - none
     * @return - void
     */
    public static void changeModStatus(){

    }

    /**
     * changes a players type (zombie, human, moderator)
     * @param - none
     * @return - void
     */
    public static void changeType(){

    }

    /**
     * changes a players status to or from active
     * @param - none
     * @return - void
     */
    public static void changeActiveStatus(){

    }

    /**
     * establishes a game code for a game
     * @param - none
     * @return - void
     */
    public static void setGameCode(){

    }

    /**
     * resets a user's password
     * @param - none
     * @return - void
     */
    public static void resetPassword(){

    }

    /**
     * displays a players type
     * @param - none
     * @return - void
     */
    public static void getType(){
        System.out.println("Type: " + type + ".");
    }

}
