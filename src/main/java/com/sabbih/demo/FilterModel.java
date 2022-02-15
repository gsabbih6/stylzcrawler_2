package com.sabbih.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "genders",
        "category",
        "store",
        "min_price",
        "max_price"
})
@Generated("jsonschema2pojo")
public class FilterModel {

    @JsonProperty("genders")
    private List<Gender> genders = null;
    @JsonProperty("category")
    private List<CategoryDTO> category = null;
    @JsonProperty("store")
    private List<StoreDTO> store = null;
    @JsonProperty("min_price")
    private String minPrice;
    @JsonProperty("max_price")
    private String maxPrice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("genders")
    public List<Gender> getGenders() {
        return genders;
    }

    @JsonProperty("genders")
    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }

    @JsonProperty("category")
    public List<CategoryDTO> getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(List<CategoryDTO> category) {
        this.category = category;
    }

    @JsonProperty("store")
    public List<StoreDTO> getStore() {
        return store;
    }

    @JsonProperty("store")
    public void setStore(List<StoreDTO> store) {
        this.store = store;
    }

    @JsonProperty("min_price")
    public String getMinPrice() {
        return minPrice;
    }

    @JsonProperty("min_price")
    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    @JsonProperty("max_price")
    public String getMaxPrice() {
        return maxPrice;
    }

    @JsonProperty("max_price")
    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
