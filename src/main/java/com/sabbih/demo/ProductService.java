package com.sabbih.demo;

import com.google.gson.Gson;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import io.imagekit.sdk.utils.Utils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.*;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RestHighLevelClient elastic;

    @Autowired
    ImageKit imagekit;

    public Product create(Product product) throws IOException {
        if (product.getId() != null)
        {  IndexRequest request = new IndexRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME);
        request.id(product.getId().toString());
        request.source(new Gson().toJson(product), XContentType.JSON);
        elastic.index(request, RequestOptions.DEFAULT);
        return productRepository.save(product);}
        return new Product();
    }

//    @Scheduled(fixedRate = 15000)
//    public void testing(){
//        System.out.println("scedule works");
//    }

    public String uploadImageToIK(String imageUrl, String name) throws SocketTimeoutException {
        try {
            BufferedImage image = ImageIO.read(new URL(imageUrl));

            String[] spilt = imageUrl.split("/");
//        spilt = spilt[spilt.length - 1].split("/?");
            String name1 = spilt[spilt.length - 1].replace("?", "")
                    .replace("=", "")
                    .replace("!", "")
                    .replace("@", "")
                    .replace(".png", "")
                    .replace("_", "")
                    .replace("-", "")
                    .replace(".jpg", "");

            String tempDir = System.getProperty("java.io.tmpdir");
            File outputfile = new File(tempDir + name + ".jpg");
//            File outputfile = File.createTempFile(name, ".jpg");
            ImageIO.write(image, "jpg", outputfile);

//            ImageOutputStream outputfile = ImageIO.createImageOutputStream(new
//                    FileOutputStream(name + ".jpg"));
//            if (outputfile == null || !ImageIO.write(image, "JPEG", outputfile)) {
//                System.err.println("Failed to write image " + name + ".jpg");
//                System.exit(1);
//            }
            String base64 = Utils.fileToBase64(outputfile);
            FileCreateRequest fileCreateRequest = new FileCreateRequest(base64, name + ".jpg");
            fileCreateRequest.setUseUniqueFileName(false);
            Result result = imagekit.upload(fileCreateRequest);
            if (result == null) {
                System.out.println("Upload failes");
                return imageUrl;
            }
            if (result.isSuccessful()) return result.getUrl();
//        result = imagekit.upload(fileCreateRequest);
//        if (result.isSuccessful()) return result.getUrl();
//        result = imagekit.upload(fileCreateRequest);
//        if (result.isSuccessful()) return result.getUrl();
        } catch (SocketTimeoutException | MalformedURLException e) {
            //Log this exception
            return imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageUrl;
    }

    public Product update(Product product) throws IOException {
        System.out.println(product);

        UpdateRequest request = new UpdateRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME, product.getId().toString());
        GetRequest getRequest = new GetRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME, product.getId().toString());
//        getRequest.fetchSourceContext(new FetchSourceContext(false));
//        getRequest.storedFields("_none_");
        GetResponse res = elastic.get(getRequest, RequestOptions.DEFAULT);
        Gson gson = new Gson();
        Product p = gson.fromJson(res.getSourceAsString(), Product.class);
//        System.out.println("Old price: " + product.getId().toString());
//        Double s = p.getPrice().toArray(new Double[1])[0];
        if (!res.isExists()) {
            return create(product);
        } else if (!p.getPrice().contains(product.getPrice().toArray(new Double[1])[0])) {

            Set<Double> prices = p.getPrice();
            product.getPrice().stream().forEach((price) -> {
                prices.add(price);
            });
            p.price = prices;

        }

        request.doc(new Gson().toJson(p), XContentType.JSON);
        request.docAsUpsert(true);
        elastic.update(request, RequestOptions.DEFAULT);


        return productRepository.save(product);
    }

    public Long getTotalItems() throws IOException {
        CountRequest countRequest = new CountRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.termQuery("_type","product"));
        countRequest.query(QueryBuilders.matchAllQuery());
        CountResponse response = elastic.count(countRequest, RequestOptions.DEFAULT);
        return response.getCount();
    }


    public Products getAll(String page) throws IOException {
//        createCassandraPageRequest(48,)
        int size = 40;
        int from = page == "0" || page == "1" ? 0 : Integer.parseInt(page) * size;
        SearchRequest searchRequest = new SearchRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME);
        CountRequest countRequest = new CountRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        AggregationBuilder agg = AggregationBuilders.count().
//                searchSourceBuilder.aggregation(agg);
        MatchAllQueryBuilder qb = QueryBuilders
                .matchAllQuery().boost(1.2f);
//        MultiMatchQueryBuilder qb = QueryBuilders
//                .multiMatchQuery("Hat", "product_details").boost(1.2f);
        searchSourceBuilder.query(QueryBuilders
                .functionScoreQuery(qb, ScoreFunctionBuilders.randomFunction().seed(10000))
                .boostMode(CombineFunction.REPLACE));
//        searchSourceBuilder.query();

        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);

        searchRequest.source(searchSourceBuilder);
        countRequest.query(qb);///source(searchSourceBuilder);
        SearchResponse response = elastic.search(searchRequest, RequestOptions.DEFAULT);
        CountResponse countResponse = elastic.count(countRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();


        List<Product> products = new ArrayList<>();
        for (SearchHit hit : hits.getHits()) {
//            System.out.println(hit.getSourceAsString());
            Gson gson = new Gson();
            Product p = gson.fromJson(hit.getSourceAsString(), Product.class);
            products.add(p);
        }
//        ProductDTO productDTO = new ProductDTO();
////        productDTO.setProducts(products);
//        productDTO.setTotalHits(String.valueOf(hits.getTotalHits().value));
        Products products1 = new Products();
        products1.setProducts(products);
        products1.setCount(countResponse.getCount());
        return products1;
//        return productRepository.findAll();
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> get(UUID uuid) {
        return productRepository.findById(uuid);
    }

    public void delete(UUID uuid) throws IOException {
        DeleteRequest request = new DeleteRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME, uuid.toString());
        elastic.delete(request, RequestOptions.DEFAULT);
        productRepository.deleteById(uuid);
    }
//    private CassandraPageRequest createCassandraPageRequest(final Integer limit, final String pagingState) {
//        final PageRequest pageRequest = PageRequest.of(0, limit);
//        final PagingState pageState = pagingState != null ? PagingState.fromString(pagingState) : null;
//        return CassandraPageRequest.of(pageRequest, pageState);
//    }

    public void crawlContent(Product p) throws IOException {

        switch (p.program_name.toLowerCase()) {
            case StylConstants.COMPANY_INGRID:
                processIngrid(p);
                break;
            case StylConstants.COMPANY_BERRYLOOK:
                processBerrylook(p);
                break;
            case StylConstants.COMPANY_AMERICANHATMAKERS:
                processAmericanHaMakers(p);
                break;
            case StylConstants.COMPANY_EVERYMAN:
                processEveryman(p);
                break;
            default:
//                System.out.println(p.program_name.toLowerCase());
                break;
        }

    }

    private void processEveryman(Product p) throws IOException {
        Document doc = null;
        try {
            doc = Jsoup.connect(p.getPayment_url()).get();

        } catch (IOException e) {
//            e.printStackTrace();
            productRepository.deleteById(p.getId());
//            System.out.println("Deleted Product: " + p.payment_url);
            return;
        }

//        System.out.println(doc.html());
//        Set<String> imageurls=new LinkedHashSet<>();
        Element slideshow = doc.getElementsByClass("js-thumbnail-carousel").first();
//        Elements achors = slideshow.getElementsByTag("img");
//        System.out.println(slideshow.getElementsByClass("pr").first().attr("data-mid"));
        // extract images
        Set<String> imageUrls = new HashSet<>();
        slideshow.getElementsByTag("img").stream().forEach((e) -> {
            imageUrls.add(e.attr("src"));
        });
        p.setImage_urls(imageUrls);
//        System.out.println(imageUrls);
        // Extract title
        String title = doc.getElementsByClass("desktop-title-container").first().text();
        p.setProduct_name(title);
//        System.out.println(title);
        // Extract details for gender
//        String title = doc.getElementsByClass("product-title").first().text();
//        p.setProduct_name(title);
//        System.out.println(title);

        update(p);
    }

    private void processAmericanHaMakers(Product p) throws IOException {
        Document doc = null;
        try {
            doc = Jsoup.connect(p.getPayment_url()).get();

        } catch (IOException e) {
//            e.printStackTrace();
            productRepository.deleteById(p.getId());
//            System.out.println("Deleted Product: " + p.payment_url);
            return;
        }

//        System.out.println(doc.html());
//        Set<String> imageurls=new LinkedHashSet<>();
        Elements slideshow = doc.getElementsByClass("list-image-for-slider");
//        Elements achors = slideshow.getElementsByTag("img");
//        System.out.println(slideshow.getElementsByClass("pr").first().attr("data-mid"));
        // extract images
        Set<String> imageUrls = new HashSet<>();
        slideshow.stream().forEach((e) -> {
            imageUrls.add(e.attr("src"));
        });
        p.setImage_urls(imageUrls);
//        System.out.println(imageUrls);
        // Extract title
        String title = doc.getElementsByClass("desktop-title-container").first().text();
        p.setProduct_name(title);
//        System.out.println(title);
        // Extract details for gender
//        String title = doc.getElementsByClass("product-title").first().text();
//        p.setProduct_name(title);
//        System.out.println(title);

        update(p);

    }

    private void processBerrylook(Product p) throws IOException {
        // extract

        Document doc = null;
        try {
            doc = Jsoup.connect(p.getPayment_url()).get();

        } catch (IOException e) {
//            e.printStackTrace();
            productRepository.deleteById(p.getId());
//            System.out.println("Deleted Product: " + p.payment_url);
            return;
        }

//        System.out.println(doc.html());
//        Set<String> imageurls=new LinkedHashSet<>();
        Element slideshow = doc.getElementsByClass("small-img").first();
//        Elements achors = slideshow.getElementsByTag("img");
//        System.out.println(slideshow.getElementsByClass("pr").first().attr("data-mid"));
        // extract images
        Set<String> imageUrls = new HashSet<>();
        slideshow.getElementsByClass("pr").stream().forEach((e) -> {
            imageUrls.add(e.attr("data-mid"));
        });
        p.setImage_urls(imageUrls);

        // Extract title
        String title = doc.getElementsByClass("product-title").first().text();
        p.setProduct_name(title);
//        System.out.println(title);
        // Extract details for gender
//        String title = doc.getElementsByClass("product-title").first().text();
//        p.setProduct_name(title);
//        System.out.println(title);

        update(p);
    }

    private void processIngrid(Product p) throws IOException {
        // extract

        Document doc = null;
        try {
            doc = Jsoup.connect(p.getPayment_url()).get();
        } catch (IOException e) {
//            e.printStackTrace();
            productRepository.deleteById(p.getId());
//            System.out.println("Deleted Product: " + p.payment_url);
            return;
        }


//        Set<String> imageurls=new LinkedHashSet<>();
        Elements slideshow = doc.getElementsByClass("product__thumbnail");
//        Elements achors = slideshow.getElementsByTag("img");

        // extract images
        Set<String> imageUrls = new HashSet<>();
        slideshow.stream().forEach((e) -> {
            imageUrls.add(e.getElementsByTag("img").attr("data-src"));
//            System.out.println(e.text());
        });
        p.setImage_urls(imageUrls);

        // Extract title
        String title = doc.getElementsByClass("product-title").first().text();
        p.setProduct_name(title);
        // Extract color
//        String color=doc.getElementsByClass("product-title").first().text();
//        p.setProduct_name(title);
//        System.out.println(imageUrls);

        update(p);


    }


}
