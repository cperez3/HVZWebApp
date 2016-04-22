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

import play.db.DB;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.noGameModSettings;
import views.html.inGameModSettings;
import views.html.gamePage;
import views.html.joinGame;
import views.html.login;
import views.html.regularSettings;

import java.sql.SQLException;

public class GamePage extends Controller {

    /**
     * loads the game page
     *
     * @param - none
     * @return - Result page of games
     */
    public static Result loadPage() {

        //TO DO: should put inGameModSettings into mod settings so moderator can access gamepage and then just do mod stuff from settings
        String uName = session("uname");
        if (uName != null) {
            String gCode = session("gCode");
            String isMod = session("is_mod");
            System.out.println("isMod: " + isMod);
            if (!gCode.equals(" ")) {
                return ok(gamePage.render(uName));
                //works for regular player and moderator this way
            }

            if ((isMod.equals("1") || isMod.equals("true"))
                    && gCode.equals(" ")) {
                return ok(noGameModSettings.render());
            } else {
                return forbidden(joinGame.render());
            }

        }
        return forbidden(login.render());

    }

    public static Result loadSettings() {
        String isMod = session("is_mod");
        if(isMod.equals("0")||isMod.equals("false")){
            return ok(regularSettings.render());
        } if(isMod.equals("1")||isMod.equals("true")){
            //TO DO: return mod settings
            String gCode = session("gCode");
            if(gCode != " "){
                return ok(inGameModSettings.render());
            }
            else {
                return ok(noGameModSettings.render());
            }
        }

        return forbidden(login.render());
    }

    public static Result logOut() {
        String uName = session("uname");
        if(uName!=null){
            session().clear();
            return ok(login.render());
        }else{
            return forbidden(login.render());
        }

    }

    public static Result deactivateAccount(){
        String email = session("email");
        System.out.println(email);
        if(email!=null){

            return deleteUser(email);
        }else{
            return forbidden(login.render());
        }
    }

    public static Result deleteUser(String email) {


            String sql1="SET SQL_SAFE_UPDATES = 0";
            String sql2 = "DELETE FROM user WHERE email = '" + email + "'";
            String sql3="SET SQL_SAFE_UPDATES = 1";
            java.sql.Connection conn2 = DB.getConnection();
            try {
                //http://stackoverflow.com/questions/18546223/play-framework-execute-raw-sql-at-start-of-request

                java.sql.Statement stmt = conn2.createStatement();
                try {
                    //Boolean rst1 = stmt.execute(sql1);
                    System.out.println("JIIIIIII");
                    Boolean rst2 = stmt.execute(sql2);
                    //Boolean rst3 = stmt.execute(sql3);
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
            return ok();



    }


}