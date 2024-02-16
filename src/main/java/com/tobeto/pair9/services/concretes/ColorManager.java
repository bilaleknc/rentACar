package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.BaseResponse;
import com.tobeto.pair9.core.utilities.results.Messages;
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
    public BaseResponse<List<GetListColorResponse>> getAll() {
        List<Color> colors = colorRepository.findAll();
        var result = colors.stream().map(color -> this.modelMapperService.forResponse().map(color,GetListColorResponse.class)).toList();
        return new BaseResponse<>(true,result);
    }

    @Override
    public BaseResponse add(AddColorRequest request) {
        colorBusinessRules.isExistColorByName(request.getName());
        Color color = this.modelMapperService.forRequest().map(request,Color.class);
        color.setId(null);
        this.colorRepository.save(color);
        return new BaseResponse<>(true, Messages.colorAdded);
    }

    @Override
    public BaseResponse update(UpdateColorRequest request) {
        colorBusinessRules.isExistColorById(request.getId());
        Color color = this.modelMapperService.forRequest().map(request,Color.class);
        this.colorRepository.save(color);
        return new BaseResponse<>(true, Messages.colorUpdated);
    }

    @Override
    public BaseResponse delete(int id) {
        this.colorRepository.deleteById(id);
        return new BaseResponse<>(true,Messages.colorDeleted);
    }

    @Override
    public boolean isExistColorById(Integer id) {
        return colorRepository.existsById(id);
    }
}
