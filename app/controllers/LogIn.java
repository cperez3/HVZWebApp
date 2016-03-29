/**
 * @author      Mariah Flaim
 * @author      Evan Willner
 * @author      Elizabeth Dellea
 * @author      Nikhil Patel
 * @version     1.0
 * @since       2016-03-28
 **/

package controllers;

//import statements
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import models.User;
import com.avaje.ebean.Query;
import com.avaje.ebean.Ebean;
import views.html.signup;
//import javax.inject.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
//import play.api.UsefulException;
//import play.api.routing.Router;
//import play.http.DefaultHttpErrorHandler;
import play.mvc.Http.*;
import play.*;

public class LogIn extends Controller{

    /**
     * loads the login page
     * @param - none
     * @return - Result login page
     */
    public static Result loadLogIn(){
        return ok(login.render());
    }

    /**
     * validates a users inputted login email and password
     * @param email - the users email
     * @param password - the users password
     * @return - Result HTTP 200 ok() status
     */
    public static Result validateLogIn(String email, String password) {
        String sql = "select id, name from user where email = " + email + " and password = " + password;
        /*RawSql rawSql =
                RawSqlBuilder
                        .parse(sql)
                        .columnMapping("id",  "user.id")
                        .columnMapping("name",  "user.name")
                        .create();

        Query<User> query = Ebean.find(User.class);
        query.setRawSql(rawSql);*/
        //TO DO if the user is moderator call moderator load page

        if (true) {
            return ok();
        } else {

        }
        return ok();

    }

}


