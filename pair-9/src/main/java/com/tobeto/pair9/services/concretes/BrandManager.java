package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.*;
import com.tobeto.pair9.entities.concretes.Brand;
import com.tobeto.pair9.repositories.BrandRepository;
import com.tobeto.pair9.services.abstracts.BrandService;
import com.tobeto.pair9.services.dtos.brand.requests.AddBrandRequest;
import com.tobeto.pair9.services.dtos.brand.requests.UpdateBrandRequest;
import com.tobeto.pair9.services.dtos.brand.responses.GetByIdBrandResponse;
import com.tobeto.pair9.services.dtos.brand.responses.GetListBrandResponse;
import com.tobeto.pair9.services.rules.BrandBusinessRules;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapperService modelMapperService;
    private final BrandBusinessRules brandBusinessRules;

    @Override
    public BaseResult<GetByIdBrandResponse> getById(Integer id) {
        Brand brand = this.brandRepository.findById(id).orElseThrow();
        GetByIdBrandResponse response = this.modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);
        return new BaseResult<>(true, response);
    }

    @Override
    public BaseResult<List<GetListBrandResponse>> getAll() {
        List<Brand> brands = brandRepository.findAll();
        var result =   brands.stream()
                .map(brand -> this.modelMapperService.forResponse().map(brand, GetListBrandResponse.class)).toList();
        return new BaseResult<>(true,result);
    }

    @Override
    public BaseResult add(AddBrandRequest request) {
        brandBusinessRules.isExistBrandByName(request.getName());
       Brand brand = this.modelMapperService.forRequest().map(request,Brand.class);
       brand.setId(null);
        this.brandRepository.save(brand);
        return new BaseResult<>(true, Messages.brandAdded);
    }

    @Override
    public BaseResult update(UpdateBrandRequest request) {
        brandBusinessRules.isExistBrandById(request.getId());
        Brand brand = this.modelMapperService.forRequest().map(request,Brand.class);
        this.brandRepository.save(brand);
        return new BaseResult<>(true, Messages.brandUpdated);
    }

    @Override
    public BaseResult delete(Integer id) {
        this.brandRepository.deleteById(id);
        return new BaseResult<>(true, Messages.brandDeleted);
    }

    @Override
    public boolean isExistBrandById(Integer id) {
        return brandRepository.existsById(id);
    }
}
