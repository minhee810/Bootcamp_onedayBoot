package com.springboot.test.data.repository;

import com.springboot.test.data.entity.Product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ProductRepositoryTest2 {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void basicCRUDTest(){
        /*create*/
        // given
        Product givenProduct = Product.builder()
                .name("note")
                .price(1000)
                .stock(500)
                .build();

        // when
        Product savedProduct = productRepository.save(givenProduct);

        Assertions.assertThat(savedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
        Assertions.assertThat(savedProduct.getName()).isEqualTo(givenProduct.getName());
        Assertions.assertThat(savedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
        Assertions.assertThat(savedProduct.getStock()).isEqualTo(givenProduct.getStock());

        /*read*/
        // when : 테스트 목적을 보여주는 단계, 실제 test 코드가 포함되며 테스트를 통한 결과값을 가져오게 됨.
        Product selectedProduct = productRepository.findById(savedProduct.getNumber())
                .orElseThrow(RuntimeException::new);

        // then : 테스트 결과를 검증하는 단계, 일반적으로 when 단계에서 나온 결과값을 검증하는 작업을 수행
        Assertions.assertThat(selectedProduct.getNumber())
                .isEqualTo(givenProduct.getNumber());
        Assertions.assertThat(selectedProduct.getName())
                .isEqualTo(givenProduct.getName());
        Assertions.assertThat(selectedProduct.getPrice())
                .isEqualTo(givenProduct.getPrice());
        Assertions.assertThat(selectedProduct.getStock())
                .isEqualTo(givenProduct.getStock());

        /*update*/
        // when
        Product foundProduct = productRepository.findById(selectedProduct.getNumber())
                .orElseThrow(RuntimeException::new);

        foundProduct.setName("장난감");

        Product updatedProduct = productRepository.save(foundProduct);

        // then
        assertEquals(updatedProduct.getName(), "장난감");

        /*delete*/
        // when
        productRepository.delete(updatedProduct);
        // then
        assertFalse(productRepository.findById(selectedProduct.getNumber()).isPresent());

    }
}
