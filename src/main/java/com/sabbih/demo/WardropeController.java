package com.sabbih.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

@RestController
//@CrossOrigin
@RequestMapping("api/wardrobe")
public class WardropeController {
    @Autowired
    WardrobeService service;

    @GetMapping("/{id}")
    public ResponseEntity<WardrobeDTO> getOne(@Valid @PathVariable String id) {
        WardrobeDTO result = service.getByID(id);
        if (result != null) return ResponseEntity.ok(result);
        return null;
    }

    @GetMapping("/create")
    public ResponseEntity<WardrobeDTO> create(@RequestParam String name, @RequestParam String userid) {

        Wardrobe wardrobe = new Wardrobe();
        wardrobe.setId(UUID.nameUUIDFromBytes((name + userid).getBytes()));
        wardrobe.setUserid(UUID.fromString(userid));
        wardrobe.setName(name);
        wardrobe.setProductids(new LinkedHashSet<>());
        WardrobeDTO result = service.create(wardrobe);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/wardrobes")
    public ResponseEntity<List<WardrobeDTO>> getbyUser(@RequestParam String userid) {

        List<WardrobeDTO> result = service.getByUserid(userid);


        return ResponseEntity.ok(result);
    }

    @PostMapping("/update")
    public ResponseEntity<WardrobeDTO> update(@RequestBody Wardrobe wardrobe) {

        WardrobeDTO result = service.update(wardrobe);

        return ResponseEntity.ok(result);
    }
}
