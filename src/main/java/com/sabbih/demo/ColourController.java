package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/color")
public class ColourController {

    @Autowired
    ColourService service;

    @GetMapping("/all")
    public ResponseEntity<List<Colour>> all() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/create")
    public ResponseEntity<Colour> all(@RequestParam String colour) {
        return ResponseEntity.ok(service.save(new Colour(colour)));
    }
}
