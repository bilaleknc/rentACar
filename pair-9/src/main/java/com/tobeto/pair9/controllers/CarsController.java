package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.BaseResponse;
import com.tobeto.pair9.services.abstracts.CarService;
import com.tobeto.pair9.services.dtos.car.requests.AddCarRequest;
import com.tobeto.pair9.services.dtos.car.requests.UpdateCarRequest;
import com.tobeto.pair9.services.dtos.car.responses.GetByIdCarResponse;
import com.tobeto.pair9.services.dtos.car.responses.GetListCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cars")
@CrossOrigin
public class CarsController {

    private final CarService carService;


    @GetMapping("/getAll")
    public BaseResponse<List<GetListCarResponse>> getAll(){
        return carService.getAll();
    }


    @GetMapping("/getById/{id}")
    public BaseResponse<GetByIdCarResponse> getById(@PathVariable Integer id){
        return carService.getById(id);
    }


    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public BaseResponse add(@RequestBody @Valid AddCarRequest request){
        return carService.add(request);
    }


    @PutMapping("/update")
    public BaseResponse update(@RequestBody @Valid UpdateCarRequest request){
        return this.carService.update(request);
    }


    @DeleteMapping("{id}")
    public BaseResponse delete(@PathVariable Integer id){
        return carService.delete(id);
    }
}
