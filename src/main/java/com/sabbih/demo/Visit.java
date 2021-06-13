package com.sabbih.demo;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.util.UUID;

public class Visit {
    @PrimaryKey
    UUID id;
    //    String url;
    UUID product_id;

    public Visit(UUID product_id) {
        this.id = UUID.nameUUIDFromBytes(("visit" + product_id).getBytes());
        this.product_id = product_id;
    }
}
