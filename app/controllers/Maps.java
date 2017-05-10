package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.MapMarker;
import models.User;
import org.joda.time.DateTime;
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
    User user = User.find.byId(Long.parseLong(session("id")));
    JsonNode body = request().body().asJson();

    JsonNode position = body.get("position");
    double longitude = position.get("lng").asDouble();
    double latitude = position.get("lat").asDouble();
    String icon = body.get("icon").asText();
    String title = body.get("title").asText();

    MapMarker mapMarker = new MapMarker(title, icon, latitude, longitude, user);
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
