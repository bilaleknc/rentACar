package com.tobeto.pair9.services.dtos.rental.requests;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRentalRequest {

    @FutureOrPresent(message = "Start date can't be any later than today!")
    private LocalDate start_date;

    private LocalDate end_date;

    private LocalDate return_date;

    private int start_kilometer;

    private int end_kilometer;

    private double total_price;

    private int carId;

    private int userId;
}
