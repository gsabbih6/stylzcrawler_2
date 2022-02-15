package com.sabbih.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WardrobeService {
    @Autowired
    WardrobeRepository repository;
    @Autowired
    ProductService service;

    public WardrobeDTO create(Wardrobe wardrobe) {
        WardrobeDTO wardrobeDTO = new WardrobeDTO();
        wardrobeDTO.setWardrobe(repository.save(wardrobe));
        wardrobeDTO.setProductList(new ArrayList<>());
        return wardrobeDTO;

    }

    public WardrobeDTO getByID(String id) {
//        List<WardrobeDTO> wardrobeDTOList = new ArrayList<>();
        Wardrobe w = repository.findById(UUID.fromString(id)).get();

            List<Product> list = new ArrayList<>();
            if (w.getProductids() != null)
                w.getProductids().stream().forEach((prdid) -> {
                    Product p = service.get(UUID.fromString(prdid)).get();
                    list.add(p);
                });

            WardrobeDTO wardrobeDTO = new WardrobeDTO();
            wardrobeDTO.setWardrobe(w);
            wardrobeDTO.setProductList(list);;
        return wardrobeDTO;
    }

    public List<WardrobeDTO> getByUserid(String userid) {

        List<WardrobeDTO> wardrobeDTOList = new ArrayList<>();
        for (Wardrobe w : repository.findByUserid(UUID.fromString(userid))) {

            List<Product> list = new ArrayList<>();
            if (w.getProductids() != null)
                w.getProductids().stream().forEach((prdid) -> {
                    Product p = service.get(UUID.fromString(prdid)).get();
                    list.add(p);
                });

            WardrobeDTO wardrobeDTO = new WardrobeDTO();
            wardrobeDTO.setWardrobe(w);
            wardrobeDTO.setProductList(list);
            wardrobeDTOList.add(wardrobeDTO);

        }
        return wardrobeDTOList;
    }

    public WardrobeDTO update(Wardrobe wardrobe) {
        Wardrobe w = repository.save(wardrobe);

        List<Product> list = new ArrayList<>();
        if (w.getProductids() != null)
            w.getProductids().stream().forEach((prdid) -> {
                Product p = service.get(UUID.fromString(prdid)).get();
                list.add(p);
            });
        WardrobeDTO wardrobeDTO = new WardrobeDTO();
        wardrobeDTO.setWardrobe(w);
        wardrobeDTO.setProductList(list);
        return wardrobeDTO;
    }
}
