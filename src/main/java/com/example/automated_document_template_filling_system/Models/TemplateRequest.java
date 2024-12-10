package com.example.automated_document_template_filling_system.Models;

public class TemplateRequest {
    private String templateName;
    private TemplateData data;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public TemplateData getData() {
        return data;
    }

    public void setData(TemplateData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TemplateRequest{" +
                "templateName='" + templateName + '\'' +
                ", data=" + data +
                '}';
    }
}
