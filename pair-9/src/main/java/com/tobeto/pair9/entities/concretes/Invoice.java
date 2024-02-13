package com.tobeto.pair9.entities.concretes;

import com.tobeto.pair9.entities.absracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "invoices")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice extends BaseEntity {

    @Column(name="invoice_no")
    private String invoiceNo;

    @Column(name ="card_name_surname")
    private String cardNameSurname;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Column(name = "cvv")
    private String cvv;

    @Column(name="total_price")
    private Float totalPrice;

    @ManyToOne
    @JoinColumn(name="rental_id")
    private Rental rental;

}
