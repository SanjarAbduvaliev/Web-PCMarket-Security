package com.example.webpcmarket.entity;

import com.example.webpcmarket.payload.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Product extends AbsEntity {

    private String brand;

    @OneToOne
    private Characters characters;

    @OneToOne
    private Attachment attachment;

    private Double price;

    @OneToOne
    private IsStar isStars;

    @ManyToOne
    private Measurement measurement;
    @ManyToOne
    private Category category;
}
