package com.example.automated_document_template_filling_system.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Templates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String temp_path;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
