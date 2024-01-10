package com.tobeto.pair9.repositories;

import com.tobeto.pair9.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {


    boolean existsCarByPlate (String plate);


}
