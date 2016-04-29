package models;
import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Version;
import java.util.Date;
import javax.persistence.PrePersist;
/**
 * Created by mariahflaim on 4/28/16.
 */
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
