package com.example.automated_document_template_filling_system.Models;


import jakarta.persistence.*;

@Entity
public class InvoiceTableRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "invoice_form_data_id", nullable = false) // Связь через поле form_data_id
    private InvoiceFormData invoiceFormData;

    private String productName;
    private String unitCode;
    private String unitSymbol;
    private String quantity;

    public InvoiceFormData getInvoiceFormData() {
        return invoiceFormData;
    }


    public void setInvoiceFormData(InvoiceFormData invoiceFormData) {
        this.invoiceFormData = invoiceFormData;
    }

    private String price;
    private String totalWithoutTax;
    private String exciseSum;
    private String taxRate;
    private String taxSum;
    private String totalWithTax;
    private String originCountryCode;
    private String originCountryName;
    private String customsDeclaration;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitSymbol() {
        return unitSymbol;
    }

    public void setUnitSymbol(String unitSymbol) {
        this.unitSymbol = unitSymbol;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalWithoutTax() {
        return totalWithoutTax;
    }

    public void setTotalWithoutTax(String totalWithoutTax) {
        this.totalWithoutTax = totalWithoutTax;
    }

    public String getExciseSum() {
        return exciseSum;
    }

    public void setExciseSum(String exciseSum) {
        this.exciseSum = exciseSum;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxSum() {
        return taxSum;
    }

    public void setTaxSum(String taxSum) {
        this.taxSum = taxSum;
    }

    public String getTotalWithTax() {
        return totalWithTax;
    }

    public void setTotalWithTax(String totalWithTax) {
        this.totalWithTax = totalWithTax;
    }

    public String getOriginCountryCode() {
        return originCountryCode;
    }

    public void setOriginCountryCode(String originCountryCode) {
        this.originCountryCode = originCountryCode;
    }

    public String getOriginCountryName() {
        return originCountryName;
    }

    public void setOriginCountryName(String originCountryName) {
        this.originCountryName = originCountryName;
    }

    public String getCustomsDeclaration() {
        return customsDeclaration;
    }

    public void setCustomsDeclaration(String customsDeclaration) {
        this.customsDeclaration = customsDeclaration;
    }

    @Override
    public String toString() {
        return "InvoiceTableRow{" +
                "productName='" + productName + '\'' +
                ", unitCode='" + unitCode + '\'' +
                ", unitSymbol='" + unitSymbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", totalWithoutTax=" + totalWithoutTax +
                ", exciseSum=" + exciseSum +
                ", taxRate=" + taxRate +
                ", taxSum=" + taxSum +
                ", totalWithTax=" + totalWithTax +
                ", originCountryCode='" + originCountryCode + '\'' +
                ", originCountryName='" + originCountryName + '\'' +
                ", customsDeclaration='" + customsDeclaration + '\'' +
                '}';
    }

}
