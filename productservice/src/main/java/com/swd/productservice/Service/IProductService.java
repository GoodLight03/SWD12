package com.swd.productservice.Service;

import java.util.List;

import com.swd.productservice.DTO.ProductDTO;
import com.swd.productservice.Entity.Product;

public interface IProductService {
    boolean createProduct(ProductDTO productDTO);
    ProductDTO productDetail(Integer id);
    boolean updateProduct(Integer id, ProductDTO productDTO);
    boolean deleteProduct(Integer id);
    boolean validProduct(ProductDTO productDTO, String action);
    void storeLog(String status);
    List<Product> getAllProduct();
}
