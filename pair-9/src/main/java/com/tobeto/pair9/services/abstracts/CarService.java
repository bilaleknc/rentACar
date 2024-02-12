package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResponse;
import com.tobeto.pair9.services.dtos.car.requests.AddCarRequest;
import com.tobeto.pair9.services.dtos.car.requests.UpdateCarRequest;
import com.tobeto.pair9.services.dtos.car.responses.GetByIdCarResponse;
import com.tobeto.pair9.services.dtos.car.responses.GetListCarResponse;

import java.util.List;

public interface CarService{

    BaseResponse<List<GetListCarResponse>> getAll();

    BaseResponse<GetByIdCarResponse> getById(Integer id);

    BaseResponse add(AddCarRequest request);

    BaseResponse update(UpdateCarRequest request);

    BaseResponse delete(Integer id);

    boolean isExistById(Integer id);

}
