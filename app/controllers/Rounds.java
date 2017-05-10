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
    Form<Round> roundForm = Form.form(Round.class);
    Round round = roundForm.bindFromRequest().get();
    round.players.add(user);
    user.currentRound = round;

    round.save();

    return ok(Json.toJson(round));

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
