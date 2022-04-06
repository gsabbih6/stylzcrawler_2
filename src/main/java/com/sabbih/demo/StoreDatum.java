package com.sabbih.demo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.annotation.processing.Generated;
import java.util.List;
@Data
@Generated("jsonschema2pojo")
public class StoreDatum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("prohibited_states")
    @Expose
    private String prohibitedStates;
    @SerializedName("mobile_tracking")
    @Expose
    private String mobileTracking;
//    @SerializedName("category")
//    @Expose
//    private List<Category> category = null;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state_code")
    @Expose
    private String stateCode;
    @SerializedName("zip_code")
    @Expose
    private String zipCode;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("contact_name")
    @Expose
    private String contactName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("join_date")
    @Expose
    private String joinDate;
    @SerializedName("cookie_duration")
    @Expose
    private String cookieDuration;
    @SerializedName("percentage_payout")
    @Expose
    private String percentagePayout;
    @SerializedName("flat_payout")
    @Expose
    private String flatPayout;
    @SerializedName("deep_linking")
    @Expose
    private String deepLinking;
    @SerializedName("product_feed")
    @Expose
    private String productFeed;
}
