package controllers;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import views.html.signup;
/**
 * Created by mariahflaim on 2/18/16.
 */
public class LogIn extends Controller{
    public static Result loadLogIn(){
        return ok(login.render());
    }
    public static Result validateLogIn(String email, String password){
        //TO DO: Validate email, password, make sure account exists, and check if user is in game
        return ok();
    }

}

