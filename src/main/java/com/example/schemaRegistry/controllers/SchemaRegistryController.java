package com.example.schemaRegistry.controllers;

import com.example.schemaRegistry.entities.Schema;
import com.example.schemaRegistry.exceptions.BadRequestException;
import com.example.schemaRegistry.exceptions.SchemaNotFoundException;
import com.example.schemaRegistry.services.SchemaService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchemaRegistryController {
    @Autowired
    private SchemaService schemaService;

    @PostMapping("/schemas/save")
    private void saveSchema(@RequestBody Schema schema) {
        schemaService.registerSchema(schema);
    }

    @GetMapping
    public List<JsonNode> getAllSchema() throws Exception {
        return schemaService.getAllSchema();
    }

    @GetMapping("/schemas")
    public JsonNode getSchema(@RequestParam String name, @RequestParam String version) throws SchemaNotFoundException, Exception {
        return schemaService.getSchema(name, version);
    }

    @GetMapping("/error")
    public BadRequestException badRequest() throws BadRequestException {
        throw new BadRequestException();
    }

    @PostMapping("schemas/validate")
    public boolean validateJsonObject(@RequestBody String data) {
        String schemaString = "{\"$schema\": \"http://json-schema.org/draft-07/schema#\",\"type\": \"object\",\"properties\": {\"name\": {\"type\": \"string\"},\"age\": {\"type\": \"integer\",\"minimum\": 0},\"email\": {\"type\": \"string\",\"format\": \"email\"}},\"required\": [\"name\",\"age\"]}";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode schemaNode = objectMapper.readTree(schemaString);
            JsonNode dataNode = objectMapper.readTree(data);
            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema schema = factory.getJsonSchema(schemaNode);

            ProcessingReport report = schema.validate(dataNode);

            if (report.isSuccess()) {
                System.out.println("Json object is valid");
                return true;
            }else {
                report.forEach(msg -> {
                    if (msg.getLogLevel() == LogLevel.ERROR || msg.getLogLevel() == LogLevel.FATAL) {
                        System.out.println(msg);
                    }
                });
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
