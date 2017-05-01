package controllers;

//import statements
import play.mvc.Controller;
import play.mvc.Result;
import views.html.homepage;
import views.html.login;
import views.html.signup;

public class Test extends Controller {

    public static Result loadTest() {
        return ok(homepage.render());
    }
}