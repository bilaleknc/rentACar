package com.tobeto.pair9.services.rules;

import com.tobeto.pair9.core.utilities.exceptions.InvoiceBusinessException;
import com.tobeto.pair9.core.utilities.results.Messages;
import com.tobeto.pair9.repositories.InvoiceRepository;
import com.tobeto.pair9.services.abstracts.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules {

    private final InvoiceRepository invoiceRepository;
    private final RentalService rentalService;

    public void isExistInvoiceByNumber(String invoiveNo){
        if(invoiceRepository.existsInvoiceByInvoiceNo(invoiveNo)){
            throw new InvoiceBusinessException(Messages.invoiceAlreadySaved);
        }
    }

    public void isExistInvoiceById(Integer id){
        if(!invoiceRepository.existsById(id)){
            throw new InvoiceBusinessException(Messages.invoiceIsNotFound);
        }
    }

    public void isExistRentalById(Integer id){
        if(!rentalService.isExistRentalById(id)){
            throw new InvoiceBusinessException(Messages.rentalIsNotFound);
        }
    }
}
