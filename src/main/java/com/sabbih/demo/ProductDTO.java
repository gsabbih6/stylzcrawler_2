package com.sabbih.demo;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

    String totalHits;
    List<Product> products;
}
