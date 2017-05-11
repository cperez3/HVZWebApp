package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.MapMarker;
import models.User;
import org.joda.time.DateTime;
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
public class Maps extends Controller {
  public static Result addMarker() {
//    response().setHeader("Accept", "application/json");
    User user = User.find.byId(Long.parseLong(session("id")));
    JsonNode body = request().body().asJson();
//    DynamicForm body = Form.form().bindFromRequest();
    System.out.println(body.toString());
    JsonNode position = body.get("position");
    double lat = position.get("lat").asDouble();
    double lng = position.get("lng").asDouble();
    System.out.println(position);
    String icon = body.get("icon").asText();
    String title = body.get("title").asText();

    MapMarker mapMarker = new MapMarker(title, icon,lat, lng, user);
    return ok(mapMarker.toJson());
  }

  public static Result getMarkers() {
    User user = User.find.byId(Long.parseLong(session("id")));
    // Find all Map Markers where the users team is the same as the creators team
    // and where the time is less than an hour ago
    List<MapMarker> mapMarkers = MapMarker.find.where()
        .eq("user.team", user.team)
        .eq("round", user.currentRound)
        .ge("time", DateTime.now().minusMinutes(60))
        .findList();
    List<JsonNode> markersJson = new ArrayList<>();
    for (MapMarker marker : mapMarkers) {
      markersJson.add(marker.toJson());
    }

    return ok(Json.toJson(markersJson));
  }
}
