package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResponse;
import com.tobeto.pair9.services.dtos.customer.requests.AddCustomerRequest;
import com.tobeto.pair9.services.dtos.customer.requests.UpdateCustomerRequest;
import com.tobeto.pair9.services.dtos.customer.responses.GetListCustomerResponse;

import java.util.List;

public interface CustomerService {

    BaseResponse<List<GetListCustomerResponse>> getAll();

    BaseResponse add(AddCustomerRequest request);

    BaseResponse update(UpdateCustomerRequest request);

    BaseResponse delete(Integer id);

    boolean isExistCustomerById(Integer id);

}
