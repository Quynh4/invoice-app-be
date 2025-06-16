package com.example.invoiceapp.controller;

import com.example.invoiceapp.model.InvoiceTemplate;
import com.example.invoiceapp.service.InvoiceTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/templates")
@CrossOrigin(origins = "*")
public class InvoiceController {

    @Autowired
    private InvoiceTemplateService invoiceTemplateService;


    @PostMapping
    public ResponseEntity<?> saveHtmlContent(@RequestBody Map<String, String> body) {

        String html = body.get("htmlContent");

        if (html == null || html.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("HTML content is missing");
        }

        InvoiceTemplate savedTemplate = invoiceTemplateService.saveHtmlContent(html);
        return ResponseEntity.ok("Invoice template saved successfully with ID = " + savedTemplate.getId());
    }


    // Lấy danh sách tất cả hóa đơn
    @GetMapping
    public ResponseEntity<List<InvoiceTemplate>> getAllInvoices() {
        List<InvoiceTemplate> invoices = invoiceTemplateService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    // Lấy danh sách hóa đơn theo companyId
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<InvoiceTemplate>> getInvoicesByCompany(@PathVariable Long companyId) {
        List<InvoiceTemplate> invoices = invoiceTemplateService.getInvoicesByCompanyId(companyId);
        return ResponseEntity.ok(invoices);
    }

    // Lấy hóa đơn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceTemplate> getInvoiceById(@PathVariable Long id) {
        Optional<InvoiceTemplate> invoice = invoiceTemplateService.getInvoiceById(id);
        return invoice.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cập nhật hóa đơn
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceTemplate> updateInvoice(@PathVariable Long id, @RequestBody InvoiceTemplate invoice) {
        try {
            Optional<InvoiceTemplate> existingInvoice = invoiceTemplateService.getInvoiceById(id);
            if (existingInvoice.isPresent()) {
                invoice.setId(id);
                InvoiceTemplate updatedInvoice = invoiceTemplateService.saveInvoice(invoice);
                return ResponseEntity.ok(updatedInvoice);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Xóa hóa đơn
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        try {
            if (invoiceTemplateService.getInvoiceById(id).isPresent()) {
                invoiceTemplateService.deleteInvoice(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Tìm kiếm hóa đơn theo tên template
    @GetMapping("/search")
    public ResponseEntity<List<InvoiceTemplate>> searchInvoicesByName(
            @RequestParam String templateName,
            @RequestParam(required = false) Long companyId) {
        List<InvoiceTemplate> invoices;
        if (companyId != null) {
            invoices = invoiceTemplateService.searchByTemplateNameAndCompanyId(templateName, companyId);
        } else {
            invoices = invoiceTemplateService.searchByTemplateName(templateName);
        }
        return ResponseEntity.ok(invoices);
    }
}