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
    public BaseResponse<List<GetListCarResponse>> getAll() {
        List<Car> cars = carRepository.findAll();
        var result = cars.stream()
                .map(car->this.modelMapperService.forResponse()
                        .map(car, GetListCarResponse.class)).collect(Collectors.toList());
        return new BaseResponse(true,result);
    }

    @Override
    public BaseResponse<GetByIdCarResponse> getById(Integer id) {
        Car car = this.carRepository.findById(id).orElseThrow();
        var result = this.modelMapperService.forResponse().map(car, GetByIdCarResponse.class);
        return new BaseResponse(false,result);
    }

    @Override
    public BaseResponse add(AddCarRequest request) {
        carBusinessRules.isExistCarByPlate(request.getPlate());
        carBusinessRules.isExistModelById(request.getModelId());
        carBusinessRules.isExistColorById(request.getColorId());

        Car car = this.modelMapperService.forRequest().map(request, Car.class);
        car.setId(null);
        this.carRepository.save(car);
        return new BaseResponse<>(true, Messages.carAdded);
    }

    @Override
    public BaseResponse update(UpdateCarRequest request) {
        carBusinessRules.isExistCarById(request.getId());
        carBusinessRules.isExistModelById(request.getModelId());
        carBusinessRules.isExistColorById(request.getColorId());
        Car car = this.modelMapperService.forRequest()
                .map(request,Car.class);
        this.carRepository.save(car);
        return new BaseResponse<>(true,Messages.carUpdated);
    }

    public boolean isExistById(Integer id) {
        return carRepository.existsById(id);
    }

    @Override
    public BaseResponse delete(Integer id) {
        this.carRepository.deleteById(id);
        return new BaseResponse<>(true,Messages.carDeleted);
    }
}
