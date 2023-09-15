package com.springboot.test.data.dao;

import com.springboot.test.data.entity.Product;

public interface ProductDAO {

    // method 선언
    Product insertProduct(Product product); // 새로운 값 입력 시 사용, REST : POST
    Product selectProduct(Long number);     // 특정 번호를 조회, REST : GET
    Product updateProduct(Long number, String name) throws Exception; // 특정 번호로 상품 명 수정, REST :  PUT
    void deleteProduct(Long number) throws Exception; // REST : DELETE

}
