package com.tobeto.pair9.services.dtos.car.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarRequest {

    @Positive
    private int id;

    @Positive
    private int kilometer;

    private String plate;

    @Min(value=2005)
    @Max(value=2024)
    private int year;

    @Positive
    private double dailyPrice;

    @Positive
    private int modelId;

    @Positive
    private int colorId;
}
