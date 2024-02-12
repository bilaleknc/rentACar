package com.tobeto.pair9.services.rules;

import com.tobeto.pair9.core.utilities.exceptions.CustomerBusinessException;
import com.tobeto.pair9.core.utilities.results.Messages;
import com.tobeto.pair9.repositories.CustomerRepository;
import com.tobeto.pair9.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {

    private final CustomerRepository customerRepository;
    private final UserService userService;

    public void isExistCustomerByIdentityNumber(String identityNumber){
        if(customerRepository.existsCustomerByIdentityNumber(identityNumber)){
            throw new CustomerBusinessException(Messages.customerAlreadySaved);
        }

    }
    public void isExistCustomerById(Integer id){
        if(!customerRepository.existsById(id)){
            throw new CustomerBusinessException(Messages.customerIsNotFound);
        }
    }

    public void isExistUserById(Integer id){
        if(!userService.isExistUserById(id)){
            throw new CustomerBusinessException(Messages.userIsNotFound);
        }
    }
}
