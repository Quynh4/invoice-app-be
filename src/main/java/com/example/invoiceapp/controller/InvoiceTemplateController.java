package com.example.invoiceapp.controller;


import com.example.invoiceapp.model.InvoiceTemplate;
import com.example.invoiceapp.service.InvoiceTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
@CrossOrigin(origins = "*") // Cho phép frontend gọi API
public class InvoiceTemplateController {

    private final InvoiceTemplateService service;

    public InvoiceTemplateController(InvoiceTemplateService service) {
        this.service = service;
    }

    @GetMapping
    public List<InvoiceTemplate> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceTemplate> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public InvoiceTemplate create(@RequestBody InvoiceTemplate template) {
        return service.save(template);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceTemplate> update(@PathVariable Long id, @RequestBody InvoiceTemplate updated) {
        return service.findById(id)
                .map(existing -> {
                    existing.setTemplateName(updated.getTemplateName());
                    existing.setHtmlContent(updated.getHtmlContent());
                    existing.setCreatedBy(updated.getCreatedBy());
                    return ResponseEntity.ok(service.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
