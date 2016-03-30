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
import views.html.login;
import views.html.signup;
import views.html.home;

public class Home extends Controller{

    /**
     * loads the home page
     * @param - none
     * @return - Result home page
     */
    public static Result loadHome(){
        return ok(home.render());
    }

    /**
     * loads the login page when login link is clicked
     * @param - none
     * @return - Result login page
     */
    public static Result logInClicked(){
        return ok(login.render());
    }

    /**
     * loads the sign page when the sign in page is clicked
     * @param - none
     * @return - Result sign up page
     */
    public static Result signUpClicked(){
        return ok(signup.render());
    }
}
