package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.abstracts.CarService;
import com.tobeto.pair9.services.dtos.car.requests.AddCarRequest;
import com.tobeto.pair9.services.dtos.car.requests.UpdateCarRequest;
import com.tobeto.pair9.services.dtos.car.responses.GetByIdCarResponse;
import com.tobeto.pair9.services.dtos.car.responses.GetListCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cars")
@CrossOrigin
@PreAuthorize("hasRole('ADMIN') and hasRole('USER')")
public class CarsController {

    private final CarService carService;


    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('admin:read') and hasRole('USER')")
    public BaseResult<List<GetListCarResponse>> getAll(){
        return carService.getAll();
    }


    @GetMapping("/getById/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public BaseResult<GetByIdCarResponse> getById(@PathVariable Integer id){
        return carService.getById(id);
    }


    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin:add')")
    public BaseResult add(@RequestBody @Valid AddCarRequest request){
        return carService.add(request);
    }


    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admin:update')")
    public BaseResult update(@RequestBody @Valid UpdateCarRequest request){
        return this.carService.update(request);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public BaseResult delete(@PathVariable Integer id){
        return carService.delete(id);
    }
}
