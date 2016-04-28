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
import play.mvc.Result;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Games {

    @Id
    public String id;



    /**
     * verifies the existence of a game
     * @param code - game code
     * @return - Boolean of whether or not a game exists
     */
    public static Boolean verifyGame(String code){
        return true;
    }

}
