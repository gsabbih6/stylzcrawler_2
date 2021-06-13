package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/visit")
public class VisitController {
    @Autowired
    ProductService productService;
    @Autowired
    VisitService visitService;

    @GetMapping("/{productid}")
    public ResponseEntity<Object> redirectToExternalUrl(@Valid @PathVariable String productid) throws URISyntaxException {
        Optional<Product> opt = productService.get(UUID.fromString(productid));
        Visit v = new Visit(UUID.fromString(productid));
        visitService.create(v);


//        StringBuilder sb = new StringBuilder();
//        sb.append(opt.get().payment_url).append()
        URI yahoo = new URI(opt.get().payment_url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(yahoo);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}