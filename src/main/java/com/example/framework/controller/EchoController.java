package com.example.framework.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/echo")
public class EchoController {

    @GetMapping
    public Map<String, Object> hello() {
        return Map.of("message", "echo", "status", "ok");
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> echo(@RequestBody(required = false) Map<String, Object> body) {
        return ResponseEntity.ok(Map.of(
                "received", body == null ? Map.of() : body,
                "status", "ok"
        ));
    }
}
