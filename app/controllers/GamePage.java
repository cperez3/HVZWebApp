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
import play.mvc.Controller;
import play.mvc.Result;
import views.html.gamePage;
import views.html.regularSettings;

public class GamePage extends Controller {

    /**
     * loads the game page
     * @param - none
     * @return - Result page of games
     */
    public static Result loadPage(){
        return ok(gamePage.render());
    }
    public static Result loadSettings(){return ok(regularSettings.render());};

}