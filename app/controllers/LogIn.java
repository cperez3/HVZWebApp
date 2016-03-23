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


}

