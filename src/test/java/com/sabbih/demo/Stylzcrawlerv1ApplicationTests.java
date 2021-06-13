package com.sabbih.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class Stylzcrawlerv1ApplicationTests {
    @Autowired
    private StoreRepository storeRepository;

    @Test
    void contextLoads() {
//    	Store s=new Store("Menkomo","https://menkomo.com","https://menkomo.com/logourl","Ghana");
//    	storeRepository.save(s);

    	new Controller().start();
    }

}
