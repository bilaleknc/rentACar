package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.dtos.corporateCustomer.requests.AddCorporateCustomerRequest;
import com.tobeto.pair9.services.dtos.corporateCustomer.requests.UpdateCorporateCustomerRequest;
import com.tobeto.pair9.services.dtos.corporateCustomer.responses.GetListCorporateCustomerResponse;

import java.util.List;

public interface CorporateCustomerService {

    BaseResult<List<GetListCorporateCustomerResponse>> getAll();

    BaseResult add(AddCorporateCustomerRequest request);

    BaseResult update(UpdateCorporateCustomerRequest request);

    BaseResult delete(Integer id);

    boolean isExistCorporateCustomerById(Integer id);
}
