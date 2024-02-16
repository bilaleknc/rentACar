package com.tobeto.pair9.services.dtos.invoice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddInvoiceRequest {

    private String cardNameSurname;

    private String cardNumber;

    private LocalDate expireDate;

    private String cvv;

    private Float totalPrice;

    private Integer rentalId;

}
