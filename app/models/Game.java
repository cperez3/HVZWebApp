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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Game {
    @Id//primary key
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;

    public String gameCode;
    public ArrayList humanUsers;
    public ArrayList zombieUsers;
    public ArrayList deletedUsers;
    public ArrayList moderatorMessages;
    public ArrayList humanMessages;
    public ArrayList zombieMessages;
    public Game(){
    }


}
