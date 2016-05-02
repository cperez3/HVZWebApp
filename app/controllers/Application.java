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
import play.*;
import play.mvc.*;
import views.html.*;
import play.Routes;

public class Application extends Controller {

    /**
     * loads the index page
     * @param - none
     * @return - Result page of index
     */
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    /**
     * loads the links to other pages
     * @param - none
     * @return - Result page of links to other pages
     */
    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
                Routes.javascriptRouter("myJsRoutes",
                        routes.javascript.JoinGame.verifyCode(),
                        routes.javascript.SignUp.validateSignUp(),
                        routes.javascript.LogIn.validateLogIn(),
                        routes.javascript.SignUp.addUser(),
                        routes.javascript.GamePage.verifyChangeModStatus()
                )
        );
    }

}
