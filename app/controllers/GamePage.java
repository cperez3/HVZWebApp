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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        String status = session("is_active");

        if (uName != null) {
            String gCode = session("gCode");
            String isMod = session("is_mod");
            if (!gCode.equals(" ")) {
                return ok(gamePage.render(uName,status));
                //works for regular player and moderator this way
            }
            if ((isMod.equals("1") || isMod.equals("true"))
                    && gCode.equals(" ")) {
                return ok(noGameModSettings.render(uName));
            } else {
                return forbidden(joinGame.render());
            }

        }
        return forbidden(login.render());

    }

    public static Result loadSettings() {
        String isMod = session("is_mod");
        String uName = session("uname");
        String status = session("is_active");
        String team = session("type");
        if(isMod!=null){
            if (status.equals("1"))
                status = "Active";
            if (status.equals("0"))
                status = "Inactive";


            if(isMod.equals("0")||isMod.equals("false")){

                return ok(regularSettings.render(uName, status, team));
            } if(isMod.equals("1")||isMod.equals("true")){
                //TO DO: return mod settings
                String gCode = session("gCode");

                if(!gCode.equals(" ")){

                    return ok(inGameModSettings.render(uName, gCode, status, team));
                }
                else {

                    return ok(noGameModSettings.render(uName));
                }
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

    //MODERATOR FUNCTIONS

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
        if((session("is_mod").equals("true")||session("is_mod").equals("1"))&&(session("gCode")==null||session("gCode").equals(" "))){
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
        if((session("is_mod").equals("true")||session("is_mod").equals("1"))&&(session("gCode")!=null&&!session("gCode").equals(" "))){
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
    public static Result verifyChangeModStatus() {
        //TO DO: check if there are anyother moderators in the game. If there arent then you cant change status
        if (session("is_mod") != null) {
            if (session("is_mod").equals("true") || session("is_mod").equals("1")) {
                if (session("gCode").equals(" ")) {
                    //change status right away
                    changeModStatus();
                    return loadPage();
                }
                Boolean isNotOnlyMod=isNotOnlyMod();
                if(isNotOnlyMod){
                    changeModStatus();
                    return loadPage();
                }else{
                    System.out.println("ONLY MOD");
                    return forbidden("You are the only moderator, please add another moderator before switching your moderator status.");
                }
            }
            System.out.println("NOT MOD");
            return forbidden(login.render());
        }
        System.out.println("NOT LOGGED IN");
        return forbidden(login.render());
    }

    public static Boolean isNotOnlyMod(){
        String sql2 = "SELECT * FROM user WHERE is_mod = 1 and game_code ='"+ Integer.parseInt(session("gCode"))+"'";
        int i=0;
        java.sql.Connection conn2 = DB.getConnection();
        try {
            //http://stackoverflow.com/questions/18546223/play-framework-execute-raw-sql-at-start-of-request

            java.sql.Statement stmt = conn2.createStatement();
            try {
                ResultSet rst = stmt.executeQuery(sql2);
                try {
                    while ( rst.next() ) {
                        i++;
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
        if(i>1){
            return true;
        }else{
            return false;
        }

    }

    public static void changeModStatus(){
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

    }

    /**
     * gets all the players that are in the moderators game from the database
     *
     * @param - none
     * @return - render the view players page with the hashmap of players or login page if player  is not a mod in a game
     */
    public static Result getPlayers() {
        if(session("is_mod")!=null){
            if((session("is_mod").equals("true")||session("is_mod").equals("1"))&&(session("gCode")!=null&&!session("gCode").equals(" "))){
                System.out.println("HEYYYYYYYYYY");
                Map<String, String> players=new HashMap<String, String>();
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
            }
            return forbidden(login.render());

        }

        return forbidden(login.render());



    }
    /**
     * Adds mod status to a particular user with a particular email
     *
     * @param - email of user you want to be moderator
     * @return - render log in if not logged in as mod in name, reloads page with success message
     */
    public static Result addModerator(String email) {
        //TO DO: if you are adding a mod who is already a mod then it should say that

        if(session("is_mod")!=null){
            if((session("is_mod").equals("true")||session("is_mod").equals("1"))&&(session("gCode")!=null&&!session("gCode").equals(" "))){
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
                    return forbidden("user not in game");
                }


            }
            return forbidden(login.render());
        }
        return forbidden(login.render());


    }


}