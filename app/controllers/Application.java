package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import play.Routes;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
                Routes.javascriptRouter("myJsRoutes",
                        routes.javascript.JoinGame.verifyCode(),
                        routes.javascript.SignUp.validateSignUp(),
                        routes.javascript.LogIn.validateLogIn(),
                        routes.javascript.SignUp.addUser()
                )
        );
    }


}
