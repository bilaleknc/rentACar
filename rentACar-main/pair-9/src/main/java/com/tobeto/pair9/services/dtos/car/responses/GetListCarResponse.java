package com.tobeto.pair9.services.dtos.car.responses;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListCarResponse {

    private int kilometer;

    private String plate;

    private int year;

    private double dailyPrice;

    private String modelName;

    private String colorName;

}
