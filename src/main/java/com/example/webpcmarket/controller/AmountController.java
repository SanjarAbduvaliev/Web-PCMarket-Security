package com.example.webpcmarket.controller;

import com.example.webpcmarket.payload.AmountDto;
import com.example.webpcmarket.service.AmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/amount")
public class AmountController {

    @Autowired
    AmountService amountService;
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody AmountDto amountDto){
        return amountService.addAmount(amountDto);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public ResponseEntity<?> getAllUser(){
        return amountService.getAllAmount();
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid@RequestBody AmountDto amountDto,@PathVariable Integer id){
        return amountService.editAmount(amountDto,id);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return   amountService.deleteUser(id);
    }

}
