package com.ilyabuglakov.shapes.entity;

import com.ilyabuglakov.shapes.model.Point;
import com.ilyabuglakov.shapes.model.Shape;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShapeEntity {
    @GeneratedValue
    @Id
    private Long id;

    @ElementCollection
    private List<Point> points;

    public ShapeEntity(){}

    public ShapeEntity(List<Point> points) {
        this.points = points;
    }

    public static ShapeEntity from(Shape shape){
        return new ShapeEntity(shape.getPoints());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
