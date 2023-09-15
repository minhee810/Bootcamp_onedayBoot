package com.springboot.test.data.dao.Impl;

import com.springboot.test.data.dao.ProductDAO;
import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component      // Bean 자동 생성  repository 클래스가 있어서 component 로 작성
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;    // 주입받을 변수 선언

    // 1. 생성자 : 이거 만들 거얌.
    // 2. setter
    // 3. 필드 변수

    @Autowired // 자동 주입 받아야 하므로...
    public ProductDAOImpl(ProductRepository productRepository) {        // 생성자 생성
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);    // JpaRepository가 주는 메서드 save()
        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {         // 조회
        Product selectedProduct = productRepository.getById(number);
        return selectedProduct;
    }

    @Override
    public Product updateProduct(Long number, String name) throws Exception {
        Optional<Product>  selectedProduct = productRepository.findById(number); //

        Product updatedProduct;

        if (selectedProduct.isPresent()){
            Product product = selectedProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);       // DB에 반영되는 부분
        }else {
            throw new Exception();
        }
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional <Product> selectedProduct = productRepository.findById(number);

        if(selectedProduct.isPresent()){
            Product product = selectedProduct.get();

            productRepository.delete(product);

        }else {
            throw new Exception();
        }
    }

    // 이제 Service와 연동
}
