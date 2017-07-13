package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Message;
import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 5/10/2017.
 */
public class Messages extends Controller {
  public static Result sendMessage() {
    User user = User.find.byId(Long.parseLong(session("id")));
    if (user == null ) {
      return unauthorized();
    }

    DynamicForm form = Form.form().bindFromRequest();

    String messageText = form.get("message");
    if (messageText.isEmpty()) {
      return badRequest("Message must have content.");
    }
    new Message(messageText, user);
    return noContent();
  }


  public static Result getMessages() {
    User user = User.find.byId(Long.parseLong(session("id")));
    if (user == null ) {
      return unauthorized();
    }

    List<Message> messages = Message.find.where()
        .eq("messageType", Message.getMessageTypeFromTeam(user.team))
        .eq("round", user.currentRound)
        .orderBy("time desc")
        .findList();
    List<JsonNode> messagesJson = new ArrayList<>();
    for (Message message : messages) {
      messagesJson.add(message.toJson());
    }
    return ok(Json.toJson(messagesJson));
  }

  public static Result getModAlerts() {
    User user = User.find.byId(Long.parseLong(session("id")));
    if (user == null ) {
      return unauthorized();
    }

    List<Message> messages = Message.find.where()
        .eq("messageType", Message.MessageType.MOD_ALERT)
        .eq("round", user.currentRound)
        .orderBy("time desc")
        .findList();
    List<JsonNode> messagesJson = new ArrayList<>();
    for (Message message : messages) {
      messagesJson.add(message.toJson());
    }
    return ok(Json.toJson(messagesJson));
  }

  public static Result sendModAlert() {
    User user = User.find.byId(Long.parseLong(session("id")));
    if (user == null ) {
      return unauthorized();
    }
    if (!user.isMod) {
      return forbidden("Must have mode privileges");
    }

    DynamicForm form = Form.form().bindFromRequest();

    String messageText = form.get("message");
    if (messageText.isEmpty()) {
      return badRequest("Message must have content.");
    }

    Message message = new Message(messageText, user);
    message.messageType = Message.MessageType.MOD_ALERT;
    message.save();

    return getModAlerts();
  }
}
