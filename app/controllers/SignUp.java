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
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.signup;
import play.data.Form;
import play.db.ebean.Model;
import java.lang.Exception;

public class SignUp extends Controller {

    /**
     * loads the sign up page
     * @param - none
     * @return - Result sign up page
     */
    public static Result loadSignUp() {
        return ok(signup.render());
    }

    /**
     * adds a new user to the User class database
     * @param - none
     * @return - HTTP 200 ok() status
     */
    public static Result addPerson() {
        User user = Form.form(User.class).bindFromRequest().get();
        user.save();

        return ok();
    }

    /**
     * validation check to sign up a user
     * @param email - user's email
     * @param password1 - user's password
     * @param password2 - user's password again
     * @param username - user's username
     * @param isMod - moderator status of the user
     * @return - Result page that approves or denies continued use based on the inputted email and password
     */
    public static Result validateSignUp(String email, String password1,String password2, String username, String isMod){
        System.out.println(email+" "+password1+" "+password2+" "+username+" "+isMod);
        //TO DO: check that username is not already being used
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String PASSWORD_REGEX="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])((?!.*\\s)).{6,}$";
        Boolean passwordVerified=password1.matches(PASSWORD_REGEX);
        Boolean emailVerified = email.matches(EMAIL_REGEX);
        Boolean passwordsMatch=password1.equals(password2);
        if(!emailVerified){
            return forbidden("email");
        }else if(!passwordVerified){
            return forbidden("password");
        }else if(!passwordsMatch) {
            return forbidden("mismatch");
        }else{
            return ok();
        }
    }

    /**
     * Constructor to add a user to the User database
     * @param emailIn - user's email
     * @param passwordIn - user's password
     * @param nameIn - user's name
     * @param isModVal - user's moderator status
     * @return - HTTP 200 ok() status
     */
    public static Result addUser(String emailIn, String passwordIn, String nameIn, String isModVal){
        //also TODO: hide passwords (hashing/encrypting/whatever)
        //TO DO: check that username is not already being used

            User newUser = new User();
            newUser.userName = nameIn;
            newUser.email = emailIn;
            newUser.password = passwordIn;
            newUser.isMod = Boolean.valueOf(isModVal);
            newUser.isActive = true;
            newUser.type = "none";
            newUser.gameCode = " ";
            newUser.save();
            return ok();



    }

}

