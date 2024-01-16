package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.DataResult;
import com.tobeto.pair9.core.utilities.results.Result;
import com.tobeto.pair9.core.utilities.results.SuccessDataResult;
import com.tobeto.pair9.core.utilities.results.SuccessResult;
import com.tobeto.pair9.entities.concretes.Brand;
import com.tobeto.pair9.entities.concretes.Model;
import com.tobeto.pair9.repositories.ModelRepository;
import com.tobeto.pair9.services.abstracts.BrandService;
import com.tobeto.pair9.services.abstracts.ModelService;
import com.tobeto.pair9.services.dtos.brand.responses.GetByIdBrandResponse;
import com.tobeto.pair9.services.dtos.model.requests.AddModelRequest;
import com.tobeto.pair9.services.dtos.model.requests.UpdateModelRequest;
import com.tobeto.pair9.services.dtos.model.responses.GetListModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private BrandService brandService;


    @Override
    public DataResult<List<GetListModelResponse>> getAll() {
        List<Model> models = modelRepository.findAll();
        return new SuccessDataResult(models.stream().map(model -> this.modelMapperService.forResponse().map(model, GetListModelResponse.class))
                .collect(Collectors.toList()),"Tüm veriler eklendi");
    }

    @Override
    public Result add(AddModelRequest request) {
        if(modelRepository.existsByName(request.getName())){
            throw new RuntimeException("There cannot be same model");
        }
        Model model = this.modelMapperService.forRequest().map(request,Model.class);
        this.modelRepository.save(model);
        return new SuccessResult("Eklendi");
    }

    @Override
    public Result update(UpdateModelRequest request) {
        if(modelRepository.existsByName(request.getName())){
            throw new RuntimeException("There cannot be same model");
        }
        Model model = this.modelMapperService.forRequest().map(request,Model.class);
        this.modelRepository.save(model);
        return new SuccessResult("Güncellendi");
    }

    @Override
    public Result delete(int id) {
        this.modelRepository.deleteById(id);
        return new SuccessResult("Silindi");
    }

    @Override
    public boolean existsId(int id) {
        return modelRepository.existsById(id);
    }

}
