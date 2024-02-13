package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.BaseResponse;
import com.tobeto.pair9.core.utilities.results.Messages;
import com.tobeto.pair9.entities.concretes.Customer;
import com.tobeto.pair9.repositories.CustomerRepository;
import com.tobeto.pair9.services.abstracts.CustomerService;
import com.tobeto.pair9.services.abstracts.UserService;
import com.tobeto.pair9.services.dtos.customer.requests.AddCustomerRequest;
import com.tobeto.pair9.services.dtos.customer.requests.UpdateCustomerRequest;
import com.tobeto.pair9.services.dtos.customer.responses.GetListCustomerResponse;
import com.tobeto.pair9.services.rules.CustomerBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;
    private final CustomerBusinessRules customerBusinessRules;
    private final UserService UserService;

    @Override
    public BaseResponse<List<GetListCustomerResponse>> getAll() {
        List<Customer> customers = customerRepository.findAll();
        var result = customers.stream()
                .map(customer -> this.modelMapperService.forResponse()
                        .map(customer,GetListCustomerResponse.class)).toList();
        return new BaseResponse<>(true,result);
    }

    @Override
    public BaseResponse add(AddCustomerRequest request) {
        customerBusinessRules.isExistCustomerByIdentityNumber(request.getIdentityNumber());
        customerBusinessRules.isExistUserByUserName(request.getUsername());
        Customer customer = this.modelMapperService.forRequest().map(request,Customer.class);
        customer.setId(null);
        customer.setUser(UserService.getUserByUsername(request.getUsername()));
        this.customerRepository.save(customer);
        return new BaseResponse<>(true, Messages.customerAdded);
    }

    @Override
    public BaseResponse update(UpdateCustomerRequest request) {
        customerBusinessRules.isExistCustomerById(request.getId());
        customerBusinessRules.isExistUserByUserName(request.getUsername());
        Customer customer = this.modelMapperService.forRequest()
                .map(request,Customer.class);
        this.customerRepository.save(customer);
        return new BaseResponse<>(true, Messages.customerUpdated);
    }

    @Override
    public BaseResponse delete(Integer id) {
        this.customerRepository.deleteById(id);
        return new BaseResponse<>(true, Messages.customerDeleted);
    }

    @Override
    public boolean isExistCustomerById(Integer id) {
        return customerRepository.existsById(id);
    }
}
