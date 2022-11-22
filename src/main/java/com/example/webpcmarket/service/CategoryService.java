package com.example.webpcmarket.service;

import com.example.webpcmarket.entity.Category;
import com.example.webpcmarket.entity.teplate.Status;
import com.example.webpcmarket.payload.CategoryDto;
import com.example.webpcmarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public HttpEntity<?> addCategory(CategoryDto categoryDto){

        if (categoryRepository.existsByName(categoryDto.getName())) {
            return ResponseEntity.status(404).body(new Status(categoryDto.getName()+" nomli kategoriya mavjud",false));
        }
        Category category=new Category();
        if (categoryDto.getCategoryId()==null){

            Category parrentCategory=new Category();
            parrentCategory.setActive(categoryDto.isActive());
            parrentCategory.setDiscription(categoryDto.getDiscription());
            parrentCategory.setName(categoryDto.getName());

            categoryRepository.save(parrentCategory);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new Status("Kategoriya muvoffaqiyatlik saqlandi",true));
        }
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryId());
        if (optionalCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Status("Bunday kategoriya mavjud emas",false));
        }

        category.setParrentCategory(optionalCategory.get());
        category.setDiscription(categoryDto.getDiscription());
        category.setName(categoryDto.getName());
        category.setActive(categoryDto.isActive());
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new Status("Kategoriya muvofaqqiyatli qo'shildi",true));
    }
    public ResponseEntity<?> getCategoryId(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent())
            return ResponseEntity.ok(optionalCategory.get());
        return ResponseEntity.ok(new Status("Kategoriya mavjud emas",false));
    }
    public ResponseEntity<?> getAllCategory(){
        return ResponseEntity.ok(categoryRepository.findAll());
    }
    public ResponseEntity<?> editCategory(Integer id, CategoryDto categoryDto){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            category.setName(categoryDto.getName());
            Category category_id = categoryRepository.findByParrentCategory_Id(categoryDto.getCategoryId());
            category.setParrentCategory(category_id);
            category.setDiscription(categoryDto.getDiscription());
            category.setActive(categoryDto.isActive());
            categoryRepository.save(category);
            return ResponseEntity.ok(new Status("Categoriya tasdiqlandi",true));

        }
        return ResponseEntity.ok(new Status("Bundan kategoriya mavjud emas",false));

    }

    public ResponseEntity<?> deleteCategory(Integer id){
        categoryRepository.deleteById(id);
        return ResponseEntity.ok(new Status("Kategoriya o'chirildi",true));
    }
}
