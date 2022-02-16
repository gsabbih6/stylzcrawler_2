package com.sabbih.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class IngressController {
    @GetMapping("/")
    public ResponseEntity.BodyBuilder ingressOk(@RequestParam String productid) {
     return ResponseEntity.ok();

    }
}
