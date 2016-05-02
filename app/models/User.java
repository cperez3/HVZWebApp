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






}
