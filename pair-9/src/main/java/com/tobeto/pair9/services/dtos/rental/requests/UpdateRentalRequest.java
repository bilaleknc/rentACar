package com.tobeto.pair9.services.dtos.rental.requests;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UpdateRentalRequest {

    private int id;

    @FutureOrPresent
    private LocalDate start_date;

    @FutureOrPresent
    private LocalDate end_date;

    @Nullable
    private LocalDate return_date;

    @Nullable
    private int end_kilometer;

    private double total_price;

    private int carId;

    private int userId;

}
