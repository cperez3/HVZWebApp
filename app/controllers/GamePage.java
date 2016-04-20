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
        String uName = session("uname");
        if (uName != null) {
            String gCode = session("gCode");
            String isMod = session("is_mod");
            System.out.println("isMod: " + isMod);
            if (!gCode.equals(" ") && (isMod.equals("0") || isMod.equals("false"))) {
                return ok(gamePage.render(uName));
            }
            if ((isMod.equals("1") || isMod.equals("true"))
                    && !gCode.equals(" ")) {
                return ok(inGameModSettings.render());

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
        return ok(regularSettings.render());
    }

    public static Result logOut() {
        session().clear();
        return ok(login.render());
    }

    public static Result deleteUser(String email) {
        String sql2 = "DELETE FROM 'hvz'.'user' WHERE email =" + email;
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
        return ok();
    }


}