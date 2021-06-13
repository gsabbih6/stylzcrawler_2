package com.sabbih.demo;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@Table
//@Table(name = "product",schema = "stylzkeyspace@mongo_pu")
public class Product {
    @PrimaryKey
    private UUID id;

    @NotNull(message = "Payment url is required")
    String payment_url;
    @NotNull(message = "product_name url is required")
    String product_name;

    Set<String> image_urls;
    @NotNull(message = "product_details  is required")
    String product_details;

    Set<String> price;

//    String vendorProductCode;

    String brand_name;
    UUID store_id;
    Set<UUID> category_id;
    UUID color_id;
    boolean available = true;

    /* use empty constructor*/
    public Product() {
    }

    public Product(String payment_url, String product_name, Set<String> image_urls, String product_details, String price, String brand_name, Set<UUID> category_id, UUID store_id) {
        this.payment_url = payment_url;
        this.id = UUID.nameUUIDFromBytes((payment_url + product_name).getBytes());
        this.product_name = product_name;
        this.image_urls = image_urls;
        this.product_details = product_details;
        if (this.price == null) {
            this.price = new LinkedHashSet<>();
        }
        this.price.add(price);
        this.brand_name = brand_name;
        this.category_id = category_id;
        this.store_id = store_id;
    }
}
