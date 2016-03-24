package controllers;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
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
    public static Result addUser(String emailIn, String passwordIn, String nameIn, String isModVal){
        //this stuff is not being parsed properly. it's also going into the db wrong, probably because of that
        //System.out.println("Username :" + nameIn + "\t email : " + emailIn + "password : " + passwordIn + "mod status : " + isModVal);
        //sorry Toby, the println is the most convenient way to do things
        //also TODO: hide passwords (hashing/encrypting/whatever)
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

