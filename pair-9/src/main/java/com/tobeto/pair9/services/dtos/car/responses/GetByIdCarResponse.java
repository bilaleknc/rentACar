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

    private Integer  kilometer;

    private String plate;

    private int year;

    private double dailyPrice;

    private Float depositPrice;

    private short minFindeksRate;

    private String transmissionType;

    private String fuelType;

    private boolean airbag;

    private Integer minDrivingLicenceAge;

    private Integer minCustomerAge;

    private Integer seatCapasity;

    private String imagePath;

    private String  modelName;

    private String colorName;
}
