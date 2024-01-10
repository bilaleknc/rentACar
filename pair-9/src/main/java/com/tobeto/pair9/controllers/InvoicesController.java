package com.tobeto.pair9.controllers;

import com.tobeto.pair9.services.abstracts.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {
    private InvoiceService invoiceService;
}
