package com.example.automated_document_template_filling_system.Controllers;
import com.example.automated_document_template_filling_system.Models.InvoiceFormData;
import com.example.automated_document_template_filling_system.Models.TemplateRequest;
import com.example.automated_document_template_filling_system.Models.Templates;
import com.example.automated_document_template_filling_system.Models.InvoiceTableRow;
import com.example.automated_document_template_filling_system.Repository.TemplatesRepository;
import com.example.automated_document_template_filling_system.Repository.UserRepository;
import com.example.automated_document_template_filling_system.Service.ExcelTemplateService;
import com.example.automated_document_template_filling_system.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.automated_document_template_filling_system.Models.User;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/templates")
public class TemplateController {

    private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);

    @Autowired
    private ExcelTemplateService excelTemplateService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TemplatesRepository templatesRepository;

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/submit")
    public ResponseEntity<byte[]> submitTemplate(@RequestBody TemplateRequest templateRequest) {
        logger.info("Template Name: {}", templateRequest.getTemplateName());
        logger.info("UserEmail:{}", templateRequest.getUserEmail());
        logger.info("Form Data: {}", templateRequest.getData().getFormData());
        logger.info("Table Data: {}", templateRequest.getData().getTableData());

        String templateName = templateRequest.getTemplateName();
        logger.info(templateName);

        if ("invoice template".equals(templateName)) {
            logger.info("1");
            try {
                InvoiceFormData invoiceFormData = new InvoiceFormData();

                User userEmail = userRepository.findByEmail(templateRequest.getUserEmail()).stream().toList().getFirst();
                invoiceFormData.setUser(userEmail);

                Templates tempName = templatesRepository.findByName(templateRequest.getTemplateName()).stream().toList().getFirst();
                invoiceFormData.setTemplates(tempName);

                invoiceFormData.setBuyerAddress(templateRequest.getData().getFormData().getBuyerAddress());
                invoiceFormData.setBuyerName(templateRequest.getData().getFormData().getBuyerName());
                invoiceFormData.setBuyerIIN(templateRequest.getData().getFormData().getBuyerIIN());
                invoiceFormData.setCurrency(templateRequest.getData().getFormData().getCurrency());
                invoiceFormData.setFromField(templateRequest.getData().getFormData().getFromField());
                invoiceFormData.setWhereField(templateRequest.getData().getFormData().getWhereField());
                invoiceFormData.setInvoiceDate(templateRequest.getData().getFormData().getInvoiceDate());
                invoiceFormData.setInvoiceNumber(templateRequest.getData().getFormData().getInvoiceNumber());
                invoiceFormData.setInvoiceTypoDate(templateRequest.getData().getFormData().getInvoiceTypoDate());
                invoiceFormData.setInvoiceTypoNumber(templateRequest.getData().getFormData().getInvoiceTypoNumber());
                invoiceFormData.setPayDate(templateRequest.getData().getFormData().getPayDate());
                invoiceFormData.setPayNumber(templateRequest.getData().getFormData().getPayNumber());
                invoiceFormData.setSellerName(templateRequest.getData().getFormData().getSellerName());
                invoiceFormData.setSellerAddress(templateRequest.getData().getFormData().getSellerAddress());
                invoiceFormData.setSellerIIN(templateRequest.getData().getFormData().getSellerIIN());

                logger.info("2");
                List<InvoiceTableRow> tableRows = templateRequest.getData().getTableData()
                        .stream()
                        .map(row -> {
                            InvoiceTableRow newRow = new InvoiceTableRow();
                            newRow.setProductName(row.getProductName());
                            newRow.setQuantity(row.getQuantity());
                            newRow.setCustomsDeclaration(row.getCustomsDeclaration());
                            newRow.setExciseSum(row.getExciseSum());
                            newRow.setPrice(row.getPrice());
                            newRow.setTaxRate(row.getTaxRate());
                            newRow.setTaxSum(row.getTaxSum());
                            newRow.setUnitCode(row.getUnitCode());
                            newRow.setUnitSymbol(row.getUnitSymbol());
                            newRow.setOriginCountryCode(row.getOriginCountryCode());
                            newRow.setOriginCountryName(row.getOriginCountryName());
                            newRow.setTotalWithoutTax(row.getTotalWithoutTax());
                            newRow.setTotalWithTax(row.getTotalWithTax());
                            return newRow;
                        })
                        .toList();

                invoiceFormData.setInvoiceTableRows(tableRows);

                invoiceService.saveInvoiceData(invoiceFormData);




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

