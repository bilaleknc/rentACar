package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.*;
import com.tobeto.pair9.entities.concretes.Car;
import com.tobeto.pair9.repositories.CarRepository;
import com.tobeto.pair9.services.abstracts.CarService;
import com.tobeto.pair9.services.dtos.car.requests.AddCarRequest;
import com.tobeto.pair9.services.dtos.car.requests.UpdateCarRequest;
import com.tobeto.pair9.services.dtos.car.responses.GetByIdCarResponse;
import com.tobeto.pair9.services.dtos.car.responses.GetListCarResponse;
import com.tobeto.pair9.services.rules.CarBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private final CarRepository carRepository;
    private final ModelMapperService modelMapperService;
    private final CarBusinessRules carBusinessRules;

    @Override
    public BaseResult<List<GetListCarResponse>> getAll() {
        List<Car> cars = carRepository.findAll();
        var result = cars.stream()
                .map(car->this.modelMapperService.forResponse()
                        .map(car, GetListCarResponse.class)).collect(Collectors.toList());
        return new BaseResult(true,result);
    }

    @Override
    public BaseResult<GetByIdCarResponse> getById(Integer id) {
        Car car = this.carRepository.findById(id).orElseThrow();
         var result = this.modelMapperService.forResponse().map(car, GetByIdCarResponse.class);
         return new BaseResult(false,result);
    }

    @Override
    public BaseResult add(AddCarRequest request) {
        carBusinessRules.isExistCarByPlate(request.getPlate());
        carBusinessRules.isExistModelById(request.getModelId());
        carBusinessRules.isExistColorById(request.getColorId());

        Car car = this.modelMapperService.forRequest().map(request, Car.class);
        car.setId(null);
        this.carRepository.save(car);
        return new BaseResult<>(true, Messages.carAdded);
    }

    @Override
    public BaseResult update(UpdateCarRequest request) {
        carBusinessRules.isExistCarById(request.getId());
        carBusinessRules.isExistModelById(request.getModelId());
        carBusinessRules.isExistColorById(request.getColorId());
        Car car = this.modelMapperService.forRequest()
                .map(request,Car.class);
        this.carRepository.save(car);
        return new BaseResult<>(true,Messages.carUpdated);
    }

    public boolean isExistById(Integer id) {
        return carRepository.existsById(id);
    }

    @Override
    public BaseResult delete(Integer id) {
        this.carRepository.deleteById(id);
        return new BaseResult<>(true,Messages.carDeleted);
    }
}
