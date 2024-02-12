package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.dtos.car.requests.AddCarRequest;
import com.tobeto.pair9.services.dtos.car.requests.UpdateCarRequest;
import com.tobeto.pair9.services.dtos.car.responses.GetByIdCarResponse;
import com.tobeto.pair9.services.dtos.car.responses.GetListCarResponse;

import java.util.List;

public interface CarService{

    BaseResult<List<GetListCarResponse>> getAll();

    BaseResult<GetByIdCarResponse> getById(Integer id);

    BaseResult add(AddCarRequest request);

    BaseResult update(UpdateCarRequest request);

    BaseResult delete(Integer id);

    boolean isExistById(Integer id);

}
