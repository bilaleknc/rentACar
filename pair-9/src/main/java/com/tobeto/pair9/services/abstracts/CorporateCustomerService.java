package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.DataResult;
import com.tobeto.pair9.core.utilities.results.Result;
import com.tobeto.pair9.services.dtos.corporateCustomer.requests.AddCorporateCustomerRequest;
import com.tobeto.pair9.services.dtos.corporateCustomer.requests.UpdateCorporateCustomerRequest;
import com.tobeto.pair9.services.dtos.corporateCustomer.responses.GetListCorporateCustomerResponse;

import java.util.List;

public interface CorporateCustomerService {
    DataResult<List<GetListCorporateCustomerResponse>> getAll();
    Result add(AddCorporateCustomerRequest request);
    Result update(UpdateCorporateCustomerRequest request);
    Result delete(int id);
}
