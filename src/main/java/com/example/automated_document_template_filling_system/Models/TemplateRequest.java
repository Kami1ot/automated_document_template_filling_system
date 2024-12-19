package com.example.automated_document_template_filling_system.Models;

public class TemplateRequest {
    private String templateName;
    private String userEmail;
    private InvoiceTemplateData data;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getUserEmail() {return userEmail;}
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}

    public InvoiceTemplateData getData() {
        return data;
    }

    public void setData(InvoiceTemplateData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TemplateRequest{" +
                "userEmail='" + userEmail + '\'' +
                "templateName='" + templateName + '\'' +
                ", data=" + data +
                '}';
    }

}
