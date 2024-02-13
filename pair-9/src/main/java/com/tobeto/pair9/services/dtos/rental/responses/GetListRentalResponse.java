package com.tobeto.pair9.services.dtos.rental.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListRentalResponse {

    private Integer id;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate returnDate;

    private Long startKilometer;

    private Long endKilometer;

    private Integer carId;

    private Integer userId;
}
