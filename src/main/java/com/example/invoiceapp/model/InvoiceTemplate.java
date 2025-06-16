package com.example.invoiceapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice_template")
public class InvoiceTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String templateName;

    @Column(columnDefinition = "TEXT")
    private String htmlContent; // Nội dung HTML của mẫu hóa đơn

    private String createdBy;

    private LocalDateTime createdAt;

    private Long companyId;
    private Long invoiceTypeId;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Constructor với companyId
    public InvoiceTemplate(String templateName, String htmlContent, String createdBy, Long companyId, Long invoiceTypeId) {
        this.templateName = templateName;
        this.htmlContent = htmlContent;
        this.createdBy = createdBy;
        this.companyId = companyId;
        this.createdAt = LocalDateTime.now();
        this.invoiceTypeId = invoiceTypeId;
    }
}