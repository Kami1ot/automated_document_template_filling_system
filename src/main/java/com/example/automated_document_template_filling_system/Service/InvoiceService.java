package com.example.automated_document_template_filling_system.Service;

import com.example.automated_document_template_filling_system.Models.InvoiceFormData;
import com.example.automated_document_template_filling_system.Models.InvoiceTableRow;
import com.example.automated_document_template_filling_system.Repository.InvoiceFormDataRepository;
import com.example.automated_document_template_filling_system.Repository.InvoiceTableRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceFormDataRepository formDataRepository;

    @Autowired
    private InvoiceTableRowRepository tableRowRepository;

    @Transactional
    public InvoiceFormData saveInvoiceData(InvoiceFormData formData) {
        formData.getInvoiceTableRows().forEach(row -> row.setInvoiceFormData(formData));
        return formDataRepository.save(formData);
    }
}
