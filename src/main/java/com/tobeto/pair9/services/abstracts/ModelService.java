package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResponse;
import com.tobeto.pair9.services.dtos.model.requests.AddModelRequest;
import com.tobeto.pair9.services.dtos.model.requests.UpdateModelRequest;
import com.tobeto.pair9.services.dtos.model.responses.GetListModelResponse;

import java.util.List;

public interface ModelService {

    BaseResponse<List<GetListModelResponse>> getAll();

    BaseResponse add(AddModelRequest request);

    BaseResponse update(UpdateModelRequest request);

    BaseResponse delete(int id);

    boolean existsModelById(Integer id);

}
