package com.sabbih.demo;

import lombok.Data;

import java.util.List;
@Data
public class WardrobeDTO {
    private Wardrobe wardrobe;
    private List<Product> productList;
}
