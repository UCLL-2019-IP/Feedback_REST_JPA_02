package be.ucll.feedback.controller;

import be.ucll.feedback.model.Feedback;
import be.ucll.feedback.model.MyService;
import be.ucll.feedback.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // need to tell Spring this is a REST controller
public class FeedbackController {
    @Autowired
    MyService feedbackService;

    @GetMapping("topic/{topicId}/feedback")
    public List<Feedback> getAllFeedbacks(@PathVariable int topicId) {
        return feedbackService.getAllFeedbacks(topicId);
    }

    @PostMapping("topic/{topicId}/feedback")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewFeedback(@RequestBody @Valid Feedback feedback, @PathVariable int topicId) {
        // create feedback and set topic, this is just to have the binding with topic
        feedback.setTopic(new Topic(topicId,"",""));
        feedbackService.addFeedback(feedback);
    }

    @GetMapping("topic/{topicId}/feedback/{id}")
    public Feedback getSpecificFeedbackById(@PathVariable int id) { // we only need the id of the feedback
        return feedbackService.findFeedbackById(id);
    }

    @GetMapping("topic/{topicId}/feedback/name/{name}")
    public List<Feedback> getSpecificFeedbackByName(@PathVariable int topicId, @PathVariable String name) {
        return feedbackService.findFeedbackByName(topicId, name);
    }

    // When a client needs to replace an existing Resource entirely, they can use PUT.
    // When they’re doing a partial update, they can use HTTP PATCH. Complicated -> for later.
    @PutMapping("topic/{topicId}/feedback/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editSpecificWholeFeedback(@PathVariable int topicId, @PathVariable int id, @RequestBody @Valid Feedback changedFeedback) {
        // create feedback and set topic, this is just to have the binding with topic
        changedFeedback.setTopic(new Topic(topicId,"",""));
        feedbackService.changeFeedback(id, changedFeedback);
    }

    @DeleteMapping("topic/{topicId}/feedback/{id}")
    // HTTP 204 No Content: The server successfully processed the request, but is not returning any content
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeedback(@PathVariable int id) {
        feedbackService.deleteFeedback(id);
    }

    // Still the same exception handler as in the previous version
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Requested feedback(s) not found!")
    @ExceptionHandler(value = IllegalArgumentException.class)
    public void badIdExceptionHandler() {
    }
}
