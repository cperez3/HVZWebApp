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

import play.api.mvc.AnyContentAsRaw;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Round extends Model {
  @Id//primary key
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int id;
  public String gameCode;

  /*New Code*/
  public String title;
  public String description;
  public String roundRules;
  public String gameRules;
  public String contactInfo;
  @OneToMany
  public List<Event> schedule;
  @OneToMany
  public List<User> players;
  @OneToMany
  public List<Message> messages;

  public Round() {
  }


}
