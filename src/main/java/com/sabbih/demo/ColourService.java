package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ColourService {
    @Autowired
    ColourRepository repository;

    public List<Colour> getAll() {

        return repository.findAll();
    }

    public Colour save(Colour c) {
        return repository.save(c);
    }
}
