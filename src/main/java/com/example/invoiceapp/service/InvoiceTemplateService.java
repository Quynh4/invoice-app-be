package com.example.invoiceapp.service;

import com.example.invoiceapp.model.InvoiceTemplate;
import java.util.List;
import java.util.Optional;

public interface InvoiceTemplateService {
    List<InvoiceTemplate> getAllInvoices();
    List<InvoiceTemplate> getInvoicesByCompanyId(Long companyId);
    Optional<InvoiceTemplate> getInvoiceById(Long id);
    InvoiceTemplate saveInvoice(InvoiceTemplate invoice);
    void deleteInvoice(Long id);
    List<InvoiceTemplate> searchByTemplateName(String templateName);
    List<InvoiceTemplate> searchByTemplateNameAndCompanyId(String templateName, Long companyId);
    InvoiceTemplate saveHtmlContent(String htmlContent);
}
