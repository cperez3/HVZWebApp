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
import scala.Int;
import views.html.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import  java.util.LinkedList;
public class ModPage extends Controller {

    /**
     * loads the moderator page if they are a moderator
     *
     * @param - none
     * @return - Result page for users who are moderators
     */
    public static Result loadPage() {
        //TO DO: check if moderator is in a game or not then load page based off if they are
        String gCode = session("gCode");
        String isMod = session("is_mod");
        String uname = session("uname");
        if (uname!=null){
            return ok(gamePage.render(uname));
        }
        return forbidden(login.render());

    }

    /**
     * creates a new game in the Game class database
     *
     * @param - none
     * @return - Result redirect to the in Game Mod Setting page or login if not logged in as moderator not in game
     */
    public static Result createGame() {
        //Game game = Form.form(Game.class).bindFromRequest().get();
        // game.save();
        //TO DO : create game and add moderator to it
        if((session("is_mod")=="true"||session("is_mod")=="1")&&(session("gCode")==null||session("gCode")==" ")){
            return loadPage();
        }else{
            return forbidden(login.render());
        }

    }

    /**
     * removes game from database
     *
     * @param - none
     * @return - Result redirect to the no Game Mod Setting page or login if not logged in as mod in game
     */
    public static Result endGame() {
        if((session("is_mod")=="true"||session("is_mod")=="1")&&(session("gCode")!=null||session("gCode")!=" ")) {
            return loadPage();
        }else{
            return forbidden(login.render());
        }
    }

    /**
     * gets rid of moderator status of user and reloads page so that game page is not longer mod page
     *
     * @param - none
     * @return - game page
     */
    public static Result changeStatus() {
        if(session("is_mod")=="true"||session("is_mod")=="1") {
            String isMod = "false";
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
            session("is_mod", "false");
            return loadPage();
        }else{
            return forbidden(login.render());
        }
    }

    /**
     * gets all the players that are in the moderators game from the database
     *
     * @param - none
     * @return - render the view players page with the hashmap of players or login page if player  is not a mod in a game
     */
    public static Result getPlayers() {

        if((session("is_mod")=="true"||session("is_mod")=="1")&&(session("gCode")!=null||session("gCode")!=" ")){
        Map<String, String>players=new HashMap<String, String>();
        List<String> names=new LinkedList<String>();
        List<String> teams=new LinkedList<String>();


            String sql2 = "SELECT * FROM user WHERE game_code = " + Integer.parseInt(session("gCode"));
            java.sql.Connection conn2 = DB.getConnection();
            try {
                //http://stackoverflow.com/questions/18546223/play-framework-execute-raw-sql-at-start-of-request

                java.sql.Statement stmt = conn2.createStatement();
                try {
                    ResultSet rst = stmt.executeQuery(sql2);
                    try {
                        while ( rst.next() ) {
                            int numColumns = rst.getMetaData().getColumnCount();

                            names.add(rst.getString(2));
                            teams.add(rst.getString(6));

                        }
                        for(int i=0;i<names.size();i++){
                            players.put(names.get(i),teams.get(i));
                        }

                    } finally {
                        try { rst.close(); } catch (Throwable ignore) { /* Propagate the original exception
instead of this one that you may want just logged */ }
                    }

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
            //TO DO: this is where you will call the player list page with the players hash map as input
            return ok();
        } else {
            return forbidden(login.render());
        }


    }
    /**
     * Adds mod status to a particular user with a particular email
     *
     * @param - email of user you want to be moderator
     * @return - render log in if not logged in as mod in name, reloads page with success message
     */
    public static Result addModerator(String email) {
        if((session("is_mod")=="true"||session("is_mod")=="1")&&(session("gCode")!=null||session("gCode")!=" ")){
        //String email = "mflaim1@ithaca.edu";
        String id = "none";
        String sql2 = "SELECT * FROM user WHERE email = '" + email + "' AND game_code = " + Integer.parseInt(session("gCode"));
        java.sql.Connection conn2 = DB.getConnection();
        try {
            //http://stackoverflow.com/questions/18546223/play-framework-execute-raw-sql-at-start-of-request

            java.sql.Statement stmt = conn2.createStatement();
            try {
                ResultSet rst = stmt.executeQuery(sql2);
                try {
                    while (rst.next()) {

                        int numColumns = rst.getMetaData().getColumnCount();
                        id = rst.getString(1);

                    }

                } finally {
                    try {
                        rst.close();
                    } catch (Throwable ignore) { /* Propagate the original exception
instead of this one that you may want just logged */
                    }
                }

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
            if (id != "none") {
                String sql3 = "UPDATE user SET is_mod = " + 1 + " WHERE id = " + Integer.parseInt(id);
                java.sql.Connection conn3 = DB.getConnection();
                try {
                    //http://stackoverflow.com/questions/18546223/play-framework-execute-raw-sql-at-start-of-request

                    java.sql.Statement stmt = conn3.createStatement();
                    try {
                        Boolean rst = stmt.execute(sql3);
                        // rst.close();
                    } finally {

                        stmt.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        conn3.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                //TO DO: reload page I guess with message of success and if user is not found the load page with fail message
                return ok();
            }else{
                return forbidden();
            }

        }else{
            return forbidden(login.render());
        }

    }
}


