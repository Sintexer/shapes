package com.ilyabuglakov.shapes.repository;

import com.ilyabuglakov.shapes.entity.ShapeEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ShapesCrudRepository extends CrudRepository<ShapeEntity, Long> {
    List<ShapeEntity> getAllByIdNotNull();
}
