package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;

    public Store create(Store store) {
//        Store sf = storeRepository.existsStoreByUrl(store.url);
//        if (storeRepository.existsByUrl(store.url)) return store;
        return storeRepository.save(store);
    }

    public Store update(Store store) {
        return null;
    }

    public List<Store> getAll() {
        return null;
    }

    public void delete(UUID uuid) {

    }

    public Optional<Store> get(UUID fromString) {
        return storeRepository.findById(fromString);
    }
}
