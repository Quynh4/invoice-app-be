package com.example.invoiceapp.service;
import com.example.invoiceapp.model.InvoiceTemplate;
import com.example.invoiceapp.repository.InvoiceTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceTemplateService {

        @Autowired
        private InvoiceTemplateRepository invoiceTemplateRepository;

        public InvoiceTemplate saveHtmlContent(String htmlContent) {
            InvoiceTemplate template = new InvoiceTemplate();
            template.setHtmlContent(htmlContent);
            template.setTemplateName(null);
            template.setCreatedBy(null);
            template.setCreatedAt(null);

            return invoiceTemplateRepository.save(template);
        }
    private final InvoiceTemplateRepository repository;

    public InvoiceTemplateService(InvoiceTemplateRepository repository) {
        this.repository = repository;
    }

    public List<InvoiceTemplate> findAll() {
        return repository.findAll();
    }

    public Optional<InvoiceTemplate> findById(Long id) {
        return repository.findById(id);
    }   

    public InvoiceTemplate save(InvoiceTemplate template) {
        return repository.save(template);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}