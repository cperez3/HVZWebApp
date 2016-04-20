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
import play.db.DB;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.signup;
import play.data.Form;
import play.db.ebean.Model;
import java.lang.Exception;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static Result addPerson() {
        User user = Form.form(User.class).bindFromRequest().get();
        user.save();

        return ok();
    }*/

    /**
     * validation check to sign up a user
     * @param email - user's email
     * @param password1 - user's password
     * @param password2 - user's password again
     * @param username - user's username
     * @param isMod - moderator status of the user
     * @return - Result page that approves or denies continued use based on the inputted email and password
     */
    public static Result validateSignUp(String email, String password1,String password2, String username, String isMod) {

        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])((?!.*\\s)).{6,}$";
        Boolean passwordVerified = password1.matches(PASSWORD_REGEX);
        Boolean emailVerified = email.matches(EMAIL_REGEX);
        Boolean passwordsMatch = password1.equals(password2);

        System.out.println(email + " " + password1 + " " + password2 + " " + username + " " + isMod);
        boolean nameExists = false;
        boolean emailExists = false;
        //should check and return true if there is an entry with these credentials
        String sql1 = "SELECT * FROM user WHERE email = '" + email + "'";
        String sql2 = "SELECT * FROM user WHERE user_name = '" + username + "'";

        java.sql.Connection conn = DB.getConnection();
        try {
            //http://stackoverflow.com/questions/18546223/play-framework-execute-raw-sql-at-start-of-request

            java.sql.Statement stmt = conn.createStatement();
            try {
                ResultSet rst = stmt.executeQuery(sql2);
                try {
                    if (rst.next()) {

                        System.out.println("name check: " + rst);
                        nameExists = true;
                    }
                } catch (Throwable ignore) { /* Propagate the original exception
instead of this one that you may want just logged */
                }
                ResultSet rst2 = stmt.executeQuery(sql1);
                try {
                    if (rst2.next()) {
                        System.out.println("email check: " + rst2);
                        emailExists = true;
                    }
                } catch (Throwable ignore) {//not sure these need to exist at this point, but better safe than sorry
                }

            } finally {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (nameExists && emailExists) {
            return forbidden("repeatUE");
        }else if(nameExists){
            return forbidden("repeatU");
        }else if(emailExists){
            return forbidden("repeatE");
        }else if(!emailVerified){
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

            System.out.println("isModVal"+isModVal);
            session("uname", nameIn);
            session("id", String.valueOf(newUser.id));
            session("is_mod", isModVal);
            session("type", "none");
            session("is_active", "true");
            session("gCode", " ");

            return ok();



    }

}

