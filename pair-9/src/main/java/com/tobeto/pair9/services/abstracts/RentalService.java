package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResponse;
import com.tobeto.pair9.services.dtos.rental.requests.AddRentalRequest;
import com.tobeto.pair9.services.dtos.rental.requests.UpdateRentalRequest;
import com.tobeto.pair9.services.dtos.rental.responses.GetListRentalResponse;

import java.util.List;

public interface RentalService {

    BaseResponse<List<GetListRentalResponse>> getAll();

    BaseResponse add(AddRentalRequest request);

    BaseResponse update(UpdateRentalRequest request);

    BaseResponse delete(Integer id);

    boolean isExistRentalById(Integer id);
}
