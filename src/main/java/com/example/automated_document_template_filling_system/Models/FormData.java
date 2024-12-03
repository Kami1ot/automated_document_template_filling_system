package com.example.automated_document_template_filling_system.Models;

public class FormData {
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

    // Геттеры и сеттеры
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

    @Override
    public String toString() {
        return "FormData{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", invoiceTypoNumber='" + invoiceTypoNumber + '\'' +
                ", invoiceTypoDate='" + invoiceTypoDate + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", sellerAddress='" + sellerAddress + '\'' +
                ", sellerIIN='" + sellerIIN + '\'' +
                ", fromField='" + fromField + '\'' +
                ", whereField='" + whereField + '\'' +
                ", payNumber='" + payNumber + '\'' +
                ", payDate='" + payDate + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", buyerAddress='" + buyerAddress + '\'' +
                ", buyerIIN='" + buyerIIN + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
