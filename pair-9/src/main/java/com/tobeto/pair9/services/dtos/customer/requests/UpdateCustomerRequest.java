package com.tobeto.pair9.services.dtos.customer.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {

    private Integer id;

    private String firstName;

    private String lastName;

    private String identityNumber;

    private LocalDate birthDate;

    private String city;

    private String address;

    private String email;

    private LocalDate drivingLicenseDate;

    private String postalCode;

    private String username;
}
