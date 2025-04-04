package com.swd.productservice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swd.productservice.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "select p from Product p where p.tenSanPham =?1")
    Optional<Product> findByNameName(String tenSanPham);
    
}
