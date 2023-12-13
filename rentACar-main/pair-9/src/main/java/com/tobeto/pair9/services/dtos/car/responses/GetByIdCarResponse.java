package com.tobeto.pair9.services.dtos.car.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdCarResponse {
    private int id;

    private int  kilometer;

    private String plate;

    private int year;

    private double dailyPrice;

    private String  modelName;

    private String colorName;
}
