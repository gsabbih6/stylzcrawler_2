//package com.sabbih.demo;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.io.IOException;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//class Stylzcrawlerv1ApplicationTests {
//    @Autowired
//    private StoreRepository storeRepository;
//    @Autowired
//    private Search search;
//    @Autowired
//    ProductService productService; @Autowired ProductController productController;
//
//    @Test
//    void searchTrends() throws IOException {
//        search.query("hat black", "0");
//    }
//
//    @Test
//    void crawler() {
//        Product p = new Product();
//        p.setPayment_url("https://www.gopjn.com/t/Qz9KS0VIP0RHRkpKQj9KS0VI?url=https%3A%2F%2Fwww.ingridandisabel.com%2Fproducts%2F1114sl2-seamless-cami");
////        productService.crawlContent(p);
//        productController.crawlContent();
//    }
//}
