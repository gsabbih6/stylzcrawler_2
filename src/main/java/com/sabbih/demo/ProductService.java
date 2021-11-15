package com.sabbih.demo;

import com.datastax.oss.driver.api.core.cql.PagingState;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product create(Product product) {
//        Product sf = productRepository.findByPaymentUrl(product.paymentUrl);
//        if (productRepository.existsByPaymentUrl(product.getPaymentUrl())) return product;
//        if (productRepository.existsById(product.getId())) {
//            Product product1 = productRepository.findById(product.getId()).get();
//            Iterator<String> iter = product1.getPrice().iterator();
//            boolean isIn = false;
//            while (iter.hasNext()) {
//                isIn = iter.next().equalsIgnoreCase(product.getPrice().toArray(new String[1])[0]);
//            }
//            if (!isIn)
//                for (int i = 0; i < product1.getPrice().size(); i++) {
//                    product.getPrice().add(product1.getPrice().toArray(new String[product1.getPrice().size()])[i]);
//                }
//
////            if (product1.getPrice().contains(product.getPrice().toArray(new String[1])[0])) {
////                product1.getPrice().add(product.getPrice().toArray(new String[1])[0]);
////
////            }
//            return update(product);
//
//
//        }
        return productRepository.save(product);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAll() {
//        createCassandraPageRequest(48,)
        return productRepository.findAll();
    }

    public Optional<Product> get(UUID uuid) {
        return productRepository.findById(uuid);
    }

    public void delete(UUID uuid) {
        productRepository.deleteById(uuid);
    }
//    private CassandraPageRequest createCassandraPageRequest(final Integer limit, final String pagingState) {
//        final PageRequest pageRequest = PageRequest.of(0, limit);
//        final PagingState pageState = pagingState != null ? PagingState.fromString(pagingState) : null;
//        return CassandraPageRequest.of(pageRequest, pageState);
//    }

    public void crawlContent(Product p)  {
        switch (p.program_name.toLowerCase()) {
            case StylConstants.COMPANY_INGRID:
                processIngrid(p);
                break;
            default:
                break;
        }

    }

    private void processIngrid(Product p)  {
        // extract

        Document doc = null;
        try {
            doc = Jsoup.connect(p.getPayment_url()).get();
        } catch (IOException e) {
//            e.printStackTrace();
            productRepository.deleteById(p.getId());
            System.out.println("Deleted Product: "+p.payment_url);
            return;
        }


//        Set<String> imageurls=new LinkedHashSet<>();
        Elements slideshow = doc.getElementsByClass("product__thumbnail");
//        Elements achors = slideshow.getElementsByTag("img");
        Set<String> imageUrls = new HashSet<>();
        slideshow.stream().forEach((e) -> {
            imageUrls.add(e.getElementsByTag("img").attr("data-src"));
//            System.out.println(e.text());
        });
        p.setImage_urls(imageUrls);

        String title=doc.getElementsByClass("product-title").first().text();
        p.setProduct_name(title);


        System.out.println(imageUrls);

        productRepository.save(p);




    }
}
