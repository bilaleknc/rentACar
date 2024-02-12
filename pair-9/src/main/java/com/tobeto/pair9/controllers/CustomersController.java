package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.abstracts.CustomerService;
import com.tobeto.pair9.services.dtos.customer.requests.AddCustomerRequest;
import com.tobeto.pair9.services.dtos.customer.requests.UpdateCustomerRequest;
import com.tobeto.pair9.services.dtos.customer.responses.GetListCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class CustomersController {

    private CustomerService customerService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('admin:read')")
    public BaseResult<List<GetListCustomerResponse>> getAll(){
        return customerService.getAll();
    }


    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin:add')")
    public void add(@RequestBody @Valid AddCustomerRequest request){
        customerService.add(request);
    }


    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admin:update')")
    public void update(@RequestBody @Valid UpdateCustomerRequest request){
        this.customerService.update(request);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public void delete(@PathVariable Integer id){
        customerService.delete(id);
    }

}
