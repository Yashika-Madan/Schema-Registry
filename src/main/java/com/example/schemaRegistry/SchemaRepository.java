package com.example.schemaRegistry;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SchemaRepository extends JpaRepository<Schema, Integer> {
}
