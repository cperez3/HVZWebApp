package models;
import play.api.mvc.AnyContentAsRaw;
import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
/**
 * Created by mariahflaim on 3/23/16.
 */
@Entity
public class Games {
    @Id
    public String id;
    public static String createGame(){
        return "gameCode";
    }
    public static void removeGame(String code){

    }
    public static Boolean verifyGame(String code){
        return true;
    }


}
