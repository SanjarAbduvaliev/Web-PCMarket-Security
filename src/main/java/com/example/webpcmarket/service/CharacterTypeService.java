package com.example.webpcmarket.service;

import com.example.webpcmarket.entity.CharacterType;
import com.example.webpcmarket.entity.Characters;
import com.example.webpcmarket.entity.teplate.Status;
import com.example.webpcmarket.payload.CharacterTypeDto;
import com.example.webpcmarket.repository.CharacterRepository;
import com.example.webpcmarket.repository.CharacterTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterTypeService {
    @Autowired
    CharacterTypeRepository characterTypeRepository;
    @Autowired
    CharacterRepository characterRepository;

    public ResponseEntity<?> addCharacterType(CharacterTypeDto characterTypeDto){


            CharacterType characterType=new CharacterType();
            characterType.setFeature(characterTypeDto.getFeature());
            Optional<Characters> optionalCharacters = characterRepository.findById(characterTypeDto.getCharacterId());
            if (optionalCharacters.isEmpty()){
                return ResponseEntity.ok(new Status("Bunday kategoriya mavjud emas",false));
            }
            Characters characters = optionalCharacters.get();
            if (characterRepository.existsByName(characters.getName())){
                characterType.setCharacter(characters);

                characterTypeRepository.save(characterType);
                return ResponseEntity.status(HttpStatus.CREATED).body(new Status(" qo'shildi",true));
            }
            return ResponseEntity.status(404).body(new Status(characters.getName()+" mavjud",false));


    }
    public ResponseEntity<?> getAllCharacterType(){
        List<CharacterType> characterTypeList = characterTypeRepository.findAll();
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(characterTypeList);
    }
    public ResponseEntity<?> getId(Integer id){
        Optional<CharacterType> optionalCharacterType = characterTypeRepository.findById(id);
        if (optionalCharacterType.isEmpty())
            return ResponseEntity.status(404).body(new Status("Detal topilmadi",false));
        return ResponseEntity.ok(optionalCharacterType.get());
    }
    public ResponseEntity<?> editCharacterType(Integer id,CharacterTypeDto characterTypeDto){
        Optional<CharacterType> optionalCharacterType = characterTypeRepository.findById(id);
        if (optionalCharacterType.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Status(characterTypeDto.getFeature()+" topilmadi!",false));
        CharacterType characterType = optionalCharacterType.get();
        characterType.setFeature(characterTypeDto.getFeature());
        characterTypeRepository.save(characterType);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Status("O'zgartirildi",true));
    }
    public ResponseEntity<?> delete(Integer id){
        characterTypeRepository.findById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Status("O'chirildi",true));
    }
}
