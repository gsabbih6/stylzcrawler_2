package com.sabbih.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
public class IngressController {
    @GetMapping("/")
    public ResponseEntity<String> ingressOk() {
     return ResponseEntity.ok("");

    }
}
