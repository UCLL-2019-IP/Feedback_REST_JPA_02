package be.ucll.feedback;

import be.ucll.feedback.model.Feedback;
import be.ucll.feedback.model.Topic;
import be.ucll.feedback.repository.FeedbackRepository;
import be.ucll.feedback.repository.TopicRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication  // need to tell Spring this is a Spring Boot Application
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

    // use this to fill up the database from the start
    @Bean
    @Order(1) // do this first
    CommandLineRunner runnerTopics(TopicRepository repo){
        return TopicArgs -> {
            repo.save(new Topic("Reizen", "Reizen, vroeger en nu."));
            repo.save(new Topic("Cursus IP", "Feedback op de cursus IP."));
            repo.save(new Topic("Filosofie", "Existentiële prietpraat."));
        };
    }

    @Bean
    @Order(2) // do this second
    CommandLineRunner runnerFeedbacks(FeedbackRepository repo){
        return feedbackArgs -> {
            // topic reizen
            repo.save(new Feedback("Jozef", "Dat kan hier niet ver meer zijn!", 1));
            repo.save(new Feedback("Maria", "Maar vraag dan toch eens de weg!", 1));

            // topic cursus IP
            repo.save(new Feedback("Rudy", "Dat kan hier veel beter!", 2));
            repo.save(new Feedback("Elke", "Dit is het beste wat je kan krijgen!", 2));
            repo.save(new Feedback("Rudi", "Dat gaat hier niet vooruit!", 2));

            // topic filosofie
            repo.save(new Feedback("Jules Kabas", "'t zijn zotten die werken!", 3));
            repo.save(new Feedback("Rudy", "Waar blijven die AI's om mijn werk over te nemen?", 3));
        };
    }
}

