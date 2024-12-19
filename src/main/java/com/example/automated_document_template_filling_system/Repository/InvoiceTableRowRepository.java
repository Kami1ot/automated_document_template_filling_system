package com.example.automated_document_template_filling_system.Repository;
import com.example.automated_document_template_filling_system.Models.InvoiceTableRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceTableRowRepository extends JpaRepository<InvoiceTableRow, Long> {
    Optional<InvoiceTableRow> findByInvoiceFormData_Id(Long invoice_form_data_id);
}
