package com.tobeto.pair9.controllers;

import com.tobeto.pair9.services.abstracts.CorporateCustomerService;
import com.tobeto.pair9.services.dtos.corporateCustomer.responses.GetListCorporateCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/corporateCustomers")
public class CorporateCustomersController {
    private CorporateCustomerService corporateCustomerService;
    @GetMapping("/getAll")
    public List<GetListCorporateCustomerResponse> getAll(){
        return corporateCustomerService.getAll();
    }
}
