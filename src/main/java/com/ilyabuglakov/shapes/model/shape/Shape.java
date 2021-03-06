package com.ilyabuglakov.shapes.model.shape;

import java.util.List;
import java.util.Objects;

public class Shape {
    private List<Point> points;

    public Shape() {
    }

    public Shape(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Objects.equals(points, shape.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "points=" + points +
                '}';
    }
}
