package com.tobeto.pair9.services.abstracts;


import com.tobeto.pair9.core.utilities.results.DataResult;
import com.tobeto.pair9.core.utilities.results.Result;
import com.tobeto.pair9.services.dtos.color.requests.AddColorRequest;
import com.tobeto.pair9.services.dtos.color.requests.UpdateColorRequest;
import com.tobeto.pair9.services.dtos.color.responses.GetListColorResponse;
import com.tobeto.pair9.services.dtos.model.requests.AddModelRequest;
import com.tobeto.pair9.services.dtos.model.requests.UpdateModelRequest;
import com.tobeto.pair9.services.dtos.model.responses.GetListModelResponse;

import javax.xml.crypto.Data;
import java.util.List;

public interface ModelService {
    DataResult<List<GetListModelResponse>> getAll();
    Result add(AddModelRequest request);
    Result update(UpdateModelRequest request);
    Result delete(int id);
    boolean existsId(int id);
    

}
