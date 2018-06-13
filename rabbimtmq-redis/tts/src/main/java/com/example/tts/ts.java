package com.example.tts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ts {
    @GetMapping("/shut")
    public String shut() {
        return "ok";
    }
}
