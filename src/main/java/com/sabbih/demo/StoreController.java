package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController("/store")
public class StoreController {
    @Autowired
    StoreService service;

    @GetMapping()
    public ResponseEntity<Object> getStore(@PathVariable String id) {
        Optional<Store> opt = service.get(UUID.fromString(id));
        return ResponseEntity.ok(opt.get());
    }
}
