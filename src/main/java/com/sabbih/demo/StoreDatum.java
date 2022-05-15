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
    private String prohibited_states;
    @SerializedName("mobile_tracking")
    @Expose
    private String mobile_tracking;
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
    private String state_code;
    @SerializedName("zip_code")
    @Expose
    private String zip_code;
    @SerializedName("country_code")
    @Expose
    private String country_code;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("contact_name")
    @Expose
    private String contact_name;
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
    private String join_date;
    @SerializedName("cookie_duration")
    @Expose
    private String cookie_duration;
    @SerializedName("percentage_payout")
    @Expose
    private String percentage_payout;
    @SerializedName("flat_payout")
    @Expose
    private String flat_payout;
    @SerializedName("deep_linking")
    @Expose
    private String deep_linking;
    @SerializedName("product_feed")
    @Expose
    private String product_feed;


}
