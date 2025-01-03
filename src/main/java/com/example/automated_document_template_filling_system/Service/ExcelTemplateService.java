package com.example.automated_document_template_filling_system.Service;
import com.example.automated_document_template_filling_system.Models.InvoiceFormData;
import com.example.automated_document_template_filling_system.Models.InvoiceTableRow;
import com.example.automated_document_template_filling_system.Models.InvoiceTemplateData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ExcelTemplateService {
    private static final Logger logger = LoggerFactory.getLogger(ExcelTemplateService.class);
    public ByteArrayOutputStream fillTemplate(InvoiceTemplateData invoiceTemplateData) throws IOException {
        ClassPathResource resource = new ClassPathResource("templates/excel_templates/vat_new_invoice_fact.xlsx");
        if (!resource.exists()) {
            logger.error("Template file not found!");
            throw new FileNotFoundException("Template file not found");
        }

        logger.info("fillTemplate: Start");

        InputStream inputStream = resource.getInputStream();

        Workbook workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        fillFormData(sheet, invoiceTemplateData.getFormData());

        fillTableData(sheet, invoiceTemplateData.getTableData());

        logger.info("fillTemplate: Before writing workbook");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        workbook.close();
        inputStream.close();

        logger.info("fillTemplate: End");

        return outputStream;
    }

    private void fillFormData(Sheet sheet, InvoiceFormData invoiceFormData) {
        String[] monthsGenitive = {
                "января",   // 1
                "февраля",  // 2
                "марта",    // 3
                "апреля",   // 4
                "мая",      // 5
                "июня",     // 6
                "июля",     // 7
                "августа",  // 8
                "сентября", // 9
                "октября",  // 10
                "ноября",   // 11
                "декабря"   // 12
        };
        Row row = sheet.getRow(6);
        Cell cell = row.getCell(4);
        cell.setCellValue(invoiceFormData.getInvoiceNumber());

        cell = row.getCell(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate invoiceDate = LocalDate.parse(invoiceFormData.getInvoiceDate(), formatter);
        int invoiceDay = invoiceDate.getDayOfMonth();
        cell.setCellValue(invoiceDay);
        cell = row.getCell(9);
        int invoiceMonth = invoiceDate.getMonthValue();
        String invoiceMonthName = monthsGenitive[invoiceMonth - 1];
        int invoiceYear = invoiceDate.getYear();
        String monthAndYear = invoiceMonthName + " " + invoiceYear;
        cell.setCellValue(monthAndYear);

        if (!Objects.equals(invoiceFormData.getInvoiceTypoNumber(), "")){
            row = sheet.getRow(7);
            cell = row.getCell(4);
            cell.setCellValue(invoiceFormData.getInvoiceTypoNumber());

            cell = row.getCell(7);
            LocalDate invoiceTypoDate = LocalDate.parse(invoiceFormData.getInvoiceTypoDate(), formatter);
            int invoiceTypoDay = invoiceTypoDate.getDayOfMonth();
            cell.setCellValue(invoiceTypoDay);
            cell = row.getCell(9);
            int invoiceTypoMonth = invoiceTypoDate.getMonthValue();
            String invoiceTypoMonthName = monthsGenitive[invoiceTypoMonth - 1];
            int invoiceTypoYear = invoiceTypoDate.getYear();
            String TypoMonthAndYear = invoiceTypoMonthName + " " + invoiceTypoYear;
            cell.setCellValue(TypoMonthAndYear);
        }

        row = sheet.getRow(9);
        cell = row.getCell(1);
        cell.setCellValue(invoiceFormData.getSellerName());

        row = sheet.getRow(10);
        cell = row.getCell(1);
        cell.setCellValue(invoiceFormData.getSellerAddress());

        row = sheet.getRow(11);
        cell = row.getCell(2);
        cell.setCellValue(invoiceFormData.getSellerIIN());

        row = sheet.getRow(12);
        cell = row.getCell(4);
        cell.setCellValue(invoiceFormData.getFromField());

        row = sheet.getRow(13);
        cell = row.getCell(4);
        cell.setCellValue(invoiceFormData.getWhereField());

        row = sheet.getRow(14);
        cell = row.getCell(5);
        cell.setCellValue(invoiceFormData.getPayNumber());
        cell = row.getCell(9);
        cell.setCellValue(invoiceFormData.getPayDate());

        row = sheet.getRow(15);
        cell = row.getCell(1);
        cell.setCellValue(invoiceFormData.getBuyerName());

        row = sheet.getRow(16);
        cell = row.getCell(1);
        cell.setCellValue(invoiceFormData.getBuyerAddress());

        row = sheet.getRow(17);
        cell = row.getCell(2);
        cell.setCellValue(invoiceFormData.getBuyerIIN());

        row = sheet.getRow(18);
        cell = row.getCell(4);
        cell.setCellValue(invoiceFormData.getCurrency());



    }

    private void fillTableData(Sheet sheet, List<InvoiceTableRow> tableData) {
        int startRowIndex = 23;

        for (int i = 0; i < tableData.size(); i++) {
            InvoiceTableRow invoiceTableRowData = tableData.get(i);
            int currentRowIndex = startRowIndex + i;

            shiftRows(sheet, currentRowIndex);

            Row row = sheet.createRow(currentRowIndex);

            Cell cell = row.createCell(0);
            cell.setCellValue(invoiceTableRowData.getProductName());
            cell = row.createCell(1);
            cell = row.createCell(2);
            cell.setCellValue(invoiceTableRowData.getUnitCode());

            cell = row.createCell(3);
            cell.setCellValue(invoiceTableRowData.getUnitSymbol());
            cell = row.createCell(4);

            cell = row.createCell(5);
            cell.setCellValue(invoiceTableRowData.getQuantity());

            cell = row.createCell(6);
            cell.setCellValue(invoiceTableRowData.getPrice());
            cell = row.createCell(7);

            cell = row.createCell(8);
            cell.setCellValue(invoiceTableRowData.getTotalWithoutTax());
            cell = row.createCell(9);

            cell = row.createCell(10);
            cell.setCellValue(invoiceTableRowData.getExciseSum());

            cell = row.createCell(11);
            cell.setCellValue(invoiceTableRowData.getTaxRate());

            cell = row.createCell(12);
            cell.setCellValue(invoiceTableRowData.getTaxSum());

            cell = row.createCell(13);
            cell.setCellValue(invoiceTableRowData.getTotalWithTax());
            cell = row.createCell(14);

            cell = row.createCell(15);
            cell.setCellValue(invoiceTableRowData.getOriginCountryCode());

            cell = row.createCell(16);
            cell.setCellValue(invoiceTableRowData.getOriginCountryName());

            cell = row.createCell(17);
            cell.setCellValue(invoiceTableRowData.getCustomsDeclaration());

        }
    }

    private void shiftRows(Sheet sheet, int startRow) {
        int lastRow = sheet.getLastRowNum();

        sheet.shiftRows(startRow, lastRow, 1);
    }

}


