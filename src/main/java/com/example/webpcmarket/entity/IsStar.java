package com.example.webpcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IsStar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer oneStar=0;
    private Integer twoStar=0;
    private Integer threeStar=0;
    private Integer fourStar=0;
    private Integer fiveStar=0;
    private boolean active=false;

}
