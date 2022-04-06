package com.sabbih.demo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Data
@Table
public class Store {

    @PrimaryKey
    UUID id;
    private String name;
    private String description;
    private String logo;
    private String prohibitedStates;
    private String mobileTracking;
//    private List<Category> category = null;
    private String address1;
    private String address2;
    private String city;
    private String stateCode;
    private String zipCode;
    private String countryCode;
    private String phone;
    private String website;
    private String contactName;
    private String email;
    private String currency;
    private String status;
    private String joinDate;
    private String cookieDuration;
    private String percentagePayout;
    private String flatPayout;
    private String deepLinking;
    private String productFeed;

    public Store(String name) {
        this.name = name;
//        this.url = url;
        this.id = UUID.nameUUIDFromBytes((name).getBytes());//randomUUID();//fromString(name + url);
//        this.logoUrl = logoUrl;
//        this.country = country;
    }
}
