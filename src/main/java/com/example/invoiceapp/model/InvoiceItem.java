package com.example.invoiceapp.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor

public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}