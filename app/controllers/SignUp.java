package controllers;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.signup;
import java.lang.Exception;
/**
 * Created by mariahflaim on 3/4/16.
 */
public class SignUp extends Controller{
    public static Result loadSignUp(){
        return ok(signup.render());
    }
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
    public static Result addUser(String emailIn, String passwordIn, String nameIn, String isModVal){
        //also TODO: hide passwords (hashing/encrypting/whatever)
        //TO DO: check that username is not already being used

            User newUser = new User();
            newUser.userName = nameIn;
            newUser.email = emailIn;
            newUser.password = passwordIn;
            newUser.isMod = Boolean.valueOf(isModVal);
            newUser.isActive = true;
            newUser.save();
            return ok();


    }

}

