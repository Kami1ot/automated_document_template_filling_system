package com.example.automated_document_template_filling_system.Repository;
import com.example.automated_document_template_filling_system.Models.FormData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormDataRepository extends JpaRepository<FormData, String> {
    Optional<FormData> findByUser_email(String user_email);
}
