package com.sabbih.demo;

import com.google.gson.Gson;
import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.SignificantTextAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/search")
public class Search {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @GetMapping("/trending")
    public ResponseEntity<List<Product>> searchTrending() throws IOException {
//        String url = "http://localhost:2288/test3";
        SearchRequest searchRequest = new SearchRequest("stylz1");
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
    public ResponseEntity<List<Product>> query(@RequestParam String query, @RequestParam String page) throws IOException {
//        String url = "http://localhost:2288/test3";
        SearchRequest searchRequest = new SearchRequest("stylz1");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        SignificantTextAggregationBuilder significant = AggregationBuilders.significantText("query", "product_details");
//        significant.subAggregation(AggregationBuilders.topHits("top_hit"));
//        searchSourceBuilder.aggregation(significant);
//        searchSourceBuilder.query(QueryBuilders.matchQuery(, query));
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(query, "product_name"));
        int size = 40;
        int from = page == "0" || page == "1" ? 0 : Integer.parseInt(page) * size;
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        // Sugesstion
        SuggestionBuilder termSuggestionBuilder =
                SuggestBuilders.termSuggestion("product_details").text(query);
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("suggest_product_search", termSuggestionBuilder);
        searchSourceBuilder.suggest(suggestBuilder);

        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        List<Product> products = new ArrayList<>();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
            Gson gson = new Gson();
            Product p = gson.fromJson(hit.getSourceAsString(), Product.class);
            products.add(p);
        }
        return ResponseEntity.ok(products);
    }
}
