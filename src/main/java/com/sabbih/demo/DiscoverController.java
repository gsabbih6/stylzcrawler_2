package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/discover")
public class DiscoverController {
    @Autowired
    DiscoverService service;

    @GetMapping("/all")
    public ResponseEntity<List<DiscoverDTO>> getall() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Discover> addDiscvoer(@RequestBody Discover discover) {
        return ResponseEntity.ok(service.save(discover));
    }
}
