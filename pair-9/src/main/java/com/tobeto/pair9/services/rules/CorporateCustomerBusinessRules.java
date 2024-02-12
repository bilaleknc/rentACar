package com.tobeto.pair9.services.rules;

import com.tobeto.pair9.core.utilities.exceptions.CorporateCustomerBusinessException;
import com.tobeto.pair9.core.utilities.results.Messages;
import com.tobeto.pair9.repositories.CorporateCustomerRepository;
import com.tobeto.pair9.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CorporateCustomerBusinessRules {

    private final CorporateCustomerRepository corporateCustomerRepository;
    private final UserService userService;

    public void isExistCorporateCustomerByName(String name){
        if(corporateCustomerRepository.existsCorporateCustomerByCompanyName(name)){
            throw new CorporateCustomerBusinessException(Messages.companyAlreadySaved);
        }
    }

    public void isExistCorporateCustomerById(Integer id){
        if(!corporateCustomerRepository.existsById(id)){
            throw new CorporateCustomerBusinessException(Messages.companyIsNotFound);
        }
    }

    public void isExistUserById(Integer id){
        if(!userService.isExistUserById(id)){
            throw new CorporateCustomerBusinessException(Messages.userIsNotFound);
        }
    }
}
