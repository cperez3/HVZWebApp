package controllers;

import models.Event;
import models.Round;
import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

/**
 * Created by Chris on 5/10/2017.
 */
public class Rounds extends Controller {

  public static Result createRound() {
    User user = User.find.byId(Long.parseLong(session("id")));
    System.out.println("ID: " + Long.parseLong(session("id")));
    DynamicForm form = Form.form().bindFromRequest();

//    Form<Round> roundForm = Form.form(Round.class);
//    Round round = roundForm.bindFromRequest().get();
    String title = form.get("title");
    String description = form.get("description");
    String roundRules = form.get("roundRules");
    String gameRules = form.get("gameRules");
    String contactInfo = form.get("contactInfo");

    Round round = new Round(title, description, roundRules, gameRules, contactInfo, user);
    user.currentRound = round;
    user.team = User.Team.HUMAN;
    user.isMod = true;
    user.isActive = true;
    round.save();
    user.save();

    return ok();
//    return ok(round.toJson());

    /*
    //Creating a User?
    Form<User> userForm = Form.form(User.class);
    User user = userForm.bindFromRequest().get();
    user.save();*/


  }

  public static Result getRound() {
    User user = User.find.byId(Long.parseLong(session("id")));
    return ok(Json.toJson(user.currentRound));
  }

  public static Result addEvent() {
    User user = User.find.byId(Long.parseLong(session("id")));
    if (user == null) {
      return Results.unauthorized("Must be logged in.");
    }
    if (!user.isMod) {
      return forbidden("Must be a moderator to add an event to the schedule.");
    }
    Form<Event> eventForm = Form.form(Event.class);
    Event event = eventForm.bindFromRequest().get();
    event.save();
    return ok(Json.toJson(event));
  }

  public static Result getSchedule() {
    return noContent();
  }
}
