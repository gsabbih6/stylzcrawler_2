package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping()
    public ResponseEntity<Object> getStore(@PathVariable String id) {
        Optional<Store> opt = service.get(UUID.fromString(id));
        return ResponseEntity.ok(opt.get());
    }

    @GetMapping("/pepperstores")
    public ResponseEntity<String> getPepperStores() throws IOException {
        String url = "https://api.pepperjamnetwork.com/20120402/publisher/advertiser/product?";
        String apiKey = "281b24302f4c6ccfd725d79dd222bffe1414d903eb9d0c1ca1feb20af9787272";
        PepperJamStore pepperJamStore =
                restTemplate.getForEntity(url + "apiKey=" + apiKey + "&" + "format=json&status=joined",
                        PepperJamStore.class).getBody();

//        Pagination pagination = pepperJamProduct.getMeta().getPagination();
        System.err.println(url + "apiKey=" + apiKey + "&" + "format=json&status=joined");
        for (StoreDatum item : pepperJamStore.getData()
        ) {
            prepareAnSave(item);
        }
        Next next = pepperJamStore.getMeta().getPagination().getNext();
        while (next != null) {
            PepperJamStore ppp = restTemplate.getForEntity(next.getHref(), PepperJamStore.class).getBody();
            next = ppp.getMeta().getPagination().getNext();
            List<StoreDatum> plist = ppp.getData();
            for (StoreDatum item : plist
            ) {
                prepareAnSave(item);
            }
        }
        return (ResponseEntity<String>) ResponseEntity.ok();
    }

    @GetMapping("/store_count")
    public ResponseEntity<Long> getStoreCount() throws IOException {
        return ResponseEntity.ok(service.getTotalItems());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Store>> getStores() throws IOException {
        return ResponseEntity.ok(service.getAll());
    }


    private ResponseEntity<Store> prepareAnSave(StoreDatum item) throws IOException {
        Store s = buildStore(item);
        if (s != null)
            return ResponseEntity.ok(service.create(s));
        return (ResponseEntity<Store>) ResponseEntity.status(HttpStatus.NOT_FOUND);
    }

    private Store buildStore(StoreDatum item) {
        Store store = new Store();
        store.setId(UUID.nameUUIDFromBytes(item.getName().getBytes()));
        store.setName(item.getName());
        store.setCurrency(item.getCurrency());
        store.setDescription(item.getDescription());
        store.setEmail(item.getEmail());
        store.setLogo(item.getLogo());
        store.setStatus(item.getStatus());
        store.setAddress1(item.getAddress1());
        store.setAddress2(item.getAddress2());
        store.setCity(item.getCity());
        store.setContactName(item.getContactName());
        store.setCookieDuration(item.getCookieDuration());
        store.setCountryCode(item.getCountryCode());
        store.setDeepLinking(item.getDeepLinking());
        store.setFlatPayout(item.getFlatPayout());
        store.setJoinDate(item.getJoinDate());
        store.setMobileTracking(item.getMobileTracking());
        store.setPercentagePayout(item.getPercentagePayout());
        store.setPhone(item.getPhone());
        store.setProductFeed(item.getProductFeed());
        store.setProhibitedStates(item.getProhibitedStates());
        store.setStateCode(item.getStateCode());
        store.setWebsite(item.getWebsite());
        store.setZipCode(item.getZipCode());


        return store;
    }
}
