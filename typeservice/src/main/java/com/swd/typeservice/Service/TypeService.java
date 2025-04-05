package com.swd.typeservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swd.typeservice.Entity.Type;
import com.swd.typeservice.Repository.TypeRepository;
@Service
public class TypeService implements ITypeService {

    @Autowired
    public TypeRepository typeRepository;

    @Override
    public List<Type> getAll() {
        return typeRepository.findAll();
    }
    
}
