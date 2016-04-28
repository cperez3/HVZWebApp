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
import com.avaje.ebean.SqlUpdate;


@Entity
public class User extends Model {

    @Id      //primary key
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;

    public String userName;
    public String password;
    public String email;
    public Boolean isMod;
    public String type;
    public Boolean isActive;
    public String gameCode;

    public User(){}

    /**
     * changes a user to or from a moderator
     * @param - none
     * @return - void
     */
    public static void changeModStatus(){
        SqlUpdate down = Ebean.createSqlUpdate("UPDATE isMod SET place = '0'");
        down.execute();
        return ok();
    }

    /**
     * changes a players type (zombie, human, moderator)
     * @param - none
     * @return - void
     */
    public static void changeType() {
        SqlUpdate down = Ebean.createSqlUpdate("UPDATE type SET place = 'zombie'");
        down.execute();
        return ok();
    }

    /**
     * changes a players status to or from active
     * @param - none
     * @return - void
     */
    public static void changeActiveStatus(){
        SqlUpdate down = Ebean.createSqlUpdate("UPDATE isActive SET place = '0'");
        down.execute();
        return ok();
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
        User user = User.find.select("type").where().eq("type",type);
        System.out.println(user);

    }

}
