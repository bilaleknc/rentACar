package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.DataResult;
import com.tobeto.pair9.core.utilities.results.Result;
import com.tobeto.pair9.core.utilities.results.SuccessDataResult;
import com.tobeto.pair9.core.utilities.results.SuccessResult;
import com.tobeto.pair9.entities.concretes.Color;
import com.tobeto.pair9.repositories.ColorRepository;
import com.tobeto.pair9.services.abstracts.ColorService;
import com.tobeto.pair9.services.dtos.color.requests.AddColorRequest;
import com.tobeto.pair9.services.dtos.color.requests.UpdateColorRequest;
import com.tobeto.pair9.services.dtos.color.responses.GetListColorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColorManager implements ColorService {
    private ColorRepository colorRepository;
    private ModelMapperService modelMapperService;

    @Override
    public DataResult<List<GetListColorResponse>> getAll() {
        List<Color> colors = colorRepository.findAll();
        return new SuccessDataResult(colors.stream().map(color -> this.modelMapperService.forResponse().map(color,GetListColorResponse.class))
                .collect(Collectors.toList()), "Tüm data getirildi");
    }

    @Override
    public Result add(AddColorRequest request) {
        if(colorRepository.existsByName(request.getName())){
            throw new RuntimeException("There cannot be same color");
        }
        Color color = this.modelMapperService.forRequest().map(request,Color.class);
        this.colorRepository.save(color);
        return new SuccessResult("Eklendi");
    }

    @Override
    public Result update(UpdateColorRequest request) {
        if(colorRepository.existsByName(request.getName())){
            throw new RuntimeException("There cannot be same color");
        }
        Color color = this.modelMapperService.forRequest().map(request,Color.class);
        this.colorRepository.save(color);
        return new SuccessResult("Gncellendi");
    }

    @Override
    public Result delete(int id) {
        this.colorRepository.deleteById(id);
        return new SuccessResult("Silindi");
    }

    @Override
    public boolean existsId(int id) {
        return colorRepository.existsById(id);
    }
}
