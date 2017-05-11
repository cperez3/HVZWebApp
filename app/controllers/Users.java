package controllers;

import models.Event;
import models.Message;
import models.User;
import org.joda.time.DateTime;
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
    session().clear();
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

    /*Message message = new Message("MOD ALERT 1", user);
    message.messageType = Message.MessageType.MOD_ALERT;
    message.save();
    Message message2 = new Message("MOD ALERT 2", user);
    message2.messageType = Message.MessageType.MOD_ALERT;
    message2.save();*/
    /*new Event(user.currentRound, "My Event", DateTime.now(), DateTime.now().plusHours(1), "Human Location", "Zombie Location");
    new Event(user.currentRound, "My Event2", DateTime.now().plusHours(1), DateTime.now().plusHours(2), "Human Location", "Zombie Location");
    new Event(user.currentRound, "My Event3", DateTime.now().plusHours(2), DateTime.now().plusHours(3), "Human Location", "Zombie Location");*/

    return ok(user.toJson());
//    return noContent();
//    return GamePage.loadPage();
  }

  public static Result getProfile() {
    User user = User.find.byId(Long.parseLong(session("id")));
    if (user == null ) {
      return unauthorized();
    }
    return ok(user.toJson());
  }

  public static Result changeTeam() {
    User user = User.find.byId(Long.parseLong(session("id")));
    if (user == null ) {
      return unauthorized();
    }

    if (user.team == User.Team.HUMAN) {
      user.team = User.Team.ZOMBIE;
    }
    user.save();
    return noContent();
  }

  public static Result leaveRound() {
    User user = User.find.byId(Long.parseLong(session("id")));
    if (user == null ) {
      return unauthorized();
    }

    user.currentRound = null;
    user.team = User.Team.NONPLAYING;
    user.isMod = false;
    user.save();
    return noContent();
  }
}

