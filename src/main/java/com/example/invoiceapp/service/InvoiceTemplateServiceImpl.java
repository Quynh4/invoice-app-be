package com.example.invoiceapp.service;


import com.example.invoiceapp.model.InvoiceTemplate;
import com.example.invoiceapp.repository.InvoiceTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceTemplateServiceImpl implements InvoiceTemplateService {

    @Autowired
    private InvoiceTemplateRepository invoiceTemplateRepository;

    public InvoiceTemplate saveHtmlContent(String htmlContent) {
        InvoiceTemplate template = new InvoiceTemplate();
        template.setCompanyId(Long.parseLong("1"));
        template.setHtmlContent(htmlContent);
        template.setTemplateName("Name");
        template.setCreatedBy("Q");
        template.setCreatedAt(null);

        return invoiceTemplateRepository.save(template);
    }

    @Override
    public List<InvoiceTemplate> getAllInvoices() {
        return invoiceTemplateRepository.findAll();
    }


    @Override
    public List<InvoiceTemplate> getInvoicesByCompanyId(Long companyId) {
        return invoiceTemplateRepository.findByCompanyId(companyId);
    }

    @Override
    public Optional<InvoiceTemplate> getInvoiceById(Long id) {
        return invoiceTemplateRepository.findByIdCustom(id);
    }

    @Override
    public InvoiceTemplate saveInvoice(InvoiceTemplate invoice) {
        return invoiceTemplateRepository.save(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceTemplateRepository.deleteById(id);
    }

    @Override
    public List<InvoiceTemplate> searchByTemplateName(String templateName) {
        return invoiceTemplateRepository.findByTemplateNameContainingIgnoreCase(templateName);
    }

    @Override
    public List<InvoiceTemplate> searchByTemplateNameAndCompanyId(String templateName, Long companyId) {
        return invoiceTemplateRepository.findByTemplateNameContainingIgnoreCaseAndCompanyId(templateName, companyId);
    }
}