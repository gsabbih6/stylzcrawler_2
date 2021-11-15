package com.sabbih.demo;

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
import org.springframework.web.client.RestTemplate;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableCassandraRepositories(basePackageClasses =
        {ProductRepository.class, StoreRepository.class, CategoryRepository.class, ColourRepository.class})

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
                .connectedTo("https://stylz.es.eastus2.azure.elastic-cloud.com:9243") //https://stylz.es.eastus2.azure.elastic-cloud.com:9243
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }

    public static void main(String[] args) {
        SpringApplication.run(Stylzcrawlerv1Application.class, args);

    }

}
