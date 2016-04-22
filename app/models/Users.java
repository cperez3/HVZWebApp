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
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;


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
    public static boolean addUser(String name, String password, String email, boolean isMod) {

        return true;
    }

    /**
     * verifies that a user exists in the User class database
     * @param name - a user's name
     * @param password - a user's password
     * @return - boolean validating whether or not a user exists
     */
    public static boolean verifyUser(String name, String password){
        return true;
    }

    /**
     * removes a user from the User class database
     * @param name - a user's name
     * @return - boolean validating whether or not a user has been removed from the User class database
     */
    public static boolean deleteUser(String name){
        return true;
    }
}