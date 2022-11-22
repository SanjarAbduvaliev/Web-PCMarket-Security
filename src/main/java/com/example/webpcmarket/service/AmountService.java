package com.example.webpcmarket.service;

import com.example.webpcmarket.entity.Amount;
import com.example.webpcmarket.entity.Basket;
import com.example.webpcmarket.entity.Product;
import com.example.webpcmarket.entity.teplate.Status;
import com.example.webpcmarket.payload.AmountDto;
import com.example.webpcmarket.payload.BaskentDto;
import com.example.webpcmarket.repository.AmountRepository;
import com.example.webpcmarket.repository.BasketRepository;
import com.example.webpcmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AmountService {
    @Autowired
    AmountRepository amountRepository;
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<?> addAmount(AmountDto amountDto){
        Amount amount=new Amount();


        if (amountRepository.existsByPhoneNumberNot(amountDto.getPhoneNumber())){
            amount.setPhoneNumber(amountDto.getPhoneNumber());
            amount.setFullName(amountDto.getFullName());
            amountRepository.save(amount);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new Status("Ro'yhatga olindingiz",true));


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new Status("Bunday telefon raqamlik mijoz mavjud",false));
    }
    public ResponseEntity<?> getAllAmount(){
        return  ResponseEntity.ok(amountRepository.findAll());
    }
    public ResponseEntity<?> editAmount(AmountDto amountDto,Integer id){
        Optional<Amount> optionalAmount = amountRepository.findById(id);
        if (optionalAmount.isPresent()){
            Amount amount = optionalAmount.get();
            if (amountRepository.existsByPhoneNumberAndFullNameNot(
                    amountDto.getPhoneNumber(), amountDto.getFullName())){
                amount.setFullName(amountDto.getFullName());
                amount.setPhoneNumber(amountDto.getPhoneNumber());
                amountRepository.save(amount);
                return ResponseEntity.ok("Muvoffaqiyatlik o'zgartirildi");
            }

        }return ResponseEntity.ok("Malumotlar  avvaldan mavjud");
    }
    public ResponseEntity<?> deleteUser(Integer id){
       try {
            amountRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new Status("User o'chirildi",true));
        }catch (Exception e){
           return ResponseEntity.ok("xatolik");
       }
    }
    //savatga saqlovchi metod
    public ResponseEntity<?> addBasket(BaskentDto baskentDto){
        List<Product> productList = productRepository.findAllById(baskentDto.getProductId());
        Basket basket = amountRepository.findAmountByBasket_Id(baskentDto.getBaskentId());
        basket.setProducts(productList);
        basketRepository.save(basket);
        return ResponseEntity.ok("Savatga saqlandi");

    }

}
