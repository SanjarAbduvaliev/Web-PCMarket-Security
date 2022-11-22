package com.example.webpcmarket.controller;

import com.example.webpcmarket.payload.CharacterTypeDto;
import com.example.webpcmarket.repository.CharacterTypeRepository;
import com.example.webpcmarket.service.CharacterTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/charactertype")
public class CharacterTypeController {
    @Autowired
    CharacterTypeService characterTypeService;
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody CharacterTypeDto characterTypeDto){
        return characterTypeService.addCharacterType(characterTypeDto);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id,@RequestBody CharacterTypeDto characterTypeDto){
        return characterTypeService.editCharacterType(id,characterTypeDto);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return characterTypeService.getAllCharacterType();
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable Integer id){
        return characterTypeService.getId(id);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return characterTypeService.delete(id);
    }
}
