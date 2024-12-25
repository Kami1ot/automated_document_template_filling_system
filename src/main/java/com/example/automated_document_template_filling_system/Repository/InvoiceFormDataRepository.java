package com.example.automated_document_template_filling_system.Repository;

import com.example.automated_document_template_filling_system.Models.InvoiceFormData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceFormDataRepository extends JpaRepository<InvoiceFormData, Long> {

    @Query("SELECT i FROM InvoiceFormData i WHERE i.user.email = :email ORDER BY i.invoiceDate DESC")
    List<InvoiceFormData> findByUserEmail(@Param("email") String email);
}
