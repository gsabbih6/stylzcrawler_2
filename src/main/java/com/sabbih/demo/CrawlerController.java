package com.sabbih.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class CrawlerController {
    @PostMapping("/crawl")
    public void beginCrawl(){
        new Controller().start();
    }
    @PostMapping("/")
    public void beginImageProcessing(){
        new Controller().start();
    }
}
