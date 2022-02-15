//package com.sabbih.demo;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//class Stylzcrawlerv1ApplicationTests {
//    @Autowired
//    private StoreRepository storeRepository;
//    @Autowired
//    private Search search;
//    @Autowired
//    ProductService productService;
//    @Autowired
//    ProductController productController;
//    @Autowired
//    UserService userService;
//
//    @Test
//    void searchTrends() throws IOException {
//        FilterModel model = new FilterModel();
//
//        model.setMaxPrice("200.00");
//        model.setMinPrice("100.00");
//
//        List<CategoryDTO> catl = new ArrayList<>();
//
//        CategoryDTO cdt = new CategoryDTO();
//        cdt.setId("e3096096-bea9-3dee-a70e-067b7b3cd75a");
//
//        catl.add(cdt);
//
//        model.setCategory(catl);
//        List<Store> store = new ArrayList<>();
//
////        Store st = new StoreDTO("Twillory");
////        store.add(st);
////        model.setStore(store);
//
//        search.queryFilter(model, "3");
//    }
//
//    @Test
//    void product() throws IOException {
////        productController.processPepperJamRequests();
////        System.out.println(productService
////                .uploadImageToIK
////                        ("https://cdn.shopify.com/s/files/1/0248/1455/1074/products/DOLCE1301_BLUSH_LB_CA_L.jpg?v=1612063364"));
////       ("https://cdn.shopify.com/s/files/1/0248/1455/1074/products/AMORE9441_NA-NA_LB_CA_L.jpg?v=1637431601"));
////        Product p = new Product();
////        p.setId(UUID.fromString("89c09438-0379-3932-bdc0-6ed67c6d2edc"));
////        p.setPrice(20.10);
////        productService.update(p);
//
//
//    }
//
//    @Test
//    void user() throws IOException, GeneralSecurityException {
//        userService.save("eyJhbGciOiJSUzI1NiIsImtpZCI6ImQwMWMxYWJlMjQ5MjY5ZjcyZWY3Y2EyNjEzYTg2YzlmMDVlNTk1NjciLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiYXpwIjoiMTQ1OTQwMTUyNDQ1LXEwNnJzNnMzOG1kZjBrZmh1b3IwdW5iMDk0azhsMGZoLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXVkIjoiMTQ1OTQwMTUyNDQ1LXEwNnJzNnMzOG1kZjBrZmh1b3IwdW5iMDk0azhsMGZoLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTEzOTg0MzI0Nzc5MDY0OTI4ODc3IiwiZW1haWwiOiJnc2FiYmloNUBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6ImhrNUR3ejhCa0lQVHRsaVNpZW5qUHciLCJuYW1lIjoiR29kZnJlZCBPcHBvbmciLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EtL0FPaDE0R2dIdHJVNzBKMWhMUlFUNGFXa2cyMGYtYWJUOHA5eUxpcGp3VFBpV0E9czk2LWMiLCJnaXZlbl9uYW1lIjoiR29kZnJlZCIsImZhbWlseV9uYW1lIjoiT3Bwb25nIiwibG9jYWxlIjoiZW4iLCJpYXQiOjE2NDEzNjg4NjUsImV4cCI6MTY0MTM3MjQ2NSwianRpIjoiMjcxYjNiZDNlOGQ3ZTk0ZTk4MGEyOGJkZWU3Y2M0ZjNiMTJmNmNkZiJ9.KUvkM10GWaDnyBCFnJmTxgMsFDfQkOQKhD8VISKGPdkNjXTsO23anLvltfXt6fQiWmkus2mReySbL87sH9eUyveEklIioyQuil4QGzDTDob2gj1QPJQ1l0pxknOkkmWbzAfiD5JtcaGFPCROmBFjsRlryzjIc9LVOul3m0ZRimC7egpJ9JI_k7vQocDmP9K4mEoYFFuWw1pZPeKeuhbbqjKbfl81rsCZfcfaESd7fSRq62pVaOF4x45ree8k7kHJOH-jqmrJYEirAlE3cjnRV6Ab5Kft1FJTzZ5qSFZLsM5WqWZU9MlEyqhBMSH4tV5142mu5KFYGhnKa7fL4Dmq2g", "google");
//    }
//
//    @Test
//    void crawler() throws IOException {
//        Product p = new Product();
//        p.setProgram_name(StylConstants.COMPANY_EVERYMAN);
//        p.setPayment_url("https://everyman.co/products/hideout-pack?source=pepperjam&publisherId=254880&clickId=3769104842");
//        productService.crawlContent(p);
////        productController.crawlContent();
//    }
//}
