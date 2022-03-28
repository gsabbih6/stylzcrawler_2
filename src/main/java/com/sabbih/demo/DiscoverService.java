package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class DiscoverService {
    @Autowired
    DiscoverRepository repository;
    @Autowired
    ProductService productService;

    public Discover save(Discover discover) {
        discover.setId(UUID.nameUUIDFromBytes(discover.getName().getBytes(StandardCharsets.UTF_8)));

        return repository.save(discover);
    }

    public List<DiscoverDTO> getAll() {
        List<Discover> discovers = repository.findAll();
        List<DiscoverDTO> discoversDTO = new ArrayList<>();

        discovers.stream().forEach((discover -> {
            DiscoverDTO discoverDTO = new DiscoverDTO();
            discoverDTO.setShort_desc(discover.short_desc);
            discoverDTO.setThumbnail(discover.thumbnail);
            discoverDTO.setId(discover.id);
            discoverDTO.setProducts(getProducts(discover.product_ids));
            discoversDTO.add(discoverDTO);
        }));

        return discoversDTO;
    }

    private Products getProducts(Set<String> product_ids) {
        Products products = new Products();
        List<Product> productList = new ArrayList<>();
        products.setCount(product_ids.size());
        product_ids.stream().forEach((id) -> {
            productList.add(productService.get(UUID.fromString(id)).get());
        });
        products.setProducts(productList);

        return products;
    }
}
