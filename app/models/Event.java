package models;

import org.joda.time.DateTime;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Chris on 4/25/2017.
 */
@Entity
public class Event {

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

}
