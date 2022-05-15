package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DealService {
    @Autowired
    DealRepository repository;

    public List<Deal> getAll() {
        List<Deal> deals = new ArrayList<>();
        repository.findAll().stream().forEach((deal) -> {
            if (deal.getExpirydate().compareTo(LocalDateTime.now()) > 0) {
                deals.add(deal);
            }
        });
        return deals;
    }

    public Deal save(Deal deal) {

        deal.setId(UUID.randomUUID());
        return repository.save(deal);
    }
}
