package com.example.invoiceapp.repository;

import com.example.invoiceapp.model.InvoiceTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceTemplateRepository extends JpaRepository<InvoiceTemplate, Long> {

    // Thêm query tùy chỉnh để test
    @Query("SELECT i FROM InvoiceTemplate i WHERE i.id = :id")
    Optional<InvoiceTemplate> findByIdCustom(@Param("id") Long id);

    // Tìm theo companyId
    List<InvoiceTemplate> findByCompanyId(Long companyId);

    // Tìm theo tên template (không phân biệt chữ hoa/thường)
    List<InvoiceTemplate> findByTemplateNameContainingIgnoreCase(String templateName);

    // Tìm theo tên template và companyId
    List<InvoiceTemplate> findByTemplateNameContainingIgnoreCaseAndCompanyId(String templateName, Long companyId);

    // Tìm theo người tạo
    List<InvoiceTemplate> findByCreatedBy(String createdBy);

    // Tìm theo người tạo và companyId
    List<InvoiceTemplate> findByCreatedByAndCompanyId(String createdBy, Long companyId);
}