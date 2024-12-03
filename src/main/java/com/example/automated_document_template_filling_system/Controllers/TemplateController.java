package com.example.automated_document_template_filling_system.Controllers;
import com.example.automated_document_template_filling_system.Models.TemplateRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api/templates")
@CrossOrigin(origins = "http://localhost:8080")
public class TemplateController {
    private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);

    @PostMapping("/submit")
    public ResponseEntity<String> submitTemplate(@RequestBody TemplateRequest templateRequest) {
        logger.info("Template Name: {}", templateRequest.getTemplateName());
        logger.info("Form Data: {}", templateRequest.getData().getFormData());
        logger.info("Table Data: {}", templateRequest.getData().getTableData());

        // Дополнительная обработка данных

        return new ResponseEntity<>("Данные успешно получены на сервере", HttpStatus.OK);
    }

}
