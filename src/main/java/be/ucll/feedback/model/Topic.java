package be.ucll.feedback.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
Tell JPA that this should be saved in the database, by adding @Entity
a table "feedback" will be made in the database.
Fields will be made in that table, corresponding to the variables in the class
in this case: id, title and description.
*/
@Entity
public class Topic {
    // tell JPA that the field id will be the primary key, by adding the @Id annotation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // generate id automatically, no more AtomicInteger
    private int id;

    @NotNull
    @NotEmpty
    @Size(min=2, max=30)
    private String title;

    @NotNull
    @NotEmpty
    @Size(min=5, max=70)
    private String description;

    public Topic() { }

    public Topic(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // new constructor used in the CommandLineRunner, to populate the database
    public Topic(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
