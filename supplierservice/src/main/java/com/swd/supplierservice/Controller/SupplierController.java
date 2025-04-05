package com.swd.supplierservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swd.supplierservice.Entity.Supplier;
import com.swd.supplierservice.Service.SupplierService;



@RestController
@RequestMapping("/")
public class SupplierController {
    @Autowired
    private SupplierService typeService;

      @GetMapping
    public ResponseEntity<List<Supplier>> getAllType() {
        return ResponseEntity.ok(typeService.getAll());
    }
}
