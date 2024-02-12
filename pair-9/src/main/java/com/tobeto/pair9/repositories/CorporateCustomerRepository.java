package com.tobeto.pair9.repositories;

import com.tobeto.pair9.entities.concretes.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateCustomerRepository extends JpaRepository<CorporateCustomer,Integer> {

    boolean existsCorporateCustomerByCompanyName(String name);

}
