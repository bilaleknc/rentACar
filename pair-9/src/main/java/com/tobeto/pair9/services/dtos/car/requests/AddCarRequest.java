package com.tobeto.pair9.services.dtos.car.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCarRequest {

    @Positive
    private int kilometer;

    @NotBlank(message = "Plate cannot be null!")
    @Pattern(regexp = "^(0[1-9]|[1-8][0-9]|9[0-8])[A-Z\s]{1,3}\\d{2,4}$", message = "Invalid Turkish license plate format!")
    private String plate;

    public void setPlate(String plate) {
        this.plate = plate != null ? plate.replaceAll("\s", "") : null;
    }

    @Min(value=2005)
    @Max(value=2024)
    private int year;

    @Positive
    private double dailyPrice;

    @Positive
    private Float depositPrice;

    private short minFindeksRate;

    private String transmissionType;

    private String fuelType;

    private boolean airbag;

    private Integer minDrivingLicenceAge;

    @Positive
    private Integer minCustomerAge;

    private Integer seatCapasity;

    private String imagePath;

    @Positive
    private Integer modelId;

    @Positive
    private Integer colorId;
}
