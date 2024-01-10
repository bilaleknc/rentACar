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
    private LocalDate startDate;

    @FutureOrPresent
    private LocalDate endDate;

    @Nullable
    private LocalDate returnDate;

    @Nullable
    private Long endKilometer;

    private int carId;

    private int userId;

}
