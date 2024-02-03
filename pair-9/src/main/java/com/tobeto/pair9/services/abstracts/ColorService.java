package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.DataResult;
import com.tobeto.pair9.core.utilities.results.Result;
import com.tobeto.pair9.services.dtos.color.requests.AddColorRequest;
import com.tobeto.pair9.services.dtos.color.requests.UpdateColorRequest;
import com.tobeto.pair9.services.dtos.color.responses.GetListColorResponse;

import java.util.List;

public interface ColorService {
    DataResult<List<GetListColorResponse>> getAll();
    Result add(AddColorRequest request);
    Result update(UpdateColorRequest request);
    Result delete(int id);

    boolean existsId(int id);
}
