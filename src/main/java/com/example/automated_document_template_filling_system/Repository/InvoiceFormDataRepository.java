package com.example.automated_document_template_filling_system.Repository;
import com.example.automated_document_template_filling_system.Models.InvoiceFormData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceFormDataRepository extends JpaRepository<InvoiceFormData, String> {
    Optional<InvoiceFormData> findByUser_email(String user_email);
}
