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

import models.Game;
import play.data.Form;
import play.db.DB;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.joinGame;
import views.html.gamePage;
import views.html.login;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinGame extends Controller {

    /**
     * loads the join game page
     * @param - none
     * @return - Result join game page
     */
    public static Result loadPage(){
        String uName = session("uname");
        String gCode= session("gCode");
        if(uName != null&&gCode==" ") {
            return ok(joinGame.render());
        }else if(uName!=null&&gCode!=" "){
            return GamePage.loadPage();
        }
        return forbidden(login.render());
    }

    /**
     * verifies a game code
     * @param code (the game code)
     * @return HTTP 200 ok() Result
     */
    public static Result verifyCode(String code){
        //TO DO: VERIFY THAT THE GAME CODE IS CORRECT Boolean isUser=false;
        boolean isGame=false;
        String sql = "SELECT * FROM game WHERE game_code = '" + code + "'";
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
                    if(rst.next()){
                        isGame=true;
                    }else{
                        isGame=false;
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


        if(isGame){
            String sql2 = "UPDATE user SET game_code = '" + code + "' WHERE user_name = '" + session("uname") +"'";
            java.sql.Connection conn2 = DB.getConnection();
            try {
                //http://stackoverflow.com/questions/18546223/play-framework-execute-raw-sql-at-start-of-request

                java.sql.Statement stmt = conn2.createStatement();
                try {
                    Boolean rst = stmt.execute(sql2);
                    // rst.close();
                } finally {

                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //TO DO: Error checking
            session("gCode",code);
            return ok();
        }else{

            return forbidden("not valid code");
        }
    }






}