package com.example.schemaRegistry.controllers;

import com.example.schemaRegistry.entities.Schema;
import com.example.schemaRegistry.exceptions.SchemaNotFoundException;
import com.example.schemaRegistry.services.SchemaService;
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
    public List<Schema> getAllSchema() {
        return schemaService.getAllSchema();
    }

    @GetMapping("/schemas")
    public Schema getSchema(@RequestParam String name, @RequestParam String version) throws SchemaNotFoundException {
        return schemaService.getSchema(name, version);
    }

    @GetMapping("/error")
    public String badRequest() {
        return "bad request";
    }

}
