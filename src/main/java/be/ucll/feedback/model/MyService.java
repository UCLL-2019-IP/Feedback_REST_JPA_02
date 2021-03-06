package be.ucll.feedback.model;

import be.ucll.feedback.repository.FeedbackRepository;
import be.ucll.feedback.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service// need to tell Spring this is a service
public class MyService {
    @Autowired
    TopicRepository topicRepository; // dependency injection of the repository

    @Autowired
    FeedbackRepository feedbackRepository; // dependency injection of the repository

    // no more hardcoded values here!
    public MyService() { }

    // *************************
    // TOPICS
    // *************************

    // just return the whole list, Spring takes care of conversion to JSON
    public List<Topic> getAllTopics() {
        // look at what the autocomplete comes up with if you use the repository!
        return topicRepository.findAll();
    }

    public void addTopic(Topic topic) {
        topicRepository.save(topic); // all we need to do to save a topic
    }

    // look for a topic by id (see controller)
    public Topic findTopicById(int id) {
        /*
        This returns an "Optional". That is kind of a promise that can be broken.
        If there is nothing with that id in the database table, then the promise
        is broken. If that happens, you can act on it in different ways. Here, I
        just throw the IllegalArgumentException, like I always did.
        */
        return topicRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void changeTopic(int id, Topic changedTopic) {
        changedTopic.setId(id); // use id from url, so people can't mess up
        // if row exists with this id, row is updated, otherwise it's created
        topicRepository.save(changedTopic); // all we need to do to change a topic
    }

    public void deleteTopic(int id) {
        topicRepository.deleteById(id);
    }

    // look for a topic by title (see controller)
    public List<Topic> findTopicByTitle(String title) {
        List<Topic> topics = new ArrayList<>();
        // needed to define the method in the repository!
        topics = topicRepository.findByTitle(title);
        // if nothing returned, throw an IllegalArgumentException
        if(topics.isEmpty()){
            throw new IllegalArgumentException();
        }
        else {
            return topics;
        }
    }

    // *************************
    // FEEDBACKS
    // *************************

    // just return the whole list, Spring takes care of conversion to JSON
    public List<Feedback> getAllFeedbacks(int topicId) {
        // look at what the autocomplete comes up with if you use the repository!
        return feedbackRepository.findByTopicId(topicId);
    }

    public void addFeedback(Feedback feedback) {
        feedbackRepository.save(feedback); // all we need to do to save a feedback
    }

    // look for a feedback by id (see controller)
    public Feedback findFeedbackById(int id) {
        /*
        This returns an "Optional". That is kind of a promise that can be broken.
        If there is nothing with that id in the database table, then the promise
        is broken. If that happens, you can act on it in different ways. Here, I
        just throw the IllegalArgumentException, like I always did.
        */
        return feedbackRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void changeFeedback(int id, Feedback changedFeedback) {
        changedFeedback.setId(id); // use id from url, so people can't mess up
        // if row exists with this id, row is updated, otherwise it's created
        feedbackRepository.save(changedFeedback); // all we need to do to change a feedback
    }

    public void deleteFeedback(int id) {
        feedbackRepository.deleteById(id);
    }

    // look for a feedback by name (see controller)
    public List<Feedback> findFeedbackByName(int topicId, String name) {
        List<Feedback> feedbacks = new ArrayList<>();
        // needed to define the method in the repository!
        feedbacks = feedbackRepository.findByTopicIdAndName(topicId, name);
        // if nothing returned, throw an IllegalArgumentException
        if(feedbacks.isEmpty()){
            throw new IllegalArgumentException();
        }
        else {
            return feedbacks;
        }
    }
}
