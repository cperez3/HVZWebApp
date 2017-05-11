package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Chris on 5/10/2017.
 */
public class Users extends Controller {

  public static Result createAccount() {
    System.out.println("Create Account called.");
    DynamicForm body = Form.form().bindFromRequest();
    String email = body.get("email");
    String password = body.get("password");
    String name = body.get("name");
    User userExisting = User.find.where().eq("email", email).findUnique();
    if (userExisting != null) {
      return badRequest("Account already exists.");
    }

    User user = new User(email, password, name);

    return ok("Account Created");
  }

  public static Result login() {
    System.out.println("Login Called");
    DynamicForm form = Form.form().bindFromRequest();
    String email = form.get("email");
    String password = form.get("password");
    System.out.println("email: " + email );
    System.out.println("password: " + password );
    User user = User.find.where().eq("email", email).findUnique();
    if (user == null) {
      return badRequest("Email not found.");
    }

    if (!user.password.equals(password)) {
      return badRequest("Incorrect password.");
    }

    session("id", Long.toString(user.id));
    session("email", user.email);
    session("uname", user.name);
    if(user.currentRound != null) {
      session("is_mod", Boolean.toString(user.isMod));
      session("teamOld", user.team.toString());
      session("is_active", Boolean.toString(user.isActive));
      session("gCode", user.currentRound.gameCode);
    }
    response().setHeader("Content-Type", "text/json");
    return ok(user.toJson());
//    return noContent();
//    return GamePage.loadPage();
  }

  public User getUserFromRequest() {
    return null;
  }
}

