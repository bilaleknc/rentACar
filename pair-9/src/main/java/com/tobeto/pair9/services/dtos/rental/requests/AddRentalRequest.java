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

    @FutureOrPresent
    private LocalDate start_date;

    @FutureOrPresent
    private LocalDate end_date;

    private int carId;

    private int userId;
}
