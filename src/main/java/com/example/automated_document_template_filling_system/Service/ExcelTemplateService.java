package com.example.automated_document_template_filling_system.Service;
import com.example.automated_document_template_filling_system.Controllers.TemplateController;
import com.example.automated_document_template_filling_system.Models.FormData;
import com.example.automated_document_template_filling_system.Models.TableRow;
import com.example.automated_document_template_filling_system.Models.TemplateData;
import org.apache.poi.ss.extractor.ExcelExtractor;
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
    public ByteArrayOutputStream fillTemplate(TemplateData templateData) throws IOException {
        // Загрузка шаблона из ресурсов
        ClassPathResource resource = new ClassPathResource("templates/excel_templates/vat_new_invoice_fact.xlsx");
        if (!resource.exists()) {
            logger.error("Template file not found!");
            throw new FileNotFoundException("Template file not found");
        }

        logger.info("fillTemplate: Start");

        InputStream inputStream = resource.getInputStream();

        // Открытие шаблона
        Workbook workbook = new XSSFWorkbook(inputStream);

        // Получение нужного листа (например, первый лист)
        Sheet sheet = workbook.getSheetAt(0);

        // Заполнение данных формы
        fillFormData(sheet, templateData.getFormData());

        // Заполнение таблицы
        fillTableData(sheet, templateData.getTableData());

        logger.info("fillTemplate: Before writing workbook");

        // Запись заполненного файла в ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        // Закрытие ресурсов
        workbook.close();
        inputStream.close();

        logger.info("fillTemplate: End");

        return outputStream;
    }

    private void fillFormData(Sheet sheet, FormData formData) {
        // Пример заполнения данных формы
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
        // Номер счета-фактуры (ячейка E7)
        Row row = sheet.getRow(6); // Индекс строки 4 соответствует строке 5 в Excel
        Cell cell = row.getCell(4); // Колонка C имеет индекс 2
        cell.setCellValue(formData.getInvoiceNumber());

        // Дата счета-фактуры (ячейка H7-J7)
        cell = row.getCell(7); // Колонка E имеет индекс 4
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate invoiceDate = LocalDate.parse(formData.getInvoiceDate(), formatter);
        int invoiceDay = invoiceDate.getDayOfMonth();
        cell.setCellValue(invoiceDay);
        cell = row.getCell(9);
        int invoiceMonth = invoiceDate.getMonthValue();
        String invoiceMonthName = monthsGenitive[invoiceMonth - 1];
        int invoiceYear = invoiceDate.getYear();
        String monthAndYear = invoiceMonthName + " " + invoiceYear;
        cell.setCellValue(monthAndYear);

        if (!Objects.equals(formData.getInvoiceTypoNumber(), "")){
            row = sheet.getRow(7); // Индекс строки 4 соответствует строке 5 в Excel
            cell = row.getCell(4); // Колонка C имеет индекс 2
            cell.setCellValue(formData.getInvoiceTypoNumber());

            // Дата счета-фактуры (ячейка H7-J7)
            cell = row.getCell(7);
            LocalDate invoiceTypoDate = LocalDate.parse(formData.getInvoiceTypoDate(), formatter);
            int invoiceTypoDay = invoiceTypoDate.getDayOfMonth();
            cell.setCellValue(invoiceTypoDay);
            cell = row.getCell(9);
            int invoiceTypoMonth = invoiceTypoDate.getMonthValue();
            String invoiceTypoMonthName = monthsGenitive[invoiceTypoMonth - 1];
            int invoiceTypoYear = invoiceTypoDate.getYear();
            String TypoMonthAndYear = invoiceTypoMonthName + " " + invoiceTypoYear;
            cell.setCellValue(TypoMonthAndYear);
        }

        // Продолжайте для остальных полей формы, используя соответствующие координаты
        // Например:
        // Продавец (ячейка B10)
        row = sheet.getRow(9);
        cell = row.getCell(1);
        cell.setCellValue(formData.getSellerName());

        // Адрес продавца (ячейка B11)
        row = sheet.getRow(10);
        cell = row.getCell(1);
        cell.setCellValue(formData.getSellerAddress());

        row = sheet.getRow(11);
        cell = row.getCell(2);
        cell.setCellValue(formData.getSellerIIN());

        row = sheet.getRow(12);
        cell = row.getCell(4);
        cell.setCellValue(formData.getFromField());

        row = sheet.getRow(13);
        cell = row.getCell(4);
        cell.setCellValue(formData.getWhereField());

        row = sheet.getRow(14);
        cell = row.getCell(5);
        cell.setCellValue(formData.getPayNumber());
        cell = row.getCell(9);
        cell.setCellValue(formData.getPayDate());

        row = sheet.getRow(15);
        cell = row.getCell(1);
        cell.setCellValue(formData.getBuyerName());

        row = sheet.getRow(16);
        cell = row.getCell(1);
        cell.setCellValue(formData.getBuyerAddress());

        row = sheet.getRow(17);
        cell = row.getCell(2);
        cell.setCellValue(formData.getBuyerIIN());

        row = sheet.getRow(18);
        cell = row.getCell(4);
        cell.setCellValue(formData.getCurrency());



        // И так далее...
    }

    private void fillTableData(Sheet sheet, List<TableRow> tableData) {
        int startRowIndex = 23; // Индекс строки, с которой начинается таблица (строка 19 в Excel)

        for (int i = 0; i < tableData.size(); i++) {
            TableRow tableRowData = tableData.get(i);
            Row row = sheet.createRow(startRowIndex + i);

            Cell cell = row.createCell(0);
            cell.setCellValue(tableRowData.getProductName());
            cell = row.createCell(1);
            cell = row.createCell(2);
            cell.setCellValue(tableRowData.getUnitCode());

            cell = row.createCell(3);
            cell.setCellValue(tableRowData.getUnitSymbol());
            cell = row.createCell(4);

            cell = row.createCell(5);
            cell.setCellValue(tableRowData.getQuantity());

            cell = row.createCell(6);
            cell.setCellValue(tableRowData.getPrice());
            cell = row.createCell(7);

            cell = row.createCell(8);
            cell.setCellValue(tableRowData.getTotalWithoutTax());
            cell = row.createCell(9);

            cell = row.createCell(10);
            cell.setCellValue(tableRowData.getExciseSum());

            cell = row.createCell(11);
            cell.setCellValue(tableRowData.getTaxRate());

            cell = row.createCell(12);
            cell.setCellValue(tableRowData.getTaxSum());

            cell = row.createCell(13);
            cell.setCellValue(tableRowData.getTotalWithTax());
            cell = row.createCell(14);

            cell = row.createCell(15);
            cell.setCellValue(tableRowData.getOriginCountryCode());

            cell = row.createCell(16);
            cell.setCellValue(tableRowData.getOriginCountryName());

            cell = row.createCell(17);
            cell.setCellValue(tableRowData.getCustomsDeclaration());

            // Если необходимо, скопируйте стили из шаблонной строки
            // copyRowStyles(templateRow, row);
        }
    }

    // Дополнительные методы (например, copyRowStyles) при необходимости
}

