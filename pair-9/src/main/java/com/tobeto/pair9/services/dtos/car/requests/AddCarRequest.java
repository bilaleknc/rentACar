package com.tobeto.pair9.services.dtos.car.requests;

import com.tobeto.pair9.entities.Model;
import jakarta.persistence.Id;
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

    @Pattern(regexp =  "^(0[1-9]|[1-8][0-9]|9[0-8])[A-Z\\s]{1,3}\\d{2,4}$", message = "Invalid plate format!!")
    private String plate;

    public void setPlate(String plate) {
        this.plate = plate != null ? plate.replaceAll("\s", "") : null;
    }

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