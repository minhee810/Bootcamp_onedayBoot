package com.springboot.relationship.data.repository.support;


import com.springboot.relationship.data.entity.Product;

import java.util.List;

// jpaRepository 를 사용하지 않고, 우리가 만든 repository를 사용하는 인터페이스
public interface ProductRepositoryCustom {

    List<Product> findByName (String name);

}
