package be.ucll.feedback.model;

import javax.persistence.*;
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

    // we need to tie the feedback to a topic, so we create a new parameter here!
    // this is actually a foreign key in the database
    // there can be many feedbacks on one topic, so we have a many to one relationship
    @ManyToOne // JPA annotation - nothing to do with Spring actually
    private Topic topic;

    public Feedback() { }

    public Feedback(Integer id, String name, String feedbackMessage, int topicId) {
        this.id = id;
        this.name = name;
        this.feedbackMessage = feedbackMessage;

        // create the topic with this specific id
        this.createNewTopic(topicId);
    }

    // new constructor used in the CommandLineRunner, to populate the database
    public Feedback(String name, String feedbackMessage, int topicId) {
        this.name = name;
        this.feedbackMessage = feedbackMessage;

        // create the topic with this specific id
        this.createNewTopic(topicId);
    }

    private void createNewTopic(int topicId) {
        // create the topic with this specific id, but empty title & description
        // this is just to make the binding
        this.topic = new Topic(topicId,"","");
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
