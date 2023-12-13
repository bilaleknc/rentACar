package com.tobeto.pair9.services.dtos.car.requests;

import com.tobeto.pair9.entities.Model;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCarRequest {

    @Positive
    private int kilometer;

    private String plate;

    @Min(value=2005)
    @Max(value=2014)
    private int year;

    @Positive
    private double dailyPrice;

    @Positive
    private int modelId;

    @Positive
    private int colorId;
}
