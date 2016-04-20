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
import views.html.*;

import java.sql.SQLException;

public class ModPage extends Controller {

    /**
     * loads the moderator page if they are a moderator
     * @param - none
     * @return - Result page for users who are moderators
     */
    public static Result loadPage(){
        //TO DO: check if moderator is in a game or not then load page based off if they are
        String gCode=session("gCode");
        String isMod=session("is_mod");
        String uname=session("uname");
        if(gCode!=" "&&(isMod=="true"||isMod=="1")){
            return ok(inGameModSettings.render());
        }else if(gCode==" "&&(isMod=="true"||isMod=="1")){
            return ok(noGameModSettings.render());
        }else if(isMod=="false"||isMod=="0"){
            return ok(gamePage.render(uname));
        }
        return forbidden(login.render());


    }
    /**
     * creates a new game in the Game class database
     * @param - none
     * @return - Result redirect to the in Game Mod Setting page
     */
    public static Result createGame() {
        //Game game = Form.form(Game.class).bindFromRequest().get();
        // game.save();
        //TO DO : create game and add moderator to it
        return redirect(routes.ModPage.loadPage());
    }
    /**
     *removes game from database
     * @param - none
     * @return - Result redirect to the no Game Mod Setting page
     */
    public static Result endGame(){
            return ok();
    }
    
    /**
     * gets rid of moderator status of user and reloads page so that game page is not longer mod page
     * @param - none
     * @return - none
     */
    public static Result changeStatus(){
        String isMod= "false";
        String sql2 = "UPDATE user SET is_mod = " + isMod + " WHERE id = " + session("id");
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
        session("is_mod","false");
        return ok(regularSettings.render());
    }

}

