package com.springboot.test.Controller;

import com.springboot.test.data.dto.ChangeProductNameDto;
import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {       // 생성자 자동 주입 받을것이기 때문에 @추가
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductResponseDto>getProduct(Long number){
        ProductResponseDto productResponseDto = productService.getProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);       // 코드 값 변경시키는 부분
    }
    // 매개변수 : 프레젠테이션에서 전달해주는 값 3개 이름 동일하면 자동 매핑 따라서 @RequestBody해줌.
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto){    // 매개변수 : 프레젠테이션에서 전달해주는 값 3개
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    // 수정 : 값이 2개 넘어와서 생성자 생성해둠.
    @PutMapping
    public ResponseEntity<ProductResponseDto> changeProductName(@RequestBody ChangeProductNameDto changeProductNameDto)throws Exception {
        ProductResponseDto productResponseDto = productService.changeProductName(
                changeProductNameDto.getNumber(),
                changeProductNameDto.getName());
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(Long number) throws Exception{
        productService.deleteProduct(number);

        return  ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}
