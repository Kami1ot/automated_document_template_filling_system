package com.example.automated_document_template_filling_system.Models;
import java.util.List;

public class InvoiceTemplateData {
    private InvoiceFormData invoiceFormData;
    private List<InvoiceTableRow> tableData;

    public InvoiceFormData getFormData() {
        return invoiceFormData;
    }

    public void setFormData(InvoiceFormData invoiceFormData) {
        this.invoiceFormData = invoiceFormData;
    }

    public List<InvoiceTableRow> getTableData() {
        return tableData;
    }

    public void setTableData(List<InvoiceTableRow> tableData) {
        this.tableData = tableData;
    }

    @Override
    public String toString() {
        return "InvoiceTemplateData{" +
                "invoiceFormData=" + invoiceFormData +
                ", tableData=" + tableData +
                '}';
    }

}
