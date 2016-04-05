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
        Boolean isUser=false;
        String sql = "SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password +"'";
        //sql query is not being case sensitive :O
        String[] fetched = new String[10];
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
                            fetched[i] = rst.getString(i);
                            System.out.println( "COLUMN " + i + " = " + fetched[i] );
                        }

                        if( !password.equals(fetched[3])){
                            System.out.println(password + " from db: " + fetched[3]);
                            isUser = false;
                        }
                        else{
                            isUser = true;
                            System.out.println("time to bake some cookies!!");
                            //process the data into cookies
                            session("uname", fetched[2]);
                            session("id", fetched[1]);
                            session("is_mod", fetched[5]);
                            session("type", fetched[6]);
                            session("is_active", fetched[7]);
                            if(fetched[8] == null){
                                fetched[8] = " ";
                            }
                            session("gCode", fetched[8]);
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


        //TO DO if the user is moderator call moderator load page

      if(isUser){
          return ok();
      }else{
          return forbidden("not user");
      }


    }

}