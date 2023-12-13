package com.tobeto.pair9.repositories;

import com.tobeto.pair9.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Integer> {
}
