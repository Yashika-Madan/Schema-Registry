package com.example.schemaRegistry.services;

import com.example.schemaRegistry.exceptions.SchemaNotFoundException;
import com.example.schemaRegistry.repositories.SchemaRepository;
import com.example.schemaRegistry.entities.Schema;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchemaService {
    @Autowired
    private SchemaRepository schemaRepository;
    public void registerSchema(Schema schema) {
        schemaRepository.save(schema);
    }
    public List<JsonNode> getAllSchema() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<JsonNode> schemas = new ArrayList<>();
        for (Schema schema : schemaRepository.findAll()) {
            schemas.add(objectMapper.readTree(schema.getSchemaJson()));
        }
        return schemas;
    }

    public JsonNode getSchema(String name, String version) throws SchemaNotFoundException, Exception {
        Schema schema = schemaRepository.findSchema(name, version);
        System.out.println("yes u are here" + schema);
        if (schema == null) {
            throw new SchemaNotFoundException();
        }
        ObjectMapper objectMapper = new ObjectMapper();

        // Parse the JSON string and create a JsonNode object
        JsonNode jsonNode = objectMapper.readTree(schema.getSchemaJson());
        System.out.println("***********"+jsonNode);
        return jsonNode;
    }

}
