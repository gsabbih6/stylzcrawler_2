package com.sabbih.demo;

import com.google.gson.Gson;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RestHighLevelClient elastic;

    public Category create(Category category) throws IOException {
//        IndexRequest request = new IndexRequest(StylConstants.ELASTIC_CATEGORY_INDEX_NAME);
//        request.id(category.getId().toString());
//        request.source(new Gson().toJson(category), XContentType.JSON);
//        elastic.index(request, RequestOptions.DEFAULT);
        return categoryRepository.save(category);
    }

    public Store update(Store store) {
        return null;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> get(UUID uuid) {
        return categoryRepository.findById(uuid);
    }

    public void delete(UUID uuid) {
        categoryRepository.deleteById(uuid);
    }

}
