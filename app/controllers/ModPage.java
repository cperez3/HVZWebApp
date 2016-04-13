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
import play.mvc.Controller;
import views.html.inGameModSettings;
import views.html.noGameModSettings;
import play.mvc.Result;

public class ModPage extends Controller {

    /**
     * loads the moderator page if they are a moderator
     * @param - none
     * @return - Result page for users who are moderators
     */
    public static Result loadPage(){
        //TO DO: check if moderator is in a game or not then load page based off if they are
        return ok(inGameModSettings.render());
    }
    /**
     * creates a new game in the Game class database
     * @param - none
     * @return - Result redirect to the join game page
     */
    public static Result createGame() {
        //Game game = Form.form(Game.class).bindFromRequest().get();
        // game.save();
        //TO DO : create game and add moderator to it
        return redirect(routes.ModPage.loadPage());
    }
    public static Result endGame(){

    }

}

