package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.dtos.customer.requests.AddCustomerRequest;
import com.tobeto.pair9.services.dtos.customer.requests.UpdateCustomerRequest;
import com.tobeto.pair9.services.dtos.customer.responses.GetListCustomerResponse;

import java.util.List;

public interface CustomerService {

    BaseResult<List<GetListCustomerResponse>> getAll();

    BaseResult add(AddCustomerRequest request);

    BaseResult update(UpdateCustomerRequest request);

    BaseResult delete(Integer id);

    boolean isExistCustomerById(Integer id);

}
