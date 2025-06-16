package com.example.invoiceapp.repository;


import com.example.invoiceapp.model.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvoiceTypeRepository extends JpaRepository<InvoiceType, Long> {

}