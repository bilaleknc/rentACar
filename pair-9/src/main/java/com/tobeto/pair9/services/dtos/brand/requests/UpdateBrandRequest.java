package com.tobeto.pair9.services.dtos.brand.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateBrandRequest {
    @Positive(message = "Id must be greater than zero")
    private int id;

    @Length(min=2,message = "The brand name must be at least 2 character")
    private String name;
}
