package com.tobeto.pair9.controllers;

import com.tobeto.pair9.services.abstracts.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomersController {
    private CustomerService customerService;
}
