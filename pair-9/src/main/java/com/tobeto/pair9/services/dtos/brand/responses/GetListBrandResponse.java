package com.tobeto.pair9.services.dtos.brand.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetListBrandResponse {

    private Integer id;

    private String name;

    private String logoPath;
}
