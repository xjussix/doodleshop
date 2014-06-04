/*
 * Created by Daniel Marell 14-02-22 11:40
 */
package se.cag.doodleshop.model;

//import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class DoodleMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATEDAT", nullable = false)
    //@Type(type = "timestamp")
    private Timestamp created;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DoodleMessage{" +
                "id=" + id +
                ", created=" + created +
                ", author='" + author + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
