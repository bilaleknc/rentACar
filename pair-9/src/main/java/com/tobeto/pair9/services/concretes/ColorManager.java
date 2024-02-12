package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.*;
import com.tobeto.pair9.entities.concretes.Color;
import com.tobeto.pair9.repositories.ColorRepository;
import com.tobeto.pair9.services.abstracts.ColorService;
import com.tobeto.pair9.services.dtos.color.requests.AddColorRequest;
import com.tobeto.pair9.services.dtos.color.requests.UpdateColorRequest;
import com.tobeto.pair9.services.dtos.color.responses.GetListColorResponse;
import com.tobeto.pair9.services.rules.ColorBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ColorManager implements ColorService {
    private final ColorRepository colorRepository;
    private final ModelMapperService modelMapperService;
    private final ColorBusinessRules colorBusinessRules;
    @Override
    public BaseResult<List<GetListColorResponse>> getAll() {
        List<Color> colors = colorRepository.findAll();
        var result = colors.stream().map(color -> this.modelMapperService.forResponse().map(color,GetListColorResponse.class)).toList();
        return new BaseResult<>(true,result);
    }

    @Override
    public BaseResult add(AddColorRequest request) {
        colorBusinessRules.isExistColorByName(request.getName());
        Color color = this.modelMapperService.forRequest().map(request,Color.class);
        color.setId(null);
        this.colorRepository.save(color);
        return new BaseResult<>(true, Messages.colorAdded);
    }

    @Override
    public BaseResult update(UpdateColorRequest request) {
        colorBusinessRules.isExistColorById(request.getId());
        Color color = this.modelMapperService.forRequest().map(request,Color.class);
        this.colorRepository.save(color);
        return new BaseResult<>(true, Messages.colorUpdated);
    }

    @Override
    public BaseResult delete(int id) {
        this.colorRepository.deleteById(id);
        return new BaseResult<>(true,Messages.colorDeleted);
    }

    @Override
    public boolean isExistColorById(Integer id) {
        return colorRepository.existsById(id);
    }
}
