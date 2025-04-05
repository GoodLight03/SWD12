package com.swd.supplierservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swd.supplierservice.Entity.Supplier;
import com.swd.supplierservice.Repository.SupplierRepository;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    public SupplierRepository typeRepository;

    @Override
    public List<Supplier> getAll() {
        return typeRepository.findAll();
    }
    
}
