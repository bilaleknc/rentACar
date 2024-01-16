package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.DataResult;
import com.tobeto.pair9.core.utilities.results.Result;
import com.tobeto.pair9.entities.concretes.Brand;
import com.tobeto.pair9.services.dtos.brand.requests.AddBrandRequest;
import com.tobeto.pair9.services.dtos.brand.requests.UpdateBrandRequest;
import com.tobeto.pair9.services.dtos.brand.responses.GetByIdBrandResponse;
import com.tobeto.pair9.services.dtos.brand.responses.GetListBrandResponse;

import java.util.List;

public interface BrandService {

    DataResult<GetByIdBrandResponse> getById(int id);

    DataResult<List<GetListBrandResponse>> getAll();

    Result add(AddBrandRequest request);

    Result update(UpdateBrandRequest request);

    Result delete(int id);
}
