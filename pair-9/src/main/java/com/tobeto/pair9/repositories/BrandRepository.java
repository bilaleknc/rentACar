package com.tobeto.pair9.repositories;

import com.tobeto.pair9.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Integer> {

    boolean existsByName(String name);
}
