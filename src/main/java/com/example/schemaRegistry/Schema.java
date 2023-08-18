package com.example.schemaRegistry;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Schema {
    @Id
    private Integer id;
    private String name;
    private String version;
    private String schemaDsp;

    public Schema() {

    }

    public Schema(Integer id, String name, String version, String schemaDsp) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.schemaDsp = schemaDsp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSchemaDsp() {
        return schemaDsp;
    }

    public void setSchemaDsp(String schemaDsp) {
        this.schemaDsp = schemaDsp;
    }
}
