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
    @NotNull(message = "product_details  is required")
    Set<Double> price;

    //    String vendorProductCode;
    @NotNull(message = "product_details  is required")
    String brand_name;
    @NotNull(message = "shipping_price  is required") String shipping_price;
    @NotNull(message = "currency  is required") String currency;
    @NotNull(message = "product_details  is required") String program_name;
    UUID store_id;
    @NotNull(message = "product_details  is required") Set<UUID> category_id;
    @NotNull(message = "category is required") Set<String> category_name;
    UUID color_id;
    @NotNull(message = "product_details  is required") String SKU;
    boolean available = true;
    @NotNull(message = "product_details  is required") String program_icon_url
            = "https://d2gjrq7hs8he14.cloudfront.net/webpack4/logo@2x-8d56700bf4acf5930388f3bea97c0260.png";
    String color;

    /* use empty constructor*/
    public Product() {
    }

    /* use empty constructor*/
    public Product(String program_name, String product_name) {
        this.id = UUID.nameUUIDFromBytes((product_name + program_name).getBytes());
        this.program_name
                = program_name;
//        this.SKU = SKU;
    }

    public Product(String payment_url, String product_name, Set<String> image_urls, String product_details, Double price, String brand_name, Set<UUID> category_id, UUID store_id) {
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

    public void setPrice(Double price) {
        if (this.price == null) {
            this.price = new LinkedHashSet<>();
        }
        this.price.add(price);
    }

    public Set<Double> getPrice() {
        return price;
    }


}
