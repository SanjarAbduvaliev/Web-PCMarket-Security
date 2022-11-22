package com.example.webpcmarket.service;

import com.example.webpcmarket.entity.*;
import com.example.webpcmarket.entity.teplate.Status;
import com.example.webpcmarket.payload.ProductDto;
import com.example.webpcmarket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    MeasurmentRepository measurmentRepository;
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    IsStarRepository isStarRepository;

    public ResponseEntity<?> addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setActive(productDto.isActive());
        Optional<Measurement> optionalMeasurement = measurmentRepository.findById(productDto.getMeasurmentId());
        Optional<Characters> optionalCharacters = characterRepository.findById(productDto.getCharacterId());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentPhotoId());
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty())
            return ResponseEntity.status(404).body("Bunday kategoriya mavjud emas");
        if (optionalMeasurement.isEmpty())
            return ResponseEntity.status(404).body("Bunday o'chov birlik mavjud emas");
        if (optionalCharacters.isEmpty())
            return ResponseEntity.status(404).body("Mavjud emas!");
        if (optionalAttachment.isEmpty())
            return ResponseEntity.status(404).body("Rasm  mavjud emas");
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setAttachment(optionalAttachment.get());
        product.setCharacters(optionalCharacters.get());

        //MAHSULOTNI BAHOLASH UCHUN
        IsStar isStars = product.getIsStars();
        if (productDto.getStars() == 1) {
            isStars.setOneStar(product.getIsStars().getOneStar() + 1);
        } else if (productDto.getStars() == 2) {
            isStars.setTwoStar(product.getIsStars().getTwoStar() + 2);
        } else if (productDto.getStars() == 3) {
            isStars.setThreeStar(product.getIsStars().getThreeStar() + 3);
        } else if (productDto.getStars() == 4) {
            isStars.setFourStar(product.getIsStars().getFourStar() + 4);
        } else if (productDto.getStars() == 5) {
            isStars.setFiveStar(product.getIsStars().getFiveStar() + 5);
        }
        product.setIsStars(isStars);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Status("Mahsulot qo'shildi", true));

    }

    //OMBORDAGI HAMMA MAHSULOTNI OLIB KELADI
    public ResponseEntity<?> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    //KATEGORIYALAR BO'YICHA OLADI
    public ResponseEntity<?> getCategoryProductId(Integer categoryId) {
        List<Product> productList = productRepository.findByCategory_Id(categoryId);
        return ResponseEntity.ok(productList);
    }

    public ResponseEntity<?> editProduct(ProductDto productDto, Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<Measurement> optionalMeasurement = measurmentRepository.findById(productDto.getMeasurmentId());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentPhotoId());
        Optional<Characters> optionalCharacters = characterRepository.findById(productDto.getCharacterId());
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalProduct.isEmpty())
            return ResponseEntity.status(404).body("Mahsulot topilmadi");
        if (optionalAttachment.isEmpty())
            return ResponseEntity.status(404).body("Rasm topilmadi");
        if (optionalCategory.isEmpty())
            return ResponseEntity.status(404).body("Kategoriya topilmadi");
        if (optionalCharacters.isEmpty())
            return ResponseEntity.status(404).body("Mavjud emas!");
        if (optionalMeasurement.isEmpty())
            return ResponseEntity.status(404).body("O'chov birlk topilmadi");
        Product product = optionalProduct.get();
        product.setCategory(optionalCategory.get());
        product.setCharacters(optionalCharacters.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setAttachment(optionalAttachment.get());
        product.setActive(productDto.isActive());
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
        return ResponseEntity.ok("Mahsulot o'zgartirildi");

    }

    public ResponseEntity<?> deleteProduct(Integer id) {
        try {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new Status("Mahsulot o'chirildi", false));
        } catch (Exception exception) {
            return ResponseEntity.status(404).body(new Status("Xatolik!!!", false));
        }

    }
}
