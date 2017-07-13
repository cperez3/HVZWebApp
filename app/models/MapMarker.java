package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.DateTime;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Chris on 5/10/2017.
 */
@Entity
public class MapMarker extends Model {
  @Id
  public long id;
  public String title;
  public String icon;
  public double latitude;
  public double longitude;
  public String position;
  public DateTime time;
  @ManyToOne
  public Round round;
  @ManyToOne
  public User user;

  public static Finder<Long, MapMarker> find = new Finder<>(Long.class, MapMarker.class);

  public MapMarker() {
  }

  public MapMarker(String title, String icon, double latitude, double longitude, User user) {
    this.title = title;
    this.icon = icon;
    this.latitude = latitude;
    this.longitude = longitude;
    this.user = user;
    this.round = user.currentRound;
    this.time = DateTime.now();
    this.save();
  }

  public MapMarker(String title, String icon, String position, User user) {
    this.title = title;
    this.icon = icon;
    this.position = position;
    this.user = user;
    this.round = user.currentRound;
    this.time = DateTime.now();
    this.save();
  }

  public JsonNode toJson() {
    ObjectNode objectNode = Json.newObject();
    objectNode.put("id", id);
    objectNode.put("title", title);
    objectNode.put("icon", icon);
    objectNode.put("roundId", round.id);
    objectNode.put("userId", user.id);
    ObjectNode position = Json.newObject();
    position.put("lat", latitude);
    position.put("lng", longitude);
    objectNode.put("position", position);
    objectNode.put("time", time.getMillis());
    return objectNode;
  }
}