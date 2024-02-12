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
    public BaseResult<List<GetListInvoiceResponse>> getAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        var result = invoices.stream()
                .map(invoice -> this.modelMapperService.forResponse()
                        .map(invoice,GetListInvoiceResponse.class)).toList();
        return new BaseResult<>(true,result);
    }

    @Override
    public BaseResult add(AddInvoiceRequest request) {
        invoiceBusinessRules.isExistInvoiceByNumber(request.getInvoiceNo());
        invoiceBusinessRules.isExistRentalById(request.getRentalId());
        Invoice invoice = this.modelMapperService.forRequest().map(request, Invoice.class);
        invoice.setId(null);
        this.invoiceRepository.save(invoice);
        return new BaseResult<>(true, Messages.invoiceAdded);
    }

    @Override
    public BaseResult update(UpdateInvoiceRequest request) {
        invoiceBusinessRules.isExistInvoiceById(request.getId());
        invoiceBusinessRules.isExistRentalById(request.getRentalId());
        Invoice invoice = this.modelMapperService.forRequest()
                .map(request,Invoice.class);
        this.invoiceRepository.save(invoice);
        return new BaseResult<>(true, Messages.invoiceUpdated);
    }

    @Override
    public BaseResult delete(Integer id) {
        this.invoiceRepository.deleteById(id);
        return new BaseResult<>(true, Messages.invoiceDeleted);
    }

    @Override
    public boolean isExistInvoiceById(Integer id) {
        return invoiceRepository.existsById(id);
    }
}
