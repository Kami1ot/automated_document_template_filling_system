package com.example.automated_document_template_filling_system.Repository;
import com.example.automated_document_template_filling_system.Models.FormData;
import com.example.automated_document_template_filling_system.Models.TableRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableRowRepository extends JpaRepository<TableRow, Long> {
    Optional<TableRow> findByFormData_Id(Long form_data_lid);
}
