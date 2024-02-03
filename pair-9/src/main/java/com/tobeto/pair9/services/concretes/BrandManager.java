package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.DataResult;
import com.tobeto.pair9.core.utilities.results.Result;
import com.tobeto.pair9.core.utilities.results.SuccessDataResult;
import com.tobeto.pair9.core.utilities.results.SuccessResult;
import com.tobeto.pair9.entities.concretes.Brand;
import com.tobeto.pair9.repositories.BrandRepository;
import com.tobeto.pair9.services.abstracts.BrandService;
import com.tobeto.pair9.services.dtos.brand.requests.AddBrandRequest;
import com.tobeto.pair9.services.dtos.brand.requests.UpdateBrandRequest;
import com.tobeto.pair9.services.dtos.brand.responses.GetByIdBrandResponse;
import com.tobeto.pair9.services.dtos.brand.responses.GetListBrandResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;

    @Override
    public DataResult<GetByIdBrandResponse> getById(int id) {
        Brand brand = this.brandRepository.findById(id).orElseThrow();
        GetByIdBrandResponse response = this.modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);

        return new SuccessDataResult(response, "Id ile getirildi");
    }

    @Override
    public DataResult<List<GetListBrandResponse>> getAll() {
        List<Brand> brands = brandRepository.findAll();
        return new SuccessDataResult(brands.stream()
                .map(brand -> this.modelMapperService.forResponse().map(brand, GetListBrandResponse.class)).toList(),
                "Tüm data getirildi");
    }

    @Override
    public Result add(AddBrandRequest request) {
        if(brandRepository.existsByName(request.getName())){
            throw new RuntimeException("There cannot be same brand");
        }
       Brand brand = this.modelMapperService.forRequest().map(request,Brand.class);
        this.brandRepository.save(brand);
        return new SuccessResult("Eklendi");
    }

    @Override
    public Result update(UpdateBrandRequest request) {
        if(brandRepository.existsByName(request.getName())){
            throw new RuntimeException("There cannot be same brand");
        }
        Brand brand = this.modelMapperService.forRequest().map(request,Brand.class);
        this.brandRepository.save(brand);
        return new SuccessResult("Güncellendi");
    }

    @Override
    public Result delete(int id) {
        this.brandRepository.deleteById(id);
        return new SuccessResult("Silindi");
    }
}
