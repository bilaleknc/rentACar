package com.tobeto.pair9.services.dtos.brand.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetByIdBrandResponse {

    private Integer id;

    private String name;
}
