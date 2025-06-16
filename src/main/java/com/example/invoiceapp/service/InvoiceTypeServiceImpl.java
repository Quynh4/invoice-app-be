package com.example.invoiceapp.service;


import com.example.invoiceapp.model.InvoiceType;
import com.example.invoiceapp.repository.InvoiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceTypeServiceImpl implements InvoiceTypeService {

    @Autowired
    private InvoiceTypeRepository InvoiceTypeRepository;

    @Override
    public List<InvoiceType> getAllInvoices() {
        return InvoiceTypeRepository.findAll();
    }


    @Override
    public Optional<InvoiceType> getInvoiceById(Long id) {
        return InvoiceTypeRepository.findById(id);
    }
}