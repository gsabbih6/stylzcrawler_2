
package com.sabbih.demo;

import java.util.HashMap;
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
    "program_id",
    "program_name",
    "currency",
    "age_range",
    "artist",
    "aspect_ratio",
    "author",
    "battery_life",
    "binding",
    "buy_url",
    "color",
    "color_output",
    "condition",
    "description_long",
    "director",
    "display_type",
    "edition",
    "expiration_date",
    "features",
    "focus_type",
    "format",
    "functions",
    "genre",
    "heel_height",
    "height",
    "image_thumb_url",
    "image_url",
    "installation",
    "isbn",
    "length",
    "load_type",
    "location",
    "made_in",
    "manufacturer",
    "material",
    "megapixels",
    "memory_type",
    "memory_capacity",
    "memory_card_slot",
    "model_number",
    "mpn",
    "name",
    "occasion",
    "operating_system",
    "optical_drive",
    "price_retail",
    "pages",
    "payment_accepted",
    "payment_notes",
    "platform",
    "price_sale",
    "processor",
    "publisher",
    "quantity_in_stock",
    "rating",
    "recommended_usage",
    "resolution",
    "shoe_size",
    "screen_size",
    "shipping_method",
    "price_shipping",
    "shoe_width",
    "size",
    "sku",
    "staring",
    "style",
    "tracks",
    "upc",
    "weight",
    "width",
    "wireless_interface",
    "year",
    "zoom",
    "category_network",
    "category_program",
    "description_short",
    "discontinued",
    "in_stock",
    "tech_spec_url",
    "keywords",
    "price"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("program_id")
    private String programId;
    @JsonProperty("program_name")
    private String programName;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("age_range")
    private String ageRange;
    @JsonProperty("artist")
    private String artist;
    @JsonProperty("aspect_ratio")
    private String aspectRatio;
    @JsonProperty("author")
    private String author;
    @JsonProperty("battery_life")
    private String batteryLife;
    @JsonProperty("binding")
    private String binding;
    @JsonProperty("buy_url")
    private String buyUrl;
    @JsonProperty("color")
    private String color;
    @JsonProperty("color_output")
    private String colorOutput;
    @JsonProperty("condition")
    private String condition;
    @JsonProperty("description_long")
    private String descriptionLong;
    @JsonProperty("director")
    private String director;
    @JsonProperty("display_type")
    private String displayType;
    @JsonProperty("edition")
    private String edition;
    @JsonProperty("expiration_date")
    private String expirationDate;
    @JsonProperty("features")
    private String features;
    @JsonProperty("focus_type")
    private String focusType;
    @JsonProperty("format")
    private String format;
    @JsonProperty("functions")
    private String functions;
    @JsonProperty("genre")
    private String genre;
    @JsonProperty("heel_height")
    private String heelHeight;
    @JsonProperty("height")
    private String height;
    @JsonProperty("image_thumb_url")
    private String imageThumbUrl;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("installation")
    private String installation;
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("length")
    private String length;
    @JsonProperty("load_type")
    private String loadType;
    @JsonProperty("location")
    private String location;
    @JsonProperty("made_in")
    private String madeIn;
    @JsonProperty("manufacturer")
    private String manufacturer;
    @JsonProperty("material")
    private String material;
    @JsonProperty("megapixels")
    private String megapixels;
    @JsonProperty("memory_type")
    private String memoryType;
    @JsonProperty("memory_capacity")
    private String memoryCapacity;
    @JsonProperty("memory_card_slot")
    private String memoryCardSlot;
    @JsonProperty("model_number")
    private String modelNumber;
    @JsonProperty("mpn")
    private String mpn;
    @JsonProperty("name")
    private String name;
    @JsonProperty("occasion")
    private String occasion;
    @JsonProperty("operating_system")
    private String operatingSystem;
    @JsonProperty("optical_drive")
    private String opticalDrive;
    @JsonProperty("price_retail")
    private String priceRetail;
    @JsonProperty("pages")
    private String pages;
    @JsonProperty("payment_accepted")
    private String paymentAccepted;
    @JsonProperty("payment_notes")
    private String paymentNotes;
    @JsonProperty("platform")
    private String platform;
    @JsonProperty("price_sale")
    private String priceSale;
    @JsonProperty("processor")
    private String processor;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("quantity_in_stock")
    private String quantityInStock;
    @JsonProperty("rating")
    private String rating;
    @JsonProperty("recommended_usage")
    private String recommendedUsage;
    @JsonProperty("resolution")
    private String resolution;
    @JsonProperty("shoe_size")
    private String shoeSize;
    @JsonProperty("screen_size")
    private String screenSize;
    @JsonProperty("shipping_method")
    private String shippingMethod;
    @JsonProperty("price_shipping")
    private String priceShipping;
    @JsonProperty("shoe_width")
    private String shoeWidth;
    @JsonProperty("size")
    private String size;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("staring")
    private String staring;
    @JsonProperty("style")
    private String style;
    @JsonProperty("tracks")
    private String tracks;
    @JsonProperty("upc")
    private String upc;
    @JsonProperty("weight")
    private String weight;
    @JsonProperty("width")
    private String width;
    @JsonProperty("wireless_interface")
    private String wirelessInterface;
    @JsonProperty("year")
    private String year;
    @JsonProperty("zoom")
    private String zoom;
    @JsonProperty("category_network")
    private String categoryNetwork;
    @JsonProperty("category_program")
    private String categoryProgram;
    @JsonProperty("description_short")
    private String descriptionShort;
    @JsonProperty("discontinued")
    private String discontinued;
    @JsonProperty("in_stock")
    private String inStock;
    @JsonProperty("tech_spec_url")
    private String techSpecUrl;
    @JsonProperty("keywords")
    private String keywords;
    @JsonProperty("price")
    private String price;
    @JsonIgnore
    private Map<String, String> additionalProperties = new HashMap<String, String>();

    @JsonProperty("program_id")
    public String getProgramId() {
        return programId;
    }

    @JsonProperty("program_id")
    public void setProgramId(String programId) {
        this.programId = programId;
    }

    @JsonProperty("program_name")
    public String getProgramName() {
        return programName;
    }

    @JsonProperty("program_name")
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("age_range")
    public String getAgeRange() {
        return ageRange;
    }

    @JsonProperty("age_range")
    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    @JsonProperty("artist")
    public String getArtist() {
        return artist;
    }

    @JsonProperty("artist")
    public void setArtist(String artist) {
        this.artist = artist;
    }

    @JsonProperty("aspect_ratio")
    public String getAspectRatio() {
        return aspectRatio;
    }

    @JsonProperty("aspect_ratio")
    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("battery_life")
    public String getBatteryLife() {
        return batteryLife;
    }

    @JsonProperty("battery_life")
    public void setBatteryLife(String batteryLife) {
        this.batteryLife = batteryLife;
    }

    @JsonProperty("binding")
    public String getBinding() {
        return binding;
    }

    @JsonProperty("binding")
    public void setBinding(String binding) {
        this.binding = binding;
    }

    @JsonProperty("buy_url")
    public String getBuyUrl() {
        return buyUrl;
    }

    @JsonProperty("buy_url")
    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
    }

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(String color) {
        this.color = color;
    }

    @JsonProperty("color_output")
    public String getColorOutput() {
        return colorOutput;
    }

    @JsonProperty("color_output")
    public void setColorOutput(String colorOutput) {
        this.colorOutput = colorOutput;
    }

    @JsonProperty("condition")
    public String getCondition() {
        return condition;
    }

    @JsonProperty("condition")
    public void setCondition(String condition) {
        this.condition = condition;
    }

    @JsonProperty("description_long")
    public String getDescriptionLong() {
        return descriptionLong;
    }

    @JsonProperty("description_long")
    public void setDescriptionLong(String descriptionLong) {
        this.descriptionLong = descriptionLong;
    }

    @JsonProperty("director")
    public String getDirector() {
        return director;
    }

    @JsonProperty("director")
    public void setDirector(String director) {
        this.director = director;
    }

    @JsonProperty("display_type")
    public String getDisplayType() {
        return displayType;
    }

    @JsonProperty("display_type")
    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    @JsonProperty("edition")
    public String getEdition() {
        return edition;
    }

    @JsonProperty("edition")
    public void setEdition(String edition) {
        this.edition = edition;
    }

    @JsonProperty("expiration_date")
    public String getExpirationDate() {
        return expirationDate;
    }

    @JsonProperty("expiration_date")
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @JsonProperty("features")
    public String getFeatures() {
        return features;
    }

    @JsonProperty("features")
    public void setFeatures(String features) {
        this.features = features;
    }

    @JsonProperty("focus_type")
    public String getFocusType() {
        return focusType;
    }

    @JsonProperty("focus_type")
    public void setFocusType(String focusType) {
        this.focusType = focusType;
    }

    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    @JsonProperty("format")
    public void setFormat(String format) {
        this.format = format;
    }

    @JsonProperty("functions")
    public String getFunctions() {
        return functions;
    }

    @JsonProperty("functions")
    public void setFunctions(String functions) {
        this.functions = functions;
    }

    @JsonProperty("genre")
    public String getGenre() {
        return genre;
    }

    @JsonProperty("genre")
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @JsonProperty("heel_height")
    public String getHeelHeight() {
        return heelHeight;
    }

    @JsonProperty("heel_height")
    public void setHeelHeight(String heelHeight) {
        this.heelHeight = heelHeight;
    }

    @JsonProperty("height")
    public String getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(String height) {
        this.height = height;
    }

    @JsonProperty("image_thumb_url")
    public String getImageThumbUrl() {
        return imageThumbUrl;
    }

    @JsonProperty("image_thumb_url")
    public void setImageThumbUrl(String imageThumbUrl) {
        this.imageThumbUrl = imageThumbUrl;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("installation")
    public String getInstallation() {
        return installation;
    }

    @JsonProperty("installation")
    public void setInstallation(String installation) {
        this.installation = installation;
    }

    @JsonProperty("isbn")
    public String getIsbn() {
        return isbn;
    }

    @JsonProperty("isbn")
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @JsonProperty("length")
    public String getLength() {
        return length;
    }

    @JsonProperty("length")
    public void setLength(String length) {
        this.length = length;
    }

    @JsonProperty("load_type")
    public String getLoadType() {
        return loadType;
    }

    @JsonProperty("load_type")
    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("made_in")
    public String getMadeIn() {
        return madeIn;
    }

    @JsonProperty("made_in")
    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    @JsonProperty("manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    @JsonProperty("manufacturer")
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @JsonProperty("material")
    public String getMaterial() {
        return material;
    }

    @JsonProperty("material")
    public void setMaterial(String material) {
        this.material = material;
    }

    @JsonProperty("megapixels")
    public String getMegapixels() {
        return megapixels;
    }

    @JsonProperty("megapixels")
    public void setMegapixels(String megapixels) {
        this.megapixels = megapixels;
    }

    @JsonProperty("memory_type")
    public String getMemoryType() {
        return memoryType;
    }

    @JsonProperty("memory_type")
    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    @JsonProperty("memory_capacity")
    public String getMemoryCapacity() {
        return memoryCapacity;
    }

    @JsonProperty("memory_capacity")
    public void setMemoryCapacity(String memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    @JsonProperty("memory_card_slot")
    public String getMemoryCardSlot() {
        return memoryCardSlot;
    }

    @JsonProperty("memory_card_slot")
    public void setMemoryCardSlot(String memoryCardSlot) {
        this.memoryCardSlot = memoryCardSlot;
    }

    @JsonProperty("model_number")
    public String getModelNumber() {
        return modelNumber;
    }

    @JsonProperty("model_number")
    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    @JsonProperty("mpn")
    public String getMpn() {
        return mpn;
    }

    @JsonProperty("mpn")
    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("occasion")
    public String getOccasion() {
        return occasion;
    }

    @JsonProperty("occasion")
    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    @JsonProperty("operating_system")
    public String getOperatingSystem() {
        return operatingSystem;
    }

    @JsonProperty("operating_system")
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @JsonProperty("optical_drive")
    public String getOpticalDrive() {
        return opticalDrive;
    }

    @JsonProperty("optical_drive")
    public void setOpticalDrive(String opticalDrive) {
        this.opticalDrive = opticalDrive;
    }

    @JsonProperty("price_retail")
    public String getPriceRetail() {
        return priceRetail;
    }

    @JsonProperty("price_retail")
    public void setPriceRetail(String priceRetail) {
        this.priceRetail = priceRetail;
    }

    @JsonProperty("pages")
    public String getPages() {
        return pages;
    }

    @JsonProperty("pages")
    public void setPages(String pages) {
        this.pages = pages;
    }

    @JsonProperty("payment_accepted")
    public String getPaymentAccepted() {
        return paymentAccepted;
    }

    @JsonProperty("payment_accepted")
    public void setPaymentAccepted(String paymentAccepted) {
        this.paymentAccepted = paymentAccepted;
    }

    @JsonProperty("payment_notes")
    public String getPaymentNotes() {
        return paymentNotes;
    }

    @JsonProperty("payment_notes")
    public void setPaymentNotes(String paymentNotes) {
        this.paymentNotes = paymentNotes;
    }

    @JsonProperty("platform")
    public String getPlatform() {
        return platform;
    }

    @JsonProperty("platform")
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @JsonProperty("price_sale")
    public String getPriceSale() {
        return priceSale;
    }

    @JsonProperty("price_sale")
    public void setPriceSale(String priceSale) {
        this.priceSale = priceSale;
    }

    @JsonProperty("processor")
    public String getProcessor() {
        return processor;
    }

    @JsonProperty("processor")
    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @JsonProperty("publisher")
    public String getPublisher() {
        return publisher;
    }

    @JsonProperty("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @JsonProperty("quantity_in_stock")
    public String getQuantityInStock() {
        return quantityInStock;
    }

    @JsonProperty("quantity_in_stock")
    public void setQuantityInStock(String quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @JsonProperty("rating")
    public String getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(String rating) {
        this.rating = rating;
    }

    @JsonProperty("recommended_usage")
    public String getRecommendedUsage() {
        return recommendedUsage;
    }

    @JsonProperty("recommended_usage")
    public void setRecommendedUsage(String recommendedUsage) {
        this.recommendedUsage = recommendedUsage;
    }

    @JsonProperty("resolution")
    public String getResolution() {
        return resolution;
    }

    @JsonProperty("resolution")
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @JsonProperty("shoe_size")
    public String getShoeSize() {
        return shoeSize;
    }

    @JsonProperty("shoe_size")
    public void setShoeSize(String shoeSize) {
        this.shoeSize = shoeSize;
    }

    @JsonProperty("screen_size")
    public String getScreenSize() {
        return screenSize;
    }

    @JsonProperty("screen_size")
    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    @JsonProperty("shipping_method")
    public String getShippingMethod() {
        return shippingMethod;
    }

    @JsonProperty("shipping_method")
    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    @JsonProperty("price_shipping")
    public String getPriceShipping() {
        return priceShipping;
    }

    @JsonProperty("price_shipping")
    public void setPriceShipping(String priceShipping) {
        this.priceShipping = priceShipping;
    }

    @JsonProperty("shoe_width")
    public String getShoeWidth() {
        return shoeWidth;
    }

    @JsonProperty("shoe_width")
    public void setShoeWidth(String shoeWidth) {
        this.shoeWidth = shoeWidth;
    }

    @JsonProperty("size")
    public String getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(String size) {
        this.size = size;
    }

    @JsonProperty("sku")
    public String getSku() {
        return sku;
    }

    @JsonProperty("sku")
    public void setSku(String sku) {
        this.sku = sku;
    }

    @JsonProperty("staring")
    public String getStaring() {
        return staring;
    }

    @JsonProperty("staring")
    public void setStaring(String staring) {
        this.staring = staring;
    }

    @JsonProperty("style")
    public String getStyle() {
        return style;
    }

    @JsonProperty("style")
    public void setStyle(String style) {
        this.style = style;
    }

    @JsonProperty("tracks")
    public String getTracks() {
        return tracks;
    }

    @JsonProperty("tracks")
    public void setTracks(String tracks) {
        this.tracks = tracks;
    }

    @JsonProperty("upc")
    public String getUpc() {
        return upc;
    }

    @JsonProperty("upc")
    public void setUpc(String upc) {
        this.upc = upc;
    }

    @JsonProperty("weight")
    public String getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(String weight) {
        this.weight = weight;
    }

    @JsonProperty("width")
    public String getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(String width) {
        this.width = width;
    }

    @JsonProperty("wireless_interface")
    public String getWirelessInterface() {
        return wirelessInterface;
    }

    @JsonProperty("wireless_interface")
    public void setWirelessInterface(String wirelessInterface) {
        this.wirelessInterface = wirelessInterface;
    }

    @JsonProperty("year")
    public String getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(String year) {
        this.year = year;
    }

    @JsonProperty("zoom")
    public String getZoom() {
        return zoom;
    }

    @JsonProperty("zoom")
    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    @JsonProperty("category_network")
    public String getCategoryNetwork() {
        return categoryNetwork;
    }

    @JsonProperty("category_network")
    public void setCategoryNetwork(String categoryNetwork) {
        this.categoryNetwork = categoryNetwork;
    }

    @JsonProperty("category_program")
    public String getCategoryProgram() {
        return categoryProgram;
    }

    @JsonProperty("category_program")
    public void setCategoryProgram(String categoryProgram) {
        this.categoryProgram = categoryProgram;
    }

    @JsonProperty("description_short")
    public String getDescriptionShort() {
        return descriptionShort;
    }

    @JsonProperty("description_short")
    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    @JsonProperty("discontinued")
    public String getDiscontinued() {
        return discontinued;
    }

    @JsonProperty("discontinued")
    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    @JsonProperty("in_stock")
    public String getInStock() {
        return inStock;
    }

    @JsonProperty("in_stock")
    public void setInStock(String inStock) {
        this.inStock = inStock;
    }

    @JsonProperty("tech_spec_url")
    public String getTechSpecUrl() {
        return techSpecUrl;
    }

    @JsonProperty("tech_spec_url")
    public void setTechSpecUrl(String techSpecUrl) {
        this.techSpecUrl = techSpecUrl;
    }

    @JsonProperty("keywords")
    public String getKeywords() {
        return keywords;
    }

    @JsonProperty("keywords")
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Datum{" +
                "programId='" + programId + '\'' +
                ", programName='" + programName + '\'' +
                ", currency='" + currency + '\'' +
                ", ageRange='" + ageRange + '\'' +
                ", artist='" + artist + '\'' +
                ", aspectRatio='" + aspectRatio + '\'' +
                ", author='" + author + '\'' +
                ", batteryLife='" + batteryLife + '\'' +
                ", binding='" + binding + '\'' +
                ", buyUrl='" + buyUrl + '\'' +
                ", color='" + color + '\'' +
                ", colorOutput='" + colorOutput + '\'' +
                ", condition='" + condition + '\'' +
                ", descriptionLong='" + descriptionLong + '\'' +
                ", director='" + director + '\'' +
                ", displayType='" + displayType + '\'' +
                ", edition='" + edition + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", features='" + features + '\'' +
                ", focusType='" + focusType + '\'' +
                ", format='" + format + '\'' +
                ", functions='" + functions + '\'' +
                ", genre='" + genre + '\'' +
                ", heelHeight='" + heelHeight + '\'' +
                ", height='" + height + '\'' +
                ", imageThumbUrl='" + imageThumbUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", installation='" + installation + '\'' +
                ", isbn='" + isbn + '\'' +
                ", length='" + length + '\'' +
                ", loadType='" + loadType + '\'' +
                ", location='" + location + '\'' +
                ", madeIn='" + madeIn + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", material='" + material + '\'' +
                ", megapixels='" + megapixels + '\'' +
                ", memoryType='" + memoryType + '\'' +
                ", memoryCapacity='" + memoryCapacity + '\'' +
                ", memoryCardSlot='" + memoryCardSlot + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                ", mpn='" + mpn + '\'' +
                ", name='" + name + '\'' +
                ", occasion='" + occasion + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", opticalDrive='" + opticalDrive + '\'' +
                ", priceRetail='" + priceRetail + '\'' +
                ", pages='" + pages + '\'' +
                ", paymentAccepted='" + paymentAccepted + '\'' +
                ", paymentNotes='" + paymentNotes + '\'' +
                ", platform='" + platform + '\'' +
                ", priceSale='" + priceSale + '\'' +
                ", processor='" + processor + '\'' +
                ", publisher='" + publisher + '\'' +
                ", quantityInStock='" + quantityInStock + '\'' +
                ", rating='" + rating + '\'' +
                ", recommendedUsage='" + recommendedUsage + '\'' +
                ", resolution='" + resolution + '\'' +
                ", shoeSize='" + shoeSize + '\'' +
                ", screenSize='" + screenSize + '\'' +
                ", shippingMethod='" + shippingMethod + '\'' +
                ", priceShipping='" + priceShipping + '\'' +
                ", shoeWidth='" + shoeWidth + '\'' +
                ", size='" + size + '\'' +
                ", sku='" + sku + '\'' +
                ", staring='" + staring + '\'' +
                ", style='" + style + '\'' +
                ", tracks='" + tracks + '\'' +
                ", upc='" + upc + '\'' +
                ", weight='" + weight + '\'' +
                ", width='" + width + '\'' +
                ", wirelessInterface='" + wirelessInterface + '\'' +
                ", year='" + year + '\'' +
                ", zoom='" + zoom + '\'' +
                ", categoryNetwork='" + categoryNetwork + '\'' +
                ", categoryProgram='" + categoryProgram + '\'' +
                ", descriptionShort='" + descriptionShort + '\'' +
                ", discontinued='" + discontinued + '\'' +
                ", inStock='" + inStock + '\'' +
                ", techSpecUrl='" + techSpecUrl + '\'' +
                ", keywords='" + keywords + '\'' +
                ", price='" + price + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
