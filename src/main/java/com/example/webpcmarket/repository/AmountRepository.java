package com.example.webpcmarket.repository;

import com.example.webpcmarket.entity.Amount;
import com.example.webpcmarket.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AmountRepository extends JpaRepository<Amount,Integer> {
    boolean existsByPhoneNumberNot(String phoneNumber);
    Basket findAmountByBasket_Id(Integer basket_id);
    boolean existsByPhoneNumberAndFullNameNot(String phoneNumber, String fullName);
}
