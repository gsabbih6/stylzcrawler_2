package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/discover")
public class DiscoverController {
    @Autowired DiscoverService service;
    @GetMapping("/all")
    public ResponseEntity<List<DiscoverDTO>>getall(){
        return ResponseEntity.ok(service.getAll());
    }

}
