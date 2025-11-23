package com.example.apitesting.web;

import com.example.apitesting.model.ApiUser;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final Logger LOG = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        LOG.info("Health check endpoint called");
        return ResponseEntity.ok("UP");
    }

    @GetMapping("/users")
    public ResponseEntity<List<ApiUser>> users() {
        LOG.info("Returning demo users");
        List<ApiUser> demoUsers = List.of(
                new ApiUser(1L, "Ada Lovelace", "ada@example.com"),
                new ApiUser(2L, "Alan Turing", "alan@example.com"),
                new ApiUser(3L, "Grace Hopper", "grace@example.com")
        );
        return ResponseEntity.ok(demoUsers);
    }
}
