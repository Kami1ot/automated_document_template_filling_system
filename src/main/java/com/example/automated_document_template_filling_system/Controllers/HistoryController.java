package com.example.automated_document_template_filling_system.Controllers;

import com.example.automated_document_template_filling_system.DTO.InvoiceFormDataDTO;
import com.example.automated_document_template_filling_system.Models.InvoiceFormData;
import com.example.automated_document_template_filling_system.Repository.InvoiceFormDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/invoices")
public class HistoryController {
    private static final Logger logger = LoggerFactory.getLogger(HistoryController.class);

    @Autowired
    private InvoiceFormDataRepository invoiceFormDataRepository;

    @GetMapping("/history")
    public ResponseEntity<List<InvoiceFormDataDTO>> getUserInvoices(@RequestParam String email) {
        logger.info("Получен запрос для email: {}", email);
        List<InvoiceFormData> invoices = invoiceFormDataRepository.findByUserEmail(email);
        logger.info("Найдено записей: {}", invoices.size());
        List<InvoiceFormDataDTO> dtos = invoices.stream()
                .map(invoice -> new InvoiceFormDataDTO(
                        invoice.getInvoiceNumber(),
                        invoice.getInvoiceDate(),
                        invoice.getInvoiceTypoNumber(),
                        invoice.getInvoiceTypoDate(),
                        invoice.getSellerName(),
                        invoice.getSellerAddress(),
                        invoice.getSellerIIN(),
                        invoice.getFromField(),
                        invoice.getWhereField(),
                        invoice.getPayNumber(),
                        invoice.getPayDate(),
                        invoice.getBuyerName(),
                        invoice.getBuyerAddress(),
                        invoice.getBuyerIIN(),
                        invoice.getCurrency()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

}
