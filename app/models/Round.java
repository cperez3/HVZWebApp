/**
 * @author Mariah Flaim
 * @author Evan Willner
 * @author Elizabeth Dellea
 * @author Nikhil Patel
 * @version 1.0
 * @since 2016-03-28
 **/

package models;

//import statements

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.api.mvc.AnyContentAsRaw;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Round extends Model {
  @Id//primary key
  public long id;
  public String gameCode;

  /*New Code*/
  public String title;
  public String description;
  public String roundRules;
  public String gameRules;
  public String contactInfo;
  @OneToMany(mappedBy = "round")
  public List<Event> schedule = new ArrayList<>();
  @OneToMany(mappedBy = "currentRound")
  public List<User> players = new ArrayList<>();
  @OneToMany(mappedBy = "round")
  public List<Message> messages = new ArrayList<>();
  @OneToMany(mappedBy = "round")
  public List<MapMarker> mapMarkers = new ArrayList<>();

  public static Finder<Long, Round> find = new Finder<>(Long.class, Round.class);

  public Round() {
  }

  public Round(String title, String description, String roundRules, String gameRules, String contactInfo, User creator) {
    this.title = title;
    this.description = description;
    this.roundRules = roundRules;
    this.gameRules = gameRules;
    this.contactInfo = contactInfo;
    players.add(creator);

    //TODO: Generate game code a good way
    gameCode = generateRandomCode();
    Round dup = findByCode(gameCode);

    while (dup != null) {
      gameCode = generateRandomCode();
      dup = findByCode(gameCode);
    }

    save();
  }

  public Round findByCode(String code) {
    return find.where().eq("gameCode", code).findUnique();
  }

  public String generateRandomCode() {
    int codeLength = 4;
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < codeLength; i++) {
      Random random = new Random();
      int j = random.nextInt(characters.length());
      stringBuilder.append(characters.charAt(j));
    }
    return stringBuilder.toString();
  }

  public ObjectNode toJson() {
    ObjectNode objectNode = Json.newObject();
    objectNode.put("id", id);
    objectNode.put("gameCode", gameCode);
    objectNode.put("title", title);
    objectNode.put("description", description);
    objectNode.put("roundRules", roundRules);
    objectNode.put("gameRules", gameRules);
    objectNode.put("contactInfo", contactInfo);

    return objectNode;
  }
}
