package be.ucll.feedback.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
Tell JPA that this should be saved in the database, by adding @Entity
a table "feedback" will be made in the database.
Fields will be made in that table, corresponding to the variables in the class
in this case: id, name and feedbackMessage.
*/
@Entity
public class Feedback {
    // tell JPA that the field id will be the primary key, by adding the @Id annotation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // generate id automatically, no more AtomicInteger
    private int id;

    @NotNull
    @NotEmpty
    @Size(min=2, max=20)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min=5, max=50)
    private String feedbackMessage;

    public Feedback() { }

    public Feedback(Integer id, String name, String feedbackMessage) {
        this.id = id;
        this.name = name;
        this.feedbackMessage = feedbackMessage;
    }

    // new constructor used in the CommandLineRunner, to populate the database
    public Feedback(String name, String feedbackMessage) {
        this.name = name;
        this.feedbackMessage = feedbackMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }
}
