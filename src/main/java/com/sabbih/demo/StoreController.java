package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@CrossOrigin
@RestController
@RequestMapping("api/store")
public class StoreController {
    @Autowired
    StoreService service;

    @GetMapping()
    public ResponseEntity<Object> getStore(@PathVariable String id) {
        Optional<Store> opt = service.get(UUID.fromString(id));
        return ResponseEntity.ok(opt.get());
    }

    @GetMapping("/store_count")
    public ResponseEntity<Long> getStoreCount() throws IOException {
        return ResponseEntity.ok(service.getTotalItems());
    }
    @GetMapping("/all")
    public ResponseEntity<List<Store>> getStores() throws IOException {
        return ResponseEntity.ok(service.getAll());
    }
}
