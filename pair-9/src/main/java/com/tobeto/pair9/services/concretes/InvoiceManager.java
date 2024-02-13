package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.*;
import com.tobeto.pair9.entities.concretes.Invoice;
import com.tobeto.pair9.repositories.InvoiceRepository;
import com.tobeto.pair9.services.abstracts.InvoiceService;
import com.tobeto.pair9.services.dtos.invoice.requests.AddInvoiceRequest;
import com.tobeto.pair9.services.dtos.invoice.requests.UpdateInvoiceRequest;
import com.tobeto.pair9.services.dtos.invoice.responses.GetListInvoiceResponse;

import com.tobeto.pair9.services.rules.InvoiceBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ModelMapperService modelMapperService;
    private final InvoiceBusinessRules invoiceBusinessRules;

    @Override
    public BaseResponse<List<GetListInvoiceResponse>> getAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        var result = invoices.stream()
                .map(invoice -> this.modelMapperService.forResponse()
                        .map(invoice,GetListInvoiceResponse.class)).toList();
        return new BaseResponse<>(true,result);
    }

    @Override
    public BaseResponse add(AddInvoiceRequest request) {
        invoiceBusinessRules.isExistRentalById(request.getRentalId());
        Invoice invoice = this.modelMapperService.forRequest().map(request, Invoice.class);
        invoice.setId(null);
        invoice.setInvoiceNo(invoiceBusinessRules.isExistInvoiceByNumber());
        this.invoiceRepository.save(invoice);
        return new BaseResponse<>(true, Messages.invoiceAdded);
    }

    @Override
    public BaseResponse update(UpdateInvoiceRequest request) {
        invoiceBusinessRules.isExistInvoiceById(request.getId());
        invoiceBusinessRules.isExistRentalById(request.getRentalId());
        Invoice invoice = this.modelMapperService.forRequest()
                .map(request,Invoice.class);
        invoice.setInvoiceNo(invoiceBusinessRules.isExistInvoiceByNumber());
        this.invoiceRepository.save(invoice);
        return new BaseResponse<>(true, Messages.invoiceUpdated);
    }

    @Override
    public BaseResponse delete(Integer id) {
        this.invoiceRepository.deleteById(id);
        return new BaseResponse<>(true, Messages.invoiceDeleted);
    }

    @Override
    public boolean isExistInvoiceById(Integer id) {
        return invoiceRepository.existsById(id);
    }
}
