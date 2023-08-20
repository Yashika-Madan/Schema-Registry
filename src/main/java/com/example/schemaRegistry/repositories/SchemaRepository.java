package com.example.schemaRegistry.repositories;

import com.example.schemaRegistry.entities.Schema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SchemaRepository extends JpaRepository<Schema, Integer> {
    @Query(value = "SELECT * FROM Schema WHERE name = ?1 AND version = ?2", nativeQuery = true)
    public Schema findSchema(String name, String version);
}
