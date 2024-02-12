package com.tobeto.pair9.services.rules;

import com.tobeto.pair9.core.utilities.exceptions.CarBusinessException;
import com.tobeto.pair9.core.utilities.results.Messages;
import com.tobeto.pair9.repositories.CarRepository;
import com.tobeto.pair9.services.abstracts.ColorService;
import com.tobeto.pair9.services.abstracts.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {

    private final CarRepository carRepository;
    private final ModelService modelService;
    private final ColorService colorService;

    public void isExistCarByPlate(String plate){
        if(carRepository.existsCarByPlate(plate)){
            throw new CarBusinessException(Messages.carAlreadySaved);
        }
    }
    public void isExistCarById(Integer id){
        if(!carRepository.existsById(id)){
            throw new CarBusinessException(Messages.carIsNotFound);
        }
    }

    public void isExistModelById(Integer id){
        if(!modelService.existsModelById(id)){
            throw new CarBusinessException(Messages.modelIsNotFound);
        }
    }
    public void isExistColorById(Integer id){
        if(!colorService.isExistColorById(id)){
            throw new CarBusinessException(Messages.colorIsNotFound);
        }
    }
}
