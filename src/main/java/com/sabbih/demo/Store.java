package com.sabbih.demo;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table
public class Store {

    @PrimaryKey
    UUID id;
    String name;
    String url;
    String logoUrl;
    String country;
    boolean active = true;
    boolean subscriber = false;
    String referralCode;

    public Store(String name, String url, String logoUrl, String country) {
        this.name = name;
        this.url = url;
        this.id = UUID.nameUUIDFromBytes((name + url).getBytes());//randomUUID();//fromString(name + url);
        this.logoUrl = logoUrl;
        this.country = country;
    }
}
