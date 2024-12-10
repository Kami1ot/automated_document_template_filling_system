package com.example.automated_document_template_filling_system.Controllers;
import com.example.automated_document_template_filling_system.Models.TemplateRequest;
import com.example.automated_document_template_filling_system.Service.ExcelTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/templates")
public class TemplateController {

    private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);

    @Autowired
    private ExcelTemplateService excelTemplateService;

    @PostMapping("/submit")
    public ResponseEntity<byte[]> submitTemplate(@RequestBody TemplateRequest templateRequest) {
        logger.info("Template Name: {}", templateRequest.getTemplateName());
        logger.info("Form Data: {}", templateRequest.getData().getFormData());
        logger.info("Table Data: {}", templateRequest.getData().getTableData());

        String templateName = templateRequest.getTemplateName();
        logger.info(templateName);

        if ("invoice template".equals(templateName)) {
            logger.info("1");
            try {
                logger.info("2");
                ByteArrayOutputStream outputStream = excelTemplateService.fillTemplate(templateRequest.getData());
                logger.info("3: Template filled successfully");

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDisposition(ContentDisposition.builder("attachment")
                        .filename("invoice.xlsx")
                        .build());
                logger.info("4: Headers set successfully");

                return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
            } catch (Exception e) {
                logger.error("Error while generating the template", e);
                return new ResponseEntity<>(("Ошибка при формировании документа: " + e.getMessage()).getBytes(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>("Неизвестный шаблон".getBytes(), HttpStatus.BAD_REQUEST);
    }

}

