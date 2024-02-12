package com.tobeto.pair9.services.dtos.corporateCustomer.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListCorporateCustomerResponse {

    private Integer id;

    private String companyName;

    private String taxNo;

    private Integer userId;
}
