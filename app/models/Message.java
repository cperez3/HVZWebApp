package models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.DateTime;
import play.db.ebean.Model;
import play.libs.Json;

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
  public MessageType messageType;

  public static Finder<Long, Message> find = new Finder<>(Long.class, Message.class);

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

  public Message(String message, User sender) {
    this.message = message;
    this.sender = sender;
    this.round = sender.currentRound;
    this.time = DateTime.now();
    save();
  }


  public static MessageType getMessageTypeFromTeam(User.Team team) {
    switch (team) {
      case HUMAN: return MessageType.HUMAN;
      case ZOMBIE: return MessageType.ZOMBIE;
      default: return MessageType.MOD_ALERT;
    }
  }

  public ObjectNode toJson() {
    ObjectNode node = Json.newObject();
    node.put("id", id);
    node.put("message", message);
    node.put("sender", sender.toJson());
    node.put("time", time.getMillis());
    node.put("type", messageType.toString());
    return node;
  }

  public enum MessageType {
    HUMAN, ZOMBIE, MOD_ALERT
  }

}
