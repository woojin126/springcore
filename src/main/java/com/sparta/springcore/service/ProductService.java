package com.sparta.springcore.service;

import com.sparta.springcore.model.Product;
import com.sparta.springcore.dto.ProductMypriceRequestDto;
import com.sparta.springcore.dto.ProductRequestDto;
import com.sparta.springcore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

/*
    @Autowired
    public ProductService(ApplicationContext context){
       // this.productRepository = (ProductRepository) context.getBean("productRepository");
        this.productRepository = context.getBean(ProductRepository.class);
    }
*/

    public Product createProduct(ProductRequestDto requestDto){
// 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);
        productRepository.save(product);

        return product;
    }

    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto){
       if (requestDto.getMyprice() <= 0){
           throw new RuntimeException("hope lower price more than 0 ");
       }
        Product product = productRepository.findById(id).orElseThrow(() -> new NullPointerException("no id"));
        if (product == null) {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }

        int myprice = requestDto.getMyprice();
        product.setMyprice(myprice);
        productRepository.save(product);

        return product;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}