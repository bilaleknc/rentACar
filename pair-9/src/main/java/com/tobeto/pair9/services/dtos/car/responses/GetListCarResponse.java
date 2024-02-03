package com.tobeto.pair9.services.dtos.car.responses;

import com.tobeto.pair9.services.dtos.color.responses.GetListColorResponse;
import com.tobeto.pair9.services.dtos.model.responses.GetListModelResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListCarResponse {

    private Long kilometer;

    private String plate;

    private short modelYear;

    private Float dailyPrice;

    private short minFindeksRate;

    private String imagePath;

    private GetListModelResponse model;

    private GetListColorResponse color;

}
