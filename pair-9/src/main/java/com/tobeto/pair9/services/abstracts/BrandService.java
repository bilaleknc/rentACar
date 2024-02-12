package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.dtos.brand.requests.AddBrandRequest;
import com.tobeto.pair9.services.dtos.brand.requests.UpdateBrandRequest;
import com.tobeto.pair9.services.dtos.brand.responses.GetByIdBrandResponse;
import com.tobeto.pair9.services.dtos.brand.responses.GetListBrandResponse;

import java.util.List;

public interface BrandService {

    BaseResult<GetByIdBrandResponse> getById(Integer id);

    BaseResult<List<GetListBrandResponse>> getAll();

    BaseResult add(AddBrandRequest request);

    BaseResult update(UpdateBrandRequest request);

    BaseResult delete(Integer id);

    boolean isExistBrandById(Integer id);
}
