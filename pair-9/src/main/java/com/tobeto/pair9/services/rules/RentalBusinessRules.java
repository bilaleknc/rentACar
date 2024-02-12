package com.tobeto.pair9.services.rules;

import com.tobeto.pair9.core.utilities.exceptions.RentalBusinessException;
import com.tobeto.pair9.core.utilities.results.Messages;
import com.tobeto.pair9.repositories.RentalRepository;
import com.tobeto.pair9.services.abstracts.CarService;
import com.tobeto.pair9.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class RentalBusinessRules {

    private final RentalRepository rentalRepository;
    private final CarService carService;
    private final UserService userService;

    public void isExistRentalById(Integer id){
        if(!rentalRepository.existsById(id)){
            throw new RentalBusinessException(Messages.rentalIsNotFound);
        }
    }

    public void isExistCarById(Integer id){
        if(!carService.isExistById(id)){
            throw new RentalBusinessException(Messages.carIsNotFound);
        }
    }

    public void isExistUserById(Integer id){
        if(!userService.isExistUserById(id)){
            throw new RentalBusinessException(Messages.userIsNotFound);
        }
    }

    public boolean calculateDiff(LocalDate startDate, LocalDate endDate){
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        if (daysBetween < 0){
            throw new RentalBusinessException(Messages.dateIsNotCorrect);
        }

        if (daysBetween > 25){
            throw new RentalBusinessException(Messages.countRentDay);
        }
        return true;
    }
}
