package models;

import org.joda.time.DateTime;

import javax.persistence.Id;

/**
 * Created by Chris on 4/25/2017.
 */
public class Event {

  @Id
  public Long id;
  public String title;
  public DateTime startTime;
  public DateTime endTime;
  public Round round;
  public String humanLocation;
  public String zombieLocation;

}
