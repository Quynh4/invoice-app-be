package com.example.invoiceapp.controller;


import com.example.invoiceapp.model.InvoiceTemplate;
import com.example.invoiceapp.service.InvoiceTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/templates")
@CrossOrigin(origins = "*") // Cho phép frontend gọi API
public class InvoiceTemplateController {

    @Autowired
    private InvoiceTemplateService service;

    @PostMapping
    public ResponseEntity<?> saveHtmlContent(@RequestBody Map<String, String> body) {
        String html = body.get("html");

        if (html == null || html.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("HTML content is missing");
        }

        InvoiceTemplate savedTemplate = service.saveHtmlContent(html);
        return ResponseEntity.ok("Invoice template saved successfully with ID = " + savedTemplate.getId());
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
