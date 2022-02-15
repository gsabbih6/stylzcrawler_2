package com.sabbih.demo;

import com.google.gson.Gson;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    RestHighLevelClient elastic;

    public Store create(Store store) throws IOException {
        IndexRequest request = new IndexRequest(StylConstants.ELASTIC_STORE_INDEX_NAME);
        request.id(store.getId().toString());
        request.source(new Gson().toJson(store), XContentType.JSON);
        IndexResponse response = elastic.index(request, RequestOptions.DEFAULT);
        System.out.println("category: " + response.toString());
        return storeRepository.save(store);
    }

    public Store update(Store store) {
        return null;
    }
    public Long getTotalItems() throws IOException {
        CountRequest countRequest = new CountRequest(StylConstants.ELASTIC_STORE_INDEX_NAME);
        countRequest.query(QueryBuilders.matchAllQuery());
        CountResponse response = elastic.count(countRequest, RequestOptions.DEFAULT);
        return response.getCount();
    }
    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    public void delete(UUID uuid) {

    }

    public Optional<Store> get(UUID fromString) {
        return storeRepository.findById(fromString);
    }
}
