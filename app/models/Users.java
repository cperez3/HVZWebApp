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
import models.User;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import play.mvc.Result;
import play.db.ebean.Model;


@Entity
public class Users {

    @Id
    public String id;
    public ArrayList registeredUsers;

    /**
     * adds a user to the user database
     * @param name - user's name
     * @param password - user's password
     * @param email - user's e-mail
     * @param isMod - user's moderator status
     * @return - boolean validating the success of adding a user to the User class database
     */


}