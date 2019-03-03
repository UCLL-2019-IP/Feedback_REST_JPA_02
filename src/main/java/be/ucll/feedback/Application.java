package be.ucll.feedback;

import be.ucll.feedback.model.Feedback;
import be.ucll.feedback.repository.FeedbackRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication  // need to tell Spring this is a Spring Boot Application
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

    // use this to fill up the database from the start
    @Bean
    CommandLineRunner runner(FeedbackRepository repo){
        return args -> {
            repo.save(new Feedback("Jozef", "Dat kan hier niet ver meer zijn!"));
            repo.save(new Feedback("Maria", "Maar vraag dan toch eens de weg!"));
        };
    }
}

