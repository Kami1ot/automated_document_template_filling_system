package com.example.automated_document_template_filling_system.DTO;

// DTO для InvoiceFormData
public class InvoiceFormDataDTO {
    private Long id;
    private String invoiceNumber;
    private String invoiceDate;
    private String invoiceTypoNumber;
    private String invoiceTypoDate;
    private String sellerName;
    private String sellerAddress;
    private String sellerIIN;
    private String fromField;
    private String whereField;
    private String payNumber;
    private String payDate;
    private String buyerName;
    private String buyerAddress;
    private String buyerIIN;
    private String currency;

    // Конструктор
    public InvoiceFormDataDTO(String invoiceNumber, String invoiceDate, String invoiceTypoNumber,
                              String invoiceTypoDate, String sellerName, String sellerAddress, String sellerIIN,
                              String fromField, String whereField, String payNumber, String payDate,
                              String buyerName, String buyerAddress, String buyerIIN, String currency) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.invoiceTypoNumber = invoiceTypoNumber;
        this.invoiceTypoDate = invoiceTypoDate;
        this.sellerName = sellerName;
        this.sellerAddress = sellerAddress;
        this.sellerIIN = sellerIIN;
        this.fromField = fromField;
        this.whereField = whereField;
        this.payNumber = payNumber;
        this.payDate = payDate;
        this.buyerName = buyerName;
        this.buyerAddress = buyerAddress;
        this.buyerIIN = buyerIIN;
        this.currency = currency;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceTypoNumber() {
        return invoiceTypoNumber;
    }

    public void setInvoiceTypoNumber(String invoiceTypoNumber) {
        this.invoiceTypoNumber = invoiceTypoNumber;
    }

    public String getInvoiceTypoDate() {
        return invoiceTypoDate;
    }

    public void setInvoiceTypoDate(String invoiceTypoDate) {
        this.invoiceTypoDate = invoiceTypoDate;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerIIN() {
        return sellerIIN;
    }

    public void setSellerIIN(String sellerIIN) {
        this.sellerIIN = sellerIIN;
    }

    public String getFromField() {
        return fromField;
    }

    public void setFromField(String fromField) {
        this.fromField = fromField;
    }

    public String getWhereField() {
        return whereField;
    }

    public void setWhereField(String whereField) {
        this.whereField = whereField;
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerIIN() {
        return buyerIIN;
    }

    public void setBuyerIIN(String buyerIIN) {
        this.buyerIIN = buyerIIN;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
