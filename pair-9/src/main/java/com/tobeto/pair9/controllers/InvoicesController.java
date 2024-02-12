package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.abstracts.InvoiceService;
import com.tobeto.pair9.services.dtos.invoice.requests.AddInvoiceRequest;
import com.tobeto.pair9.services.dtos.invoice.requests.UpdateInvoiceRequest;
import com.tobeto.pair9.services.dtos.invoice.responses.GetListInvoiceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/invoices")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class InvoicesController {
    private InvoiceService invoiceService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('admin:read')")
    public BaseResult<List<GetListInvoiceResponse>> getAll(){
        return invoiceService.getAll();
    }


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin:add')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddInvoiceRequest request){
        invoiceService.add(request);
    }


    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admin:update')")
    public void update(@RequestBody @Valid UpdateInvoiceRequest request){
        this.invoiceService.update(request);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public void delete(@PathVariable Integer id){
        invoiceService.delete(id);
    }


}
