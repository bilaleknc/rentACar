package com.tobeto.pair9.entities.concretes;

import com.tobeto.pair9.entities.absracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "customers")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "driving_License_date")
    private LocalDate drivingLicenseDate;

    @Column(name = "postal_code")
    private String postalCode;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;
}
