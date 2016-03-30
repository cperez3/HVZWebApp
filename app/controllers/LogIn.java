/**
 * @author      Mariah Flaim
 * @author      Evan Willner
 * @author      Elizabeth Dellea
 * @author      Nikhil Patel
 * @version     1.0
 * @since       2016-03-28
 **/

package controllers;

import play.db.DB;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

import java.sql.ResultSet;
import java.sql.SQLException;

//import statements
//import javax.inject.*;
//import play.api.UsefulException;
//import play.api.routing.Router;
//import play.http.DefaultHttpErrorHandler;


/**
 * Created by mariahflaim on 2/18/16.
 */


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

       String sql = "SELECT id, user_name FROM user WHERE email = '" + email + "' AND password = '" + password +"'";
        //sql query is not being case sensitive :O
        System.out.println(sql);
    //https://www.playframework.com/documentation/2.1.3/JavaEbean
        java.sql.Connection conn = DB.getConnection();
        try {
            //http://stackoverflow.com/questions/18546223/play-framework-execute-raw-sql-at-start-of-request

            java.sql.Statement stmt = conn.createStatement();
            try {
               ResultSet rst = stmt.executeQuery(sql);
                try {
                    while ( rst.next() ) {
                        int numColumns = rst.getMetaData().getColumnCount();
                        //this is where to throw an error I guess -- if one or more is invalid, it just sends back an empty result
                        for ( int i = 1 ; i <= numColumns ; i++ ) {
                            //this is where we'd take the stuff in each column and put it places!!!!!!!
                            // Column numbers start at 1.
                            // Also there are many methods on the result set to return
                            //  the column as a particular type. Refer to the Sun documentation
                            //  for the list of valid conversions.
                            System.out.println( "COLUMN " + i + " = " + rst.getObject(i) );
                        }
                    }
                } finally {
                    try { rst.close(); } catch (Throwable ignore) { /* Propagate the original exception
instead of this one that you may want just logged */ }
                }
            } finally {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


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