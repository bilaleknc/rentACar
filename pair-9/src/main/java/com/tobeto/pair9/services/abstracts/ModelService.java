package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.dtos.model.requests.AddModelRequest;
import com.tobeto.pair9.services.dtos.model.requests.UpdateModelRequest;
import com.tobeto.pair9.services.dtos.model.responses.GetListModelResponse;

import java.util.List;

public interface ModelService {

    BaseResult<List<GetListModelResponse>> getAll();

    BaseResult add(AddModelRequest request);

    BaseResult update(UpdateModelRequest request);

    BaseResult delete(int id);

    boolean existsModelById(Integer id);
    

}
