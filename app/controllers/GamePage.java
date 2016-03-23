package controllers;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.gamePage;
import views.html.joinGame;
/*
 * Created by mariahflaim on 2/18/16.
 */
public class GamePage extends Controller {
    public static Result loadPage(){
        return ok(gamePage.render());
    }


}