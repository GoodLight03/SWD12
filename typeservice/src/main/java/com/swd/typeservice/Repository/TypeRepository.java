package com.swd.typeservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swd.typeservice.Entity.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long>{
    
}
