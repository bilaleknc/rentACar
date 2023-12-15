package com.tobeto.pair9.repositories;

import com.tobeto.pair9.entities.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoices,Integer> {
}
