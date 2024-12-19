package com.example.automated_document_template_filling_system.Repository;
import com.example.automated_document_template_filling_system.Models.Templates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplatesRepository extends JpaRepository<Templates, String> {
    Optional<Templates> findByName(String name);
}
