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
import views.html.joinGame;
import views.html.login;
import views.html.regularSettings;

public class GamePage extends Controller {

    /**
     * loads the game page
     * @param - none
     * @return - Result page of games
     */
    public static Result loadPage(){
        String uName = session("uname");
        if(uName != null) {
            String gCode = session("gCode");
            if(!gCode.equals(" ")) {
                return ok(gamePage.render(uName));
            }
            else{
                return forbidden(joinGame.render());
            }

        }
            return forbidden(login.render());

    }
    public static Result loadSettings(){return ok(regularSettings.render());}
    public static Result logOut(){
        session().clear();
        return ok();
    }

}