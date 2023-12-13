package com.tobeto.pair9.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Table(name = "invoices")
@Entity
@Data


public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "create_date")
    private LocalDate create_date;
    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

}
