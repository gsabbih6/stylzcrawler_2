package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/deals")
public class DealController {
    @Autowired
    DealService service;

    @GetMapping("/all")
    public ResponseEntity<List<Deal>> getDeals() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Deal> addDeal(@RequestBody Deal deal) {
        return ResponseEntity.ok(service.save(deal));
    }
}
