package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.dtos.rental.requests.AddRentalRequest;
import com.tobeto.pair9.services.dtos.rental.requests.UpdateRentalRequest;
import com.tobeto.pair9.services.dtos.rental.responses.GetListRentalResponse;

import java.util.List;

public interface RentalService {

    BaseResult<List<GetListRentalResponse>> getAll();

    BaseResult add(AddRentalRequest request);

    BaseResult update(UpdateRentalRequest request);

    BaseResult delete(Integer id);

    boolean isExistRentalById(Integer id);
}
