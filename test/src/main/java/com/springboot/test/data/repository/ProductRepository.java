package com.springboot.test.data.repository;

import com.springboot.test.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

  /*

    List<T> findAll(); ==> select * from

    List<T> findAll(Sort sort); ==> select * from order by

    List<T> findAllById(Iterable<ID> ids); ==> select * from where

    <S extends T> saveAll(Iterable<S> entities);  ==> insert, update, delete 에서 사용됨.

    */
}
