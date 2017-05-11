package controllers;

import models.Event;
import models.Round;
import models.User;
import org.joda.time.DateTime;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import views.html.homepage;

/**
 * Created by Chris on 5/10/2017.
 */
public class Rounds extends Controller {

  public static Result createRound() {
    User user = User.find.byId(Long.parseLong(session("id")));
    if (user == null ) {
      return unauthorized();
    }
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

    return ok(round.toJson());
  }

  public static Result joinRound() {
    User user = User.find.byId(Long.parseLong(session("id")));
    if (user == null ) {
      return unauthorized();
    }

    DynamicForm form = Form.form().bindFromRequest();
    String roundCode = form.get("code");
    Round round = Round.findByCode(roundCode);
    if (round == null) {
      return badRequest("Invalid round code.");
    }

    round.players.add(user);
    user.currentRound = round;
    user.isActive = true;
    user.team = User.Team.HUMAN;
    user.isMod = false;
    user.save();
    round.save();
    return ok(round.toJson());
  }

  public static Result getRound() {
    User user = User.find.byId(Long.parseLong(session("id")));
    if (user == null ) {
      return unauthorized();
    }
    return ok(user.currentRound.toJson());
  }

  public static Result addEvent() {
    User user = User.find.byId(Long.parseLong(session("id")));
    if (user == null) {
      return Results.unauthorized("Must be logged in.");
    }
    if (!user.isMod) {
      return forbidden("Must be a moderator to add an event to the schedule.");
    }

    DynamicForm form = Form.form().bindFromRequest();
    String title = form.get("title");
    DateTime startTime = new DateTime(Long.parseLong(form.get("startTime")));
    DateTime endTime = new DateTime(Long.parseLong(form.get("endTime")));
    String humanLocation = form.get("humanLocation");
    String zombieLocation = form.get("zombieLocation");
    new Event(user.currentRound, title, startTime, endTime, humanLocation, zombieLocation);
    return ok(user.currentRound.toJson());
  }

  public static Result getSchedule() {
    User user = User.find.byId(Long.parseLong(session("id")));
    if (user == null) {
      return Results.unauthorized("Must be logged in.");
    }
    return ok(user.currentRound.getScheduleAsJson());
  }

  public static Result roundPage() {
    return ok(homepage.render());
  }
}
