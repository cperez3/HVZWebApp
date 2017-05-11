package models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.DateTime;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Chris on 4/25/2017.
 */
@Entity
public class Event extends  Model{

  @Id
  public Long id;
  public String title;
  public DateTime startTime;
  public DateTime endTime;
  @ManyToOne
  public Round round;
  public String humanLocation;
  public String zombieLocation;

  public static Model.Finder<Long, Event> find = new Model.Finder<>(Long.class, Event.class);

  public Event() {
  }

  public Event(Round round, String title, DateTime startTime, DateTime endTime, String humanLocation, String zombieLocation) {
    this.title = title;
    this.startTime = startTime;
    this.endTime = endTime;
    this.round = round;
    this.humanLocation = humanLocation;
    this.zombieLocation = zombieLocation;
    save();
  }

  public ObjectNode toJson() {
    ObjectNode objectNode = Json.newObject();
    objectNode.put("id", id);
    objectNode.put("title", title);
    objectNode.put("startTime", startTime.getMillis());
    objectNode.put("endTime", endTime.getMillis());
    objectNode.put("humanLocation", humanLocation);
    objectNode.put("zombieLocation", zombieLocation);
    return null;
  }
}
