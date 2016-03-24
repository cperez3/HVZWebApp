package controllers;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.home;
import views.html.signup;

/**
 * Created by mariahflaim on 3/4/16.
 */
public class SignUp extends Controller{
    public static Result loadSignUp(){
        return ok(signup.render());
    }
    public static Result validateSignUp(String email, String password1,String password2, String username, String isMod){
        System.out.println(email+" "+password1+" "+password2+" "+username+" "+isMod);
        return ok();
    }

}

