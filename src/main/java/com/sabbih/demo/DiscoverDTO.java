package com.sabbih.demo;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class DiscoverDTO {
    UUID id;
    String thumbnail;
    String short_desc;
    Products products;
}
