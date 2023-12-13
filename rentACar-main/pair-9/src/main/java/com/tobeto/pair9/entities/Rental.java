package com.tobeto.pair9.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Table(name = "rentals")
@Entity
@Data



public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "start_date")
    private LocalDate start_date;
    @Column(name = "end_date")
    private LocalDate end_date;
    @Column(name = "return_date")
    private LocalDate return_date;
    @Column(name = "start_kilometer")
    private int start_kilometer;
    @Column(name = "end_kilometer")
    private int end_kilometer;
    @Column(name = "total_price")
    private double total_price;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "rental")
    @JsonIgnore
    private List<Invoices> invoices;
}
