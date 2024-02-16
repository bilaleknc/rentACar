package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResponse;
import com.tobeto.pair9.services.dtos.color.requests.AddColorRequest;
import com.tobeto.pair9.services.dtos.color.requests.UpdateColorRequest;
import com.tobeto.pair9.services.dtos.color.responses.GetListColorResponse;

import java.util.List;

public interface ColorService {

    BaseResponse<List<GetListColorResponse>> getAll();

    BaseResponse add(AddColorRequest request);

    BaseResponse update(UpdateColorRequest request);

    BaseResponse delete(int id);

    boolean isExistColorById(Integer id);
}
