package controllers;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.home;
import views.html.signup;
import models.User;
import play.data.Form;
import play.db.ebean.Model;


/**
 * Created by mariahflaim on 3/4/16.
 */
public class SignUp extends Controller{
    public static Result loadSignUp() {
        return ok(signup.render());

    }

    public static Result addPerson() {
        User user = Form.form(User.class).bindFromRequest().get();
        user.save();

        return redirect(routes.SignUp.loadSignUp());        //redirects to the sign up page after adding a person to db
    }

    public static Result validateSignUp(String email, String password1,String password2, String username, String isMod) {
        return ok();
    }

}

