package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.DataResult;
import com.tobeto.pair9.core.utilities.results.Result;
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

    private CarService carService;

    @GetMapping("/getAll")
    public DataResult<List<GetListCarResponse>> getAll(){
        return carService.getAll();
    }
    @GetMapping("/getById/{id}")
    public DataResult<GetByIdCarResponse> getById(@PathVariable int id){
        return carService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result add(@RequestBody @Valid AddCarRequest request){
        return carService.add(request);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCarRequest request){
        return this.carService.update(request);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable int id){
        return carService.delete(id);
    }
}
