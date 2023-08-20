package com.example.schemaRegistry.services;

import com.example.schemaRegistry.exceptions.SchemaNotFoundException;
import com.example.schemaRegistry.repositories.SchemaRepository;
import com.example.schemaRegistry.entities.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchemaService {
    @Autowired
    private SchemaRepository schemaRepository;
    public void registerSchema(Schema schema) {
        schemaRepository.save(schema);
    }
    public List<Schema> getAllSchema() {
        return schemaRepository.findAll();
    }

    public Schema getSchema(String name, String version) throws SchemaNotFoundException {
        Schema schema = schemaRepository.findSchema(name, version);
        System.out.println("yes u are here" + schema);
        if (schema == null) {
            throw new SchemaNotFoundException();
        }
        return schema;
    }

}
