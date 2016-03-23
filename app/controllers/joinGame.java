package controllers;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.joinGame;
/**
 * Created by mariahflaim on 2/18/16.
 */
public class JoinGame extends Controller{
    public static Result loadPage(){
        return ok(joinGame.render());
    }
    public static Result verifyCode(String code){
    //TO DO: VERIFY THAT THE GAME CODE IS CORRECT
        return ok();

    }

}