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

    private int id;

    private LocalDate start_date;

    private LocalDate end_date;

    private LocalDate return_date;

    private int start_kilometer;

    private int end_kilometer;

    private double total_price;

    private int carId;

    private int userId;
}
