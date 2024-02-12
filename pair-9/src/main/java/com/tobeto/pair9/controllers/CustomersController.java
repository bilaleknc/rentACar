package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.BaseResponse;
import com.tobeto.pair9.services.abstracts.CustomerService;
import com.tobeto.pair9.services.dtos.customer.requests.AddCustomerRequest;
import com.tobeto.pair9.services.dtos.customer.requests.UpdateCustomerRequest;
import com.tobeto.pair9.services.dtos.customer.responses.GetListCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
@CrossOrigin
public class CustomersController {

    private CustomerService customerService;

    @GetMapping("/getAll")
    public BaseResponse<List<GetListCustomerResponse>> getAll(){
        return customerService.getAll();
    }


    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public BaseResponse add(@RequestBody @Valid AddCustomerRequest request){
        return customerService.add(request);
    }


    @PutMapping("/update")
    public BaseResponse update(@RequestBody @Valid UpdateCustomerRequest request){
        return this.customerService.update(request);
    }


    @DeleteMapping("{id}")
    public BaseResponse delete(@PathVariable Integer id){
        return customerService.delete(id);
    }

}
