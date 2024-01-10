package com.tobeto.pair9.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tobeto.pair9.entities.absracts.BaseEntity;
import com.tobeto.pair9.entities.concretes.Rental;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Table(name = "users")
@Entity
@Data

public class User extends BaseEntity {


    @Column(name = "email")
    private String email;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "user")

    private List<Rental> rentals;

}
