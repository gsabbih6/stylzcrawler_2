package com.sabbih.demo;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.utils.Utils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.time.Duration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
//@EnableWebSecurity
//@EnableCassandraRepositories(basePackageClasses =
//        {ProductRepository.class, StoreRepository.class, CategoryRepository.class, ColourRepository.class})

public class Stylzcrawlerv1Application {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    //    @Bean
//    public RestHighLevelClient HighLevelRestClient() {
//        return new RestHighLevelClient(
//                RestClient.builder(new HttpHost("localhost", 9200, "http"))
//                        .setDefaultHeaders(new Header[]{
//                                new BasicHeader("accept", "application/json"),
//                                new BasicHeader("content-type", "application/json")})
//                        .setFailureListener(new RestClient.FailureListener() {
//                            public void onFailure(Node node) {
//                                System.err.println("High level Rest Client Failure on node " +
//                                        node.getName());
//                            }
//                        }));
//    }

    @Bean
    public RestHighLevelClient client() {
        ClientConfiguration clientConfiguration
                = ClientConfiguration.builder()
//                .connectedTo("es02")
                .connectedTo("10.60.8.57:9200")
//                .connectedTo("104.198.79.248:9200")
//                .connectedTo("localhost:9200")
//                .withConnectTimeout(50000)
//                .usingSsl()
//                .withProxy("localhost:8888")
//                .withPathPrefix("ela")
//                .withConnectTimeout(Duration.ofSeconds(50))
//                .withSocketTimeout(Duration.ofSeconds(30))
//                .withDefaultHeaders(defaultHeaders)
//                .withBasicAuth()
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }

    @Bean
    public ImageKit imagekit() throws IOException {
        ImageKit imageKit = ImageKit.getInstance();
        Configuration config = Utils.getSystemConfig(Stylzcrawlerv1Application.class);
        imageKit.setConfig(config);
        return imageKit;
    }

    public static void main(String[] args) {
        SpringApplication.run(Stylzcrawlerv1Application.class, args);

    }

}
