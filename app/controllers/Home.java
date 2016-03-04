package controllers;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import views.html.signup;
import views.html.home;
/**
 * Created by mariahflaim on 3/4/16.
 */
public class Home extends Controller{
    public static Result loadHome(){
        return ok(home.render());
    }
    public static Result logInClicked(){
        return ok(login.render());
    }
    public static Result signUpClicked(){
        return ok(signup.render());
    }
}
