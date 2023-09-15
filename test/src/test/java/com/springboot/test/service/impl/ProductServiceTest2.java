package com.springboot.test.service.impl;

import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import com.springboot.test.service.Impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@Import({ProductServiceImpl.class})  // 어떤 클래스를 주입받겠다는 의미 주의할 점은 {}처리가 되어있다는 점 .
public class ProductServiceTest2 {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceTest2.class); // 추가 1
    // 스프링의 기능을 의존받을 경우. 즉 스프링 빈에 등록해서 사용할 때
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUpTest(){
        productService = new ProductServiceImpl(productRepository);
    }

   //*
   @Test
    void getProductTest(){
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("펜");
        givenProduct.setPrice(1000);
        givenProduct.setStock(1234);

    Mockito.when(productRepository.findById(123L))
            .thenReturn(Optional.of(givenProduct)); // ThenReturn : 리턴 값이 있을 수 있고 없을 수 있다.
       // 위에서 만든 값을 리턴 시키겠다.


        // When
        ProductResponseDto productResponseDto = productService.getProduct(123L);

        // Then 넘겨준 값이 맞는지 확인해주는 부분
        Assertions.assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        Assertions.assertEquals(productResponseDto.getName(), givenProduct.getName());
        Assertions.assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(productResponseDto.getStock(), givenProduct.getStock());

        verify(productRepository).findById(123L);
    }
    //*/
// 객체를 전달할 때 any라는 메서드 사용


   /* @Test
    void saveProductTest(){

    }*/
   // 예제 7.12
   @Test
   void saveProductTest() {
       // given
       Mockito.when(productRepository.save(any(Product.class)))
               .then(returnsFirstArg());

       // when
       ProductResponseDto productResponseDto = productService.saveProduct(
               new ProductDto("펜", 1000, 1234));

       // then
       Assertions.assertEquals(productResponseDto.getName(), "펜");
       Assertions.assertEquals(productResponseDto.getPrice(), 1000);
       Assertions.assertEquals(productResponseDto.getStock(), 1234);

       verify(productRepository).save(any()); // any() 포함해서 재 검진

   }
}


