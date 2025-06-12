package com.example.invoiceapp.repository;


import com.example.invoiceapp.model.InvoiceTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceTemplateRepository extends JpaRepository<InvoiceTemplate, Long> {
}

