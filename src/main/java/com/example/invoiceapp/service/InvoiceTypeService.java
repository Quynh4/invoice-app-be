package com.example.invoiceapp.service;

import com.example.invoiceapp.model.InvoiceType;

import java.util.List;
import java.util.Optional;

public interface InvoiceTypeService {
    List<InvoiceType> getAllInvoices();
    Optional<InvoiceType> getInvoiceById(Long id);

}
