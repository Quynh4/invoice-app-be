package com.example.invoiceapp.controller;

import com.example.invoiceapp.model.InvoiceType;
import com.example.invoiceapp.service.InvoiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/types")
@CrossOrigin(origins = "*")
public class InvoiceTypeController {

    @Autowired
    private InvoiceTypeService InvoiceTypeService;

    // Lấy danh sách tất cả hóa đơn
    @GetMapping
    public ResponseEntity<List<InvoiceType>> getAllInvoices() {
        List<InvoiceType> invoices = InvoiceTypeService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    // Lấy hóa đơn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceType> getInvoiceById(@PathVariable Long id) {
        Optional<InvoiceType> invoice = InvoiceTypeService.getInvoiceById(id);
        return invoice.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}