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
import play.mvc.Result;
import views.html.joinGame;
import views.html.login;

public class JoinGame extends Controller {

    /**
     * loads the join game page
     * @param - none
     * @return - Result join game page
     */
    public static Result loadPage(){
        String uName = session("uname");
        if(uName != null) {
            return ok(joinGame.render());
        }
        return forbidden(login.render());
    }

    /**
     * verifies a game code
     * @param code (the game code)
     * @return HTTP 200 ok() Result
     */
    public static Result verifyCode(String code){
        //TO DO: VERIFY THAT THE GAME CODE IS CORRECT
        return ok();

    }

    /**
     * creates a new game in the Game class database
     * @param - none
     * @return - Result redirect to the join game page
     */
    public static Result createGame() {
        Game game = Form.form(Game.class).bindFromRequest().get();
       // game.save();

        return redirect(routes.JoinGame.loadPage());
    }

}