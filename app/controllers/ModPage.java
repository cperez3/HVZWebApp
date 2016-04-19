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
import java.util.Random;
import models.Game;
import play.data.Form;
import play.mvc.Controller;
import views.html.inGameModSettings;
import views.html.noGameModSettings;
import play.mvc.Result;
import java.util.ArrayList;

public class ModPage extends Controller {

    /**
     * loads the moderator page if they are a moderator
     * @param - none
     * @return - Result page for users who are moderators
     */
    public static Result loadPage(){
        //TO DO: check if moderator is in a game or not then load page based off if they are
        return ok(noGameModSettings.render());
    }

    /**
     * adds a new game to the Game class database
     * @param - none
     * @return - HTTP 200 ok() status
     */
    public static Result addNewGame() {
        Game game = Form.form(Game.class).bindFromRequest().get();
        game.save();

        return ok();
    }

    /**
     * creates an alphanumeric game code
     * @return String game code
     */
    public static String gameCode() {
        int limit = 7;

        Random r = new Random();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < limit; i++) {
            char c = (char)(r.nextInt((int)(Character.MAX_VALUE)));
            sb.append(c);
        }
        return sb.toString();
    }


    /**
     * Adds a game to the games database
     * @param idIn - game id
     * @param gameCodeIn - game code
     * @param humanUsersIn - list of human users in game
     * @param zombieUsersIn - list of zombie users in game
     * @param deletedUsersIn - list of users removed from game
     * @param moderatorMessagesIn - list of moderator messages
     * @param humanMessagesIn - list of human messages
     * @param zombieMessagesIn - list of zombie messages
     * @return - Result HTTP 200 ok status
     */
    public static Result addGame(String idIn, String gameCodeIn, ArrayList humanUsersIn,
                                 ArrayList zombieUsersIn, ArrayList deletedUsersIn, ArrayList moderatorMessagesIn,
                                 ArrayList humanMessagesIn, ArrayList zombieMessagesIn) {

        Game newGame = new Game();

        newGame.id = idIn;
        newGame.gameCode = gameCodeIn;
        newGame.humanUsers = humanUsersIn;
        newGame.zombieUsers = zombieUsersIn;
        newGame.deletedUsers = deletedUsersIn;
        newGame.moderatorMessages = moderatorMessagesIn;
        newGame.humanMessages = humanMessagesIn;
        newGame.zombieMessages = zombieMessagesIn;

        newGame.save();

        return ok();
    }


}

