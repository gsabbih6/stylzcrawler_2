package com.sabbih.demo;

import com.google.gson.Gson;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.SignificantTextAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.rescore.QueryRescorerBuilder;
import org.elasticsearch.search.rescore.RescorerBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("api/search")
public class Search {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private BrandService brandService;

    @GetMapping("/hotnow")
    public ResponseEntity<List<Product>> searchHotNow() throws IOException {
        SearchRequest searchRequest = new SearchRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SignificantTextAggregationBuilder significant = AggregationBuilders.significantText("trends", "product_details");
//        searchSourceBuilder.aggregation(significant);

        ;
        searchSourceBuilder.aggregation(AggregationBuilders.filter("filers", QueryBuilders.matchAllQuery()));


        RescorerBuilder rescorerBuilder = new QueryRescorerBuilder(QueryBuilders.matchAllQuery())
                .windowSize(10)
                .setQueryWeight(0.7f)
                .setRescoreQueryWeight(1.2f);

        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.addRescorer(rescorerBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        Gson gson = new Gson();
        List<Product> products = new ArrayList<>();
        for (SearchHit hit : hits.getHits()) {
//            System.out.println(hit.getSourceAsString());
            Product p = gson.fromJson(hit.getSourceAsString(), Product.class);
            products.add(p);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/trending")
    public ResponseEntity<List<Product>> searchTrending() throws IOException {
//        String url = "http://localhost:2288/test3";
        SearchRequest searchRequest = new SearchRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SignificantTextAggregationBuilder significant = AggregationBuilders.significantText("trends", "product_details");
        searchSourceBuilder.aggregation(significant);
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        Gson gson = new Gson();
        List<Product> products = new ArrayList<>();
        for (SearchHit hit : hits.getHits()) {
//            System.out.println(hit.getSourceAsString());
            Product p = gson.fromJson(hit.getSourceAsString(), Product.class);
            products.add(p);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/query")
    public ResponseEntity<Products> query(@RequestParam String query, @RequestParam String page) throws IOException {
//        String url = "http://localhost:2288/test3";
        SearchRequest searchRequest = new SearchRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME);
        CountRequest countRequest = new CountRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        SignificantTextAggregationBuilder significant = AggregationBuilders.significantText("query", "product_details");
//        significant.subAggregation(AggregationBuilders.topHits("top_hit"));
//        searchSourceBuilder.aggregation(significant);
//        searchSourceBuilder.query(QueryBuilders.matchQuery(, query));

        QueryBuilders
                .multiMatchQuery(query, "product_name", "product_details", "brand_name", "category_name").boost(0.1f);

        QueryBuilder qb = new BoolQueryBuilder()
                .should(QueryBuilders.multiMatchQuery(query, "product_name", "product_details", "brand_name", "category_name")
//                        .analyzer("atsCustomSearchAnalyzer")
                        .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
                        .operator(Operator.AND));
//                .should(QueryBuilders.multiMatchQuery(term, "ngramFirstName^3", "ngramLastName^3", "ngramLocationName^3", "ngramCompanyName^3", "_all")
//                        .analyzer("atsCustomSearchAnalyzer")
//                        .operator(Operator.AND));


        searchSourceBuilder.query(qb);
        int size = 40;
        int from = page == "0" || page == "1" ? 0 : Integer.parseInt(page) * size;
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        // Sugesstion
//        SuggestionBuilder termSuggestionBuilder =
//                SuggestBuilders.termSuggestion("product_details").text(query);
//        SuggestBuilder suggestBuilder = new SuggestBuilder();
//        suggestBuilder.addSuggestion("suggest_product_search", termSuggestionBuilder);
//        searchSourceBuilder.suggest(suggestBuilder);

        searchRequest.source(searchSourceBuilder);
        countRequest.query(qb);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        CountResponse countResponse = restHighLevelClient.count(countRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        List<Product> products = new ArrayList<>();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
            Gson gson = new Gson();
            Product p = gson.fromJson(hit.getSourceAsString(), Product.class);
            products.add(p);
        }
        Products products1 = new Products();
        products1.setProducts(products);
        products1.setCount(countResponse.getCount());
        return ResponseEntity.ok(products1);
    }

    @GetMapping("/query_recommended")
    public ResponseEntity<List<Product>> queryRecommended(@RequestParam String query, @RequestParam String page) throws IOException {
//        String url = "http://localhost:2288/test3";
        SearchRequest searchRequest = new SearchRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        SignificantTextAggregationBuilder significant = AggregationBuilders.significantText("query", "product_details");
//        significant.subAggregation(AggregationBuilders.topHits("top_hit"));
//        searchSourceBuilder.aggregation(significant);
//        searchSourceBuilder.query(QueryBuilders.matchQuery(, query));

        QueryBuilders
                .multiMatchQuery(query, "product_name", "product_details", "brand_name", "category_name").boost(0.1f);

        QueryBuilder qb = new BoolQueryBuilder()
                .should(QueryBuilders.multiMatchQuery(query, "product_name", "product_details", "brand_name", "category_name")
//                        .analyzer("atsCustomSearchAnalyzer")
                        .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
                        .operator(Operator.AND));
//                .should(QueryBuilders.multiMatchQuery(term, "ngramFirstName^3", "ngramLastName^3", "ngramLocationName^3", "ngramCompanyName^3", "_all")
//                        .analyzer("atsCustomSearchAnalyzer")
//                        .operator(Operator.AND));


        searchSourceBuilder.query(qb);
        int size = 40;
        int from = page == "0" || page == "1" ? 0 : Integer.parseInt(page) * size;
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        // Sugesstion
//        SuggestionBuilder termSuggestionBuilder =
//                SuggestBuilders.termSuggestion("product_details").text(query);
//        SuggestBuilder suggestBuilder = new SuggestBuilder();
//        suggestBuilder.addSuggestion("suggest_product_search", termSuggestionBuilder);
//        searchSourceBuilder.suggest(suggestBuilder);

        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        List<Product> products = new ArrayList<>();
        for (SearchHit hit : hits.getHits()) {
            Gson gson = new Gson();
            Product p = gson.fromJson(hit.getSourceAsString(), Product.class);
            products.add(p);
        }
        System.out.println(products);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/query_recommended_brand")
    public ResponseEntity<Products> queryRecommendedBrand() throws IOException {
        List<Brand> brands = brandService.getAll();
        Random rand = new Random();
        String query = brands.get(rand.nextInt(brands.size())).name;
        String page = String.valueOf(rand.nextInt(20));

//        String url = "http://localhost:2288/test3";
        SearchRequest searchRequest = new SearchRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SignificantTextAggregationBuilder significant = AggregationBuilders.significantText("query", "brand_name");
//        significant.subAggregation(AggregationBuilders.topHits("top_hit"));
        searchSourceBuilder.aggregation(significant);
//        searchSourceBuilder.query(QueryBuilders.matchQuery(, query));

//        QueryBuilders
//                .multiMatchQuery(query, "brand_name").boost(0.1f);

        QueryBuilder qb = new BoolQueryBuilder()
                .should(QueryBuilders.multiMatchQuery(query, "brand_name")
//                        .analyzer("atsCustomSearchAnalyzer")
                                .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
                );
//                        .operator(Operator.AND));
//                .should(QueryBuilders.multiMatchQuery(term, "ngramFirstName^3", "ngramLastName^3", "ngramLocationName^3", "ngramCompanyName^3", "_all")
//                        .analyzer("atsCustomSearchAnalyzer")
//                        .operator(Operator.AND));


        searchSourceBuilder.query(qb);
        int size = 3;
        int from = page == "0" || page == "1" ? 0 : Integer.parseInt(page) * size;
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        // Sugesstion
//        SuggestionBuilder termSuggestionBuilder =
//                SuggestBuilders.termSuggestion("product_details").text(query);
//        SuggestBuilder suggestBuilder = new SuggestBuilder();
//        suggestBuilder.addSuggestion("suggest_product_search", termSuggestionBuilder);
//        searchSourceBuilder.suggest(suggestBuilder);

        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        List<Product> products = new ArrayList<>();
        for (SearchHit hit : hits.getHits()) {
            Gson gson = new Gson();
            Product p = gson.fromJson(hit.getSourceAsString(), Product.class);
            products.add(p);
        }
        System.out.println("brands are: " + query + " "+ page);

        Products products1 = new Products();
        products1.setProducts(products);
        products1.setCount(3);
        return ResponseEntity.ok(products1);
    }

    @GetMapping("/query_price_low_high")
    public ResponseEntity<List<Product>> queryPriceLowHigh(@RequestParam String query, @RequestParam String page) throws IOException {
//        String url = "http://localhost:2288/test3";
        SearchRequest searchRequest = new SearchRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        QueryBuilders
                .multiMatchQuery(query, "product_name", "product_details", "brand_name").boost(0.1f);

        QueryBuilder qb = new BoolQueryBuilder()
                .should(QueryBuilders.multiMatchQuery(query, "product_name", "product_details", "brand_name")
                        .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
                        .operator(Operator.AND));

        searchSourceBuilder.sort("price", SortOrder.ASC);
        searchSourceBuilder.query(qb);
        int size = 40;
        int from = page == "0" || page == "1" ? 0 : Integer.parseInt(page) * size;
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        // Sugesstion
//        SuggestionBuilder termSuggestionBuilder =
//                SuggestBuilders.termSuggestion("product_details").text(query);
//        SuggestBuilder suggestBuilder = new SuggestBuilder();
//        suggestBuilder.addSuggestion("suggest_product_search", termSuggestionBuilder);
//        searchSourceBuilder.suggest(suggestBuilder);

        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        List<Product> products = new ArrayList<>();
        for (SearchHit hit : hits.getHits()) {
            Gson gson = new Gson();
            Product p = gson.fromJson(hit.getSourceAsString(), Product.class);
            products.add(p);
        }
        System.out.println(products);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/query_price_high_low")
    public ResponseEntity<List<Product>> queryPriceHighLow(@RequestParam String query, @RequestParam String page) throws IOException {
//        String url = "http://localhost:2288/test3";
        SearchRequest searchRequest = new SearchRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        SignificantTextAggregationBuilder significant = AggregationBuilders.significantText("query", "product_details");
//        significant.subAggregation(AggregationBuilders.topHits("top_hit"));
//        searchSourceBuilder.aggregation(significant);
//        searchSourceBuilder.query(QueryBuilders.matchQuery(, query));

        QueryBuilders
                .multiMatchQuery(query, "product_name", "product_details", "brand_name").boost(0.1f);

        QueryBuilder qb = new BoolQueryBuilder()
                .should(QueryBuilders.multiMatchQuery(query, "product_name", "product_details", "brand_name")
//                        .analyzer("atsCustomSearchAnalyzer")
                        .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
                        .operator(Operator.AND));
//                .should(QueryBuilders.multiMatchQuery(term, "ngramFirstName^3", "ngramLastName^3", "ngramLocationName^3", "ngramCompanyName^3", "_all")
//                        .analyzer("atsCustomSearchAnalyzer")
//                        .operator(Operator.AND));

        searchSourceBuilder.sort("price", SortOrder.DESC);
        searchSourceBuilder.query(qb);
        int size = 40;
        int from = page == "0" || page == "1" ? 0 : Integer.parseInt(page) * size;
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);

        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        List<Product> products = new ArrayList<>();
        for (SearchHit hit : hits.getHits()) {

            Gson gson = new Gson();
            Product p = gson.fromJson(hit.getSourceAsString(), Product.class);
            products.add(p);
        }
        System.out.println(products);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/filter")
    public ResponseEntity<Products> queryFilter(@RequestBody FilterModel model,
                                                @RequestParam String page) throws IOException {
        SearchRequest searchRequest = new SearchRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        CountRequest countRequest = new CountRequest(StylConstants.ELASTIC_PRODUCT_INDEX_NAME);
        MultiMatchQueryBuilder categoryFilter;
        TermsQueryBuilder storeFilter;
        TermsQueryBuilder brandFilter;
        RangeQueryBuilder priceFilter;
        MultiMatchQueryBuilder genderFilter;

        BoolQueryBuilder priceqb = new BoolQueryBuilder();
        BoolQueryBuilder qb = new BoolQueryBuilder();
        qb.must(QueryBuilders.matchAllQuery().boost(1.2f));


        StringBuilder genderquery = new StringBuilder();
        List<String> genders = new ArrayList<>();
        if (model.getGenders() != null)
            model.getGenders().stream().forEach(gender -> {
                if (gender != null) {
                    genderquery.append(gender.getKeywords()).append(" ");
                    genders.add(gender.getKeywords());
                }

            });
        if (!genderquery.toString().isEmpty()) {
            genderFilter = QueryBuilders.multiMatchQuery(genderquery.toString(),
                    "product_name", "product_details", "brand_name", "category_name")
                    .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
                    .operator(Operator.OR);
            qb.must(genderFilter);
        } else {
            qb.must(QueryBuilders.matchAllQuery());
        }

        // category
        List<String> category = new ArrayList<>();
        StringBuilder catquery = new StringBuilder();
        if (model.getCategory() != null)

            model.getCategory().stream().forEach(cat -> {
                if (cat != null) {
                    catquery.append(cat.getName()).append(" ").append(cat.getId());
                    category.add(cat.getName());
                    if (cat.getChildren() != null)
                        cat.getChildren().stream().forEach(child -> {
                            catquery.append(child.getName()).append(" ").append(child.getId());
                            category.add(child.getName());
                        });
                }
                //children
            });
        if (!catquery.toString().isEmpty()) {
            categoryFilter = QueryBuilders
                    .multiMatchQuery(catquery.toString(), "category_name")
//                    .multiMatchQuery(catquery.toString(), "product_name", "product_details", "brand_name", "category_name")
                    .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
                    .operator(Operator.OR);
            ;
            qb.filter(categoryFilter);
        }

        //Store
        List<String> stores = new ArrayList<>();
        if (model.getStore() != null)
            model.getStore().stream().forEach(store -> {
                stores.add(store.getId());
            });
        if (!stores.isEmpty()) {
            storeFilter = QueryBuilders.termsQuery("store_id.keyword", stores);
            qb.filter(storeFilter);
        }

        //Brand
        List<String> brands = new ArrayList<>();
        if (model.getBrand() != null)
            model.getBrand().stream().forEach(brand -> {
                brands.add(brand.getName());
            });
        if (!stores.isEmpty()) {
            brandFilter = QueryBuilders.termsQuery("brand_name.keyword", stores);
            qb.filter(brandFilter);
        }
        //Price
        if (model.getMinPrice() != null && model.getMaxPrice() != "0.00" && model.getMaxPrice() != null) {
            double minPrice = Double.parseDouble(model.getMinPrice());
            double maxPrice = model.getMaxPrice() == "1000.00+" ? 10000.00 : Double.parseDouble(model.getMaxPrice());
//            searchSourceBuilder.aggregation(AggregationBuilders.range("price").field("price")
//                    .addRange(10.00, 80.00));
//            builder.must(QueryBuilders.rangeQuery("price").gte(minPrice).lte(maxPrice));
            priceFilter = QueryBuilders.rangeQuery("price").gte(minPrice).lte(maxPrice);
            qb.filter(priceqb.must(priceFilter));
        }

        System.out.println(qb);
//        searchSourceBuilder.query(qb);
        searchSourceBuilder.query(QueryBuilders
                .functionScoreQuery(qb, ScoreFunctionBuilders.randomFunction().seed(10000))
                .boostMode(CombineFunction.REPLACE));


//        searchSourceBuilder.aggregation(aggs);
        countRequest.query(qb);

        int size = 40;
        int from = page == "0" || page == "1" ? 0 : Integer.parseInt(page) * size;
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);

        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        CountResponse countResponse = restHighLevelClient.count(countRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        List<Product> products = new ArrayList<>();

        for (SearchHit hit : hits.getHits()) {
//            System.out.println(hit.getSourceAsString());
            Gson gson = new Gson();
            Product p = gson.fromJson(hit.getSourceAsString(), Product.class);
            products.add(p);
        }
        Products products1 = new Products();
        products1.setProducts(products);
        products1.setCount(countResponse.getCount());
        return ResponseEntity.ok(products1);
    }
}

