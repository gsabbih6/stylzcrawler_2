package com.sabbih.demo;

//import com.sabbih.demo.CassandraConfig;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class Processor {
    //    @Autowired
    StoreService storeService = SpringContext.getBean(StoreService.class);
    //    @Autowired
    ProductService productService = SpringContext.getBean(ProductService.class);
    //    @Autowired
    CategoryService categoryService = SpringContext.getBean(CategoryService.class);
    ;
    private static final Log LOGGER = LogFactory.getLog(Processor.class);
//    private final EntityManagerFactory emf;

    public Processor() {
    }

    //
    public void process(Page page) {

        String url = page.getWebURL().getURL();
//        System.out.println("URL: " + url);
        if (page.getParseData() instanceof HtmlParseData) {

            HtmlParseData parseData = (HtmlParseData) page.getParseData();
//            processJoyNews(parseData, CountryUtils.GHANA);

            if (url.startsWith("https://www.diyanu.com/collections/men/products/")) {
                processDiyano(Enumerations.CATEGORY_MEN, url, parseData, "test_01");
            }
            if (url.startsWith("https://www.diyanu.com/collections/women/products/")) {
                processDiyano(Enumerations.CATEGORY_Women, url, parseData, "test_01");
            }

            if (url.startsWith("https://www.zerefashionhouse.com/women/")) {
                processZerefashion(Enumerations.CATEGORY_Women, url, parseData);
            }

        }


    }

    private void processZerefashion(String category, String url, HtmlParseData data) {
        if (category == null) System.out.println("service null");
        Category category1 = categoryService.create(new Category(category));
        String html = data.getHtml();
        String paymentUrl = url;

        // extract
        Document doc = Jsoup.parse(html);

        // images
        Element slideshow = doc.getElementsByClass("thumbnails thumbnails-left clearfix").first();
        Elements achors = slideshow.getElementsByTag("a");
        Set<String> imageUrls = new HashSet<>();
        achors.stream().forEach((e) -> {
            imageUrls.add(e.attr("href"));
        });


        String description = doc.getElementsByClass("tab-content").first().text();
        String price = doc.getElementsByClass("price-new").text();
        String productName = doc.getElementById("title-page").text();
        String brandname = doc.getElementsByClass("description").first().getElementsByTag("a").first().text();

        System.out.println("IMAGE: " + imageUrls);
        Set<UUID> cat = new HashSet<>();
        cat.add(category1.getId());

        Store store = storeService.create(new Store("Zerefashionhouse", "https://www.zerefashionhouse.com/",
                "https://www.zerefashionhouse.com/image/catalog/Zere_Fashion_House_Logo.png",
                Enumerations.COUNTRY_UK));
//        String paymentUrl, String productName, Set<String> imageUrls, String productDetails, String price, String brandName, Set<UUID> categories, UUID store
        Product p = productService.create(new Product(paymentUrl, productName, imageUrls, description, price, brandname, cat, store.getId()));

        LOGGER.info(p);
        System.out.println("Product saved: " + p.toString());
    }

    private void processDiyano(String category, String url, HtmlParseData data, String affiliateCode) {
        if (category == null) System.out.println("service null");
        Category category1 = categoryService.create(new Category(category));
        String html = data.getHtml();
        String paymentUrl = url;

        // extract
        Document doc = Jsoup.parse(html);
        Element slideshow = doc.getElementsByClass("Product__SlideshowNavScroller").first();
        Elements achors = slideshow.getElementsByTag("a");
        Set<String> imageUrls = new HashSet<>();
        achors.stream().forEach((e) -> {
            imageUrls.add(e.attr("href"));
        });
        String description = doc.getElementsByClass("ProductMeta__Description").text();
        String price = doc.getElementsByClass("ProductMeta__Price Price Text--subdued u-h4").text();
        String productName = doc.getElementsByClass("ProductMeta__Title Heading u-h2").text();

        System.out.println("IMAGE: " + imageUrls);
        Set<UUID> cat = new HashSet<>();
        cat.add(category1.getId());

        Store store = storeService.create(new Store("Diyanu", "https://www.diyanu.com/",
                "https://cdn.shopify.com/s/files/1/0684/2619/files/logo-color_210x@2x.png?v=1589391997",
                Enumerations.COUNTRY_USA));
//        String paymentUrl, String productName, Set<String> imageUrls, String productDetails, String price, String brandName, Set<UUID> categories, UUID store
        Product p = productService.create(new Product(paymentUrl, productName, imageUrls, description, price, "", cat, store.getId()));

        LOGGER.info(p);
        System.out.println("Product saved: " + p.toString());

    }

}
