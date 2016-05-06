package models;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
/**
 * @author      Mariah Flaim
 * @author      Evan Willner
 * @author      Elizabeth Dellea
 * @author      Nikhil Patel
 * @version     1.0
 * @since       2016-04-28
 **/
@Entity
public class Message extends Model {
    @Id//primary key
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;

    @Column(name = "created_at")
    public Date createdAt;

    public String gameCode;
    public String recipient;
    public String message;
    public String location;
    public String sender;

    @Override
    public void save() {
        createdAt();
        super.save();
    }

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    public Message(){
    }

}
