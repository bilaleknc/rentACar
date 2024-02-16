package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.BaseResponse;
import com.tobeto.pair9.services.abstracts.RentalService;
import com.tobeto.pair9.services.dtos.rental.requests.AddRentalRequest;
import com.tobeto.pair9.services.dtos.rental.requests.UpdateRentalRequest;
import com.tobeto.pair9.services.dtos.rental.responses.GetListRentalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rentals")
@CrossOrigin
public class RentalsController {

    private RentalService rentalService;

    @GetMapping("/getAll")
    public BaseResponse<List<GetListRentalResponse>> getAll(){
        return rentalService.getAll();
    }


    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public BaseResponse add(@RequestBody @Valid AddRentalRequest request){
        return rentalService.add(request);
    }


    @PutMapping("/update")
    public BaseResponse update(@RequestBody @Valid UpdateRentalRequest request){
        return rentalService.update(request);
    }


    @DeleteMapping("{id}")
    public BaseResponse delete(@PathVariable Integer id){
        return rentalService.delete(id);
    }

}
