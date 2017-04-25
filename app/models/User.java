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

import play.db.ebean.Model;

import javax.persistence.*;

import com.avaje.ebean.SqlUpdate;

import java.util.List;

@Entity
public class User extends Model {

  @Id      //primary key
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int id;

  public String displayName;
  public String password;
  public String email;
  public Boolean isMod;
  public String teamOld;
  public Boolean isActive;
  public String gameCode;


  /*New Code*/
  public String name;
  @ManyToOne
  public Round currentRound;
  public Team team;
  @OneToMany
  public List<Message> messages;

  public User() {
  }

  public enum Team {HUMAN, ZOMBIE, NONPLAYING}

}
