package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.dtos.color.requests.AddColorRequest;
import com.tobeto.pair9.services.dtos.color.requests.UpdateColorRequest;
import com.tobeto.pair9.services.dtos.color.responses.GetListColorResponse;

import java.util.List;

public interface ColorService {

    BaseResult<List<GetListColorResponse>> getAll();

    BaseResult add(AddColorRequest request);

    BaseResult update(UpdateColorRequest request);

    BaseResult delete(int id);

    boolean isExistColorById(Integer id);
}
