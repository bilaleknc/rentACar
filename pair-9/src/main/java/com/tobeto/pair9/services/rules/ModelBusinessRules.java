package com.tobeto.pair9.services.rules;

import com.tobeto.pair9.core.utilities.exceptions.ModelBusinessException;
import com.tobeto.pair9.core.utilities.results.Messages;
import com.tobeto.pair9.repositories.ModelRepository;
import com.tobeto.pair9.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelBusinessRules {

    private final ModelRepository modelRepository;
    private final UserService userService;

    public void isExistModelByName(String name){
        if(modelRepository.existsByName(name)){
            throw new ModelBusinessException(Messages.modelAlreadySaved);
        }
    }
    public void isExistModelById(Integer id){
        if(!modelRepository.existsById(id)){
            throw new ModelBusinessException(Messages.modelIsNotFound);
        }
    }

    public void isExistBrandById(Integer id){
        if(!userService.isExistUserById(id)){
            throw new ModelBusinessException(Messages.modelIsNotFound);
        }
    }
}
