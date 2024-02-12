package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.abstracts.RentalService;
import com.tobeto.pair9.services.dtos.rental.requests.AddRentalRequest;
import com.tobeto.pair9.services.dtos.rental.requests.UpdateRentalRequest;
import com.tobeto.pair9.services.dtos.rental.responses.GetListRentalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rentals")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class RentalsController {

    private RentalService rentalService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('admin:read')")
    public BaseResult<List<GetListRentalResponse>> getAll(){
        return rentalService.getAll();
    }


    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin:add')")
    public BaseResult add(@RequestBody @Valid AddRentalRequest request){
        return rentalService.add(request);
    }


    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admin:update')")
    public BaseResult update(@RequestBody @Valid UpdateRentalRequest request){
        return rentalService.update(request);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public BaseResult delete(@PathVariable Integer id){
        return rentalService.delete(id);
    }

}
