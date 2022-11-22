package com.example.webpcmarket.entity;

import com.example.webpcmarket.entity.Characters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CharacterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String feature;
    @ManyToOne
    private Characters character;

    public CharacterType(String feature) {
        this.feature = feature;
    }
}
