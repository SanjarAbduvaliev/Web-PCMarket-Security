package com.example.webpcmarket.entity;

import com.example.webpcmarket.payload.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Category extends AbsEntity {
    private String discription;
    @ManyToOne
    private Category parrentCategory;
}
