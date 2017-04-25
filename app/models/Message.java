package models;

import org.joda.time.DateTime;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mariahflaim on 4/28/16.
 */
@Entity
public class Message extends Model {
  @Id//primary key
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int id;

  @Column(name = "created_at")
  public Date createdAt;

  public String gameCode;
  public String recipient;
  public String message;
  public String location;
  public String senderOld;

  /*New Code*/
  @ManyToOne
  public Round round;
  @ManyToOne
  public User sender;
  public DateTime time;

  @Override
  public void save() {
    createdAt();
    super.save();
  }

  @PrePersist
  void createdAt() {
    this.createdAt = new Date();
  }

  public Message() {
  }

  public enum MessageType {
    HUMAN, ZOMBIE, MOD_ALERT
  }

}
