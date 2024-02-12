package com.tobeto.pair9.services.rules;

import com.tobeto.pair9.core.utilities.exceptions.ColorBusinessException;
import com.tobeto.pair9.core.utilities.results.Messages;
import com.tobeto.pair9.repositories.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ColorBusinessRules {

    private final ColorRepository colorRepository;

    public void isExistColorByName(String name){
        if(colorRepository.existsByName(name)){
            throw new ColorBusinessException(Messages.colorIsAlreadySaved);
        }
    }

    public void isExistColorById(Integer id){
        if(!colorRepository.existsById(id)){
            throw new ColorBusinessException(Messages.colorIsNotFound);
        }
    }
}
