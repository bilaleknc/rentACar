package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.abstracts.CorporateCustomerService;
import com.tobeto.pair9.services.dtos.corporateCustomer.requests.AddCorporateCustomerRequest;
import com.tobeto.pair9.services.dtos.corporateCustomer.requests.UpdateCorporateCustomerRequest;
import com.tobeto.pair9.services.dtos.corporateCustomer.responses.GetListCorporateCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/corporateCustomers")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class CorporateCustomersController {

    private CorporateCustomerService corporateCustomerService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('admin:read')")
    public BaseResult<List<GetListCorporateCustomerResponse>> getAll(){
        return corporateCustomerService.getAll();
    }


    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin:add')")
    public void add(@RequestBody @Valid AddCorporateCustomerRequest request){
        corporateCustomerService.add(request);
    }


    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admin:update')")
    public void update(@RequestBody @Valid UpdateCorporateCustomerRequest request){
        this.corporateCustomerService.update(request);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public void delete(@PathVariable Integer id){
        corporateCustomerService.delete(id);
    }


}
