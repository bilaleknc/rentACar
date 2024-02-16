package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.*;
import com.tobeto.pair9.entities.concretes.Model;
import com.tobeto.pair9.repositories.ModelRepository;
import com.tobeto.pair9.services.abstracts.ModelService;
import com.tobeto.pair9.services.dtos.model.requests.AddModelRequest;
import com.tobeto.pair9.services.dtos.model.requests.UpdateModelRequest;
import com.tobeto.pair9.services.dtos.model.responses.GetListModelResponse;
import com.tobeto.pair9.services.rules.ModelBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapperService modelMapperService;
    private final ModelBusinessRules modelBusinessRules;

    @Override
    public BaseResponse<List<GetListModelResponse>> getAll() {
        List<Model> models = modelRepository.findAll();
        var result = models.stream().map(model -> this.modelMapperService.forResponse().map(model, GetListModelResponse.class)).toList();
        return new BaseResponse<>(true,result);
    }

    @Override
    public BaseResponse add(AddModelRequest request) {
        modelBusinessRules.isExistModelByName(request.getName());
        modelBusinessRules.isExistBrandById(request.getBrandId());
        Model model = this.modelMapperService.forRequest().map(request,Model.class);
        model.setId(null);
        this.modelRepository.save(model);
        return new BaseResponse<>(true, Messages.modelAdded);
    }

    @Override
    public BaseResponse update(UpdateModelRequest request) {
        modelBusinessRules.isExistModelById(request.getId());
        modelBusinessRules.isExistBrandById(request.getBrandId());
        Model model = this.modelMapperService.forRequest().map(request,Model.class);
        this.modelRepository.save(model);
        return new BaseResponse<>(true, Messages.modelUpdated);
    }

    @Override
    public BaseResponse delete(int id) {
        this.modelRepository.deleteById(id);
        return new BaseResponse<>(true, Messages.modelDeleted);
    }

    @Override
    public boolean existsModelById(Integer id) {
        return modelRepository.existsById(id);
    }
}
