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
import views.html.noGameModSettings;
import views.html.inGameModSettings;
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
            String isMod=session("is_mod");
            System.out.println("isMod: "+isMod);
            if(!gCode.equals(" ")&&(isMod.equals("0")||isMod.equals("false"))){
                return ok(gamePage.render(uName));
            }if((isMod.equals("1")||isMod.equals("true"))
                    &&!gCode.equals(" ")){
                return ok(inGameModSettings.render());

            }if((isMod.equals("1")||isMod.equals("true"))
                    &&gCode.equals(" ")){
                return ok(noGameModSettings.render());
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
        return ok(login.render());
    }

}