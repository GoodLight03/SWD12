package com.swd.supplierservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swd.supplierservice.Entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long>{
    
}
