package com.sabbih.demo;


import com.google.gson.Gson;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.*;

//@CrossOrigin
// hangle http requests
@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    ProductService service;
    @Autowired
    StoreService storeService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    RestHighLevelClient elasRestHighLevelClient;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        Optional<Product> opt = service.get(UUID.fromString(id));
        if (opt.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(opt.get(), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) throws IOException {
        Product p = service.create(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Products> getAllProduct(@RequestParam String page) throws IOException {
        page = page == null ? "1" : page;
        Products allProducts = service.getAll(page);
        if (allProducts == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(allProducts);
        //findById(userId).map(this.customerRepository::findByUser).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) throws IOException {
        if (service.get(UUID.fromString(id)).isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.delete(UUID.fromString(id));
        return new ResponseEntity<>("Item Deleted", HttpStatus.OK);
    }

    @GetMapping("/product_count")
    public ResponseEntity<Long> totalItems() throws IOException {
        return ResponseEntity.ok(service.getTotalItems());
    }


    @GetMapping("/pepperjamnetwork")
    public void processPepperJamRequests(@RequestParam String programid) throws Exception {
        String url = "https://api.pepperjamnetwork.com/20120402/publisher/creative/product?";
        String apiKey = "281b24302f4c6ccfd725d79dd222bffe1414d903eb9d0c1ca1feb20af9787272";
        PepperJamProduct pepperJamProduct =
                restTemplate.getForEntity(url + "apiKey=" + apiKey + "&" + "format=json&programIds=" + programid,
                        PepperJamProduct.class).getBody();

//        Pagination pagination = pepperJamProduct.getMeta().getPagination();
        System.err.println(url + "apiKey=" + apiKey + "&" + "format=json&programIds=" + programid);
        for (Datum item : pepperJamProduct.getData()
        ) {
            prepareAnSave(item);
        }
        Next next = pepperJamProduct.getMeta().getPagination().getNext();
        while (next != null) {
            PepperJamProduct ppp = restTemplate.getForEntity(next.getHref(), PepperJamProduct.class).getBody();
            next = ppp.getMeta().getPagination().getNext();
            List<Datum> plist = ppp.getData();
//        Datum item= plist.get(1);
//        System.err.println(plist.get(1));
            for (Datum item : plist
            ) {
                prepareAnSave(item);
                prepareAnSaveElastic(item);
            }
////
        }

    }

    void prepareAnSaveElastic(Datum item) throws IOException {
        Product p = getProduct(item);
        if (p != null) {
            UpdateRequest request = new UpdateRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME, p.getId().toString());
            request.doc(new Gson().toJson(p), XContentType.JSON);
            request.docAsUpsert(true);
            elasRestHighLevelClient.update(request, RequestOptions.DEFAULT);
        }
    }

    void prepareAnSave(Datum item) throws Exception {

        if (getProduct(item) != null)
            service.create(getProduct(item));
//                System.out.println(product);

        return;
    }

    private Product getProduct(Datum item) throws IOException {

        String imageUrl = service.uploadImageToIK(item.getImageUrl(), item.getProgramName() + "-" + item.getName()
                .replace("?", "")
                .replace("=", "")
                .replace(" ", "-")
                .replace("'", "")
                .replace("!", "")
                .replace("@", "")
                .replace(".png", "")
                .replace("_", "")
                .replace(".jpg", "")
                .replace("\\", "")
                .replace(",", "")
                .replace("$", "")
                .replace("#", "")
                .replace("^", "")
                .replace("*", "")
                .replace("&", "")
                .replace(")", "")
                .replace("(", "")
                .replace("/", "")
                .replace("~", "")
                .replace("`", "")
        );
//        if (imageUrl.equalsIgnoreCase(item.getImageUrl())) return null;

        Product product = new Product(item.getProgramName(), item.getName());

        Store store = new Store(item.getProgramName());
        storeService.create(store);

        product.setStore_id(store.getId());

        product.available = item.getInStock() == null ? false : item.getInStock().equalsIgnoreCase("Yes");
        product.setProduct_name(item.getName());
        product.setPayment_url(item.getBuyUrl());
        product.setProduct_details(item.getDescriptionLong());
        product.setCurrency(item.getCurrency());
        product.setPrice(Double.valueOf(item.getPrice()));
        Set<String> images = new LinkedHashSet<>();

        images.add(imageUrl);
        product.setImage_urls(images);
        product.setSKU(item.getSku());
        product.setShipping_price(item.getPriceShipping());
        Set<UUID> category_id = new LinkedHashSet<>();
//                System.err.println(item.getCategoryProgram());
        String[] cat1 = item.getCategoryNetwork().equalsIgnoreCase("") ?
                item.getCategoryProgram().split(">") : item.getCategoryNetwork().split(">");
        Category c;
        c = new Category("Category", UUID.nameUUIDFromBytes(("Category").getBytes()));
        categoryService.create(c);
        for (int j = 0; j < cat1.length; j++) {
            category_id.add(UUID.nameUUIDFromBytes((cat1[j]).getBytes()));
            if (j == 0) {
                //root must be added to category
                c = new Category(cat1[j], UUID.nameUUIDFromBytes(("Category").getBytes()));
                categoryService.create(c);

            } else {
                if (!cat1[j].isEmpty()) {
                    c = new Category(cat1[j], UUID.nameUUIDFromBytes((cat1[j - 1]).getBytes()));
                    categoryService.create(c);
                }
            }
        }
        product.setCategory_id(category_id);
        product.setBrand_name(item.getManufacturer() == null || item.getManufacturer().isEmpty() ||
                item.getManufacturer().equalsIgnoreCase("") ? "" : item.getManufacturer());
        return product;
    }

    @PostMapping("/crawlandupdate")
    public void crawlContent() {
        service.getAll().stream().forEach(product -> {
//            try {
            try {
                service.crawlContent(product);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        });

    }

    @PostMapping("/productlist")
    public ResponseEntity<List<Product>> getProductsByID(@RequestBody List<String> ids) {

        List<Product> list = new ArrayList<>();

        for (String id : ids) {
            list.add(service.get(UUID.fromString(id)).get());
        }
        return ResponseEntity.ok(list);

    }
    @PostMapping("/productlist")
    public ResponseEntity<List<Product>> getByID(@RequestBody List<String> ids) {

        List<Product> list = new ArrayList<>();

        for (String id : ids) {
            list.add(service.get(UUID.fromString(id)).get());
        }
        return ResponseEntity.ok(list);

    }
}
