package be.ucll.feedback.controller;

import be.ucll.feedback.model.Feedback;
import be.ucll.feedback.model.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // need to tell Spring this is a REST controller
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @GetMapping("feedback")
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @PostMapping("feedback")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewFeedback(@RequestBody @Valid Feedback feedback) {
        feedbackService.addFeedback(feedback);
    }

    @GetMapping("feedback/id/{foo}")
    public Feedback getSpecificFeedbackById(@PathVariable("foo") int id) {
        return feedbackService.findFeedbackById(id);
    }

    @GetMapping("feedback/name/{name}")
    public List<Feedback> getSpecificFeedbackByName(@PathVariable() String name) {
        return feedbackService.findFeedbackByName(name);
    }

    // When a client needs to replace an existing Resource entirely, they can use PUT.
    // When they’re doing a partial update, they can use HTTP PATCH. Complicated -> for later.
    @PutMapping("feedback/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editSpecificWholeFeedback(@PathVariable("id") int id, @RequestBody @Valid Feedback changedFeedback) {
        feedbackService.changeFeedback(id, changedFeedback);
    }

    @DeleteMapping("feedback/{id}")
    // HTTP 204 No Content: The server successfully processed the request,
    // but is not returning any content
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeedback(@PathVariable("id") int id) {
        feedbackService.deleteFeedback(id);
    }

    // Still the same exception handler as in the previous version
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Requested feedback(s) not found!")
    @ExceptionHandler(value = IllegalArgumentException.class)
    public void badIdExceptionHandler() {
    }
}
