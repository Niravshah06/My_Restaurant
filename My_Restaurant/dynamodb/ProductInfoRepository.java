package com.myeample.restapi.restaurants.dynamodb;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
public interface ProductInfoRepository extends
  CrudRepository<ProductInfo, String> {
     
    List<ProductInfo> findById(String id);
}