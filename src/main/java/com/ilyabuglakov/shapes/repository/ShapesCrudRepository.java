package com.ilyabuglakov.shapes.repository;

import com.ilyabuglakov.shapes.entity.ShapeEntity;
import org.springframework.data.repository.CrudRepository;

public interface ShapesCrudRepository extends CrudRepository<ShapeEntity, Long> {
}
