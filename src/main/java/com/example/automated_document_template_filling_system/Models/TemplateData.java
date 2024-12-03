package com.example.automated_document_template_filling_system.Models;
import java.util.List;

public class TemplateData {
    private FormData formData;
    private List<TableRow> tableData;

    // Геттеры и сеттеры
    public FormData getFormData() {
        return formData;
    }

    public void setFormData(FormData formData) {
        this.formData = formData;
    }

    public List<TableRow> getTableData() {
        return tableData;
    }

    public void setTableData(List<TableRow> tableData) {
        this.tableData = tableData;
    }

    @Override
    public String toString() {
        return "TemplateData{" +
                "formData=" + formData +
                ", tableData=" + tableData +
                '}';
    }

}
