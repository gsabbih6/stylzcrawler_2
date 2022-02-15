package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PurchaseService {

    @Autowired PurchaseRepository repository;

    public Purchase create(String productid){

        Purchase purchase =new Purchase();
        purchase.setId(UUID.randomUUID());
        purchase.setProductid(UUID.fromString(productid));
        return repository.save(purchase);
    }
}
