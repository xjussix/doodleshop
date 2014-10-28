/*
 * Created by Daniel Marell 14-02-22 11:40
 */
package se.caglabs.doodleshop.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DoodleMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATEDAT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    public DoodleMessage() {
    }

    public DoodleMessage(String author, String message) {
        this.author = author;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
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
