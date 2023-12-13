package com.tobeto.pair9.controllers;

import com.tobeto.pair9.entities.Car;
import com.tobeto.pair9.services.abstracts.BrandService;
import com.tobeto.pair9.services.abstracts.CarService;
import com.tobeto.pair9.services.dtos.car.requests.AddCarRequest;
import com.tobeto.pair9.services.dtos.car.responses.GetListCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class carController {

    private CarService carService;

    @PostMapping("/add")
    public void add(@RequestBody @Valid AddCarRequest request){
        carService.add(request);
    }

    @GetMapping("/getAll")
    public List<GetListCarResponse> getAll(){
        return carService.getAll();
    }


}
