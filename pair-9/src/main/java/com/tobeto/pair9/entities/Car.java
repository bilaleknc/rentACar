package com.tobeto.pair9.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "cars")
@Entity
@Data

public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @Column(name = "kilometer")
    private int kilometer;
    @Column(name = "plate")
    private String plate;
    @Column(name = "year")
    private int year;
    @Column(name = "daily_price")
    private double daily_price;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Rental> rentals;

}
