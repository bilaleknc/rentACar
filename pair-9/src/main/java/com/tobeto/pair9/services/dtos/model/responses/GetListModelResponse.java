package com.tobeto.pair9.services.dtos.model.responses;

import com.tobeto.pair9.services.dtos.brand.responses.GetListBrandResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListModelResponse {

    private Integer id;

    private String name;

    private GetListBrandResponse brand;
}
