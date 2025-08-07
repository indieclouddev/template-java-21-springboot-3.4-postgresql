package com.example.demo21;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo21.persistence.SessionEntity;
import com.example.demo21.persistence.SessionRepo;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.demo")
@EntityScan(basePackages = "com.example.demo.persistence")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /*
     * This is a simple REST controller that handles HTTP GET requests to the "/hello" endpoint.
     * It is just for demonstration purposes.
     */
    @RestController
    public static class DemoRestController {

        @Autowired
        private SessionRepo sessionRepo;

        @GetMapping("/hello")
        public String hello() {
            String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

            // Creating a session and saving it to the database
            // This is just to show if data is being saved correctly to the database
            SessionEntity session = new SessionEntity();
            session.setName("Demo " + dateTime);

            session = sessionRepo.save(session);
            
            return "Hello World! Session: " + session.getId() + " created at " + dateTime;
        }
    }
}