package com.swd.productservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.swd.productservice.DTO.ProductDTO;
import com.swd.productservice.Entity.Product;
import com.swd.productservice.Service.ProductServiceImpl;

@RestController
@RequestMapping("/")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<Boolean> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @PostMapping("/validate/{action}")
    public ResponseEntity<Boolean> valid(@PathVariable String action, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.validProduct(productDTO,action));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
        ProductDTO product = productService.productDetail(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateProduct(@PathVariable Integer id,@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProduct(id,productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

   //@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
}
