package com.swd.typeservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swd.typeservice.Entity.Type;
import com.swd.typeservice.Service.TypeService;

@RestController
@RequestMapping("/")
public class TypeController {
    @Autowired
    private TypeService typeService;

      @GetMapping
    public ResponseEntity<List<Type>> getAllType() {
        return ResponseEntity.ok(typeService.getAll());
    }
}
