package com.example.webpcmarket.controller;

import com.example.webpcmarket.payload.ProductDto;
import com.example.webpcmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto productDto){
        return productService.addProduct(productDto);

    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping("/categoryproduct/{productId}")
    public ResponseEntity<?> getPrId(@PathVariable Integer productId){
        return productService.getCategoryProductId(productId);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public ResponseEntity<?> getAll(){
       return productService.getAllProduct();
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody ProductDto productDto,@PathVariable Integer id){
        return productService.editProduct(productDto,id);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }
}
