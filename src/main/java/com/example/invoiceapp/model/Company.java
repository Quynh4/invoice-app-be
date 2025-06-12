package com.example.invoiceapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@RequiredArgsConstructor
public class Company {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String taxCode;
    private LocalDateTime createdAt = LocalDateTime.now();
}
