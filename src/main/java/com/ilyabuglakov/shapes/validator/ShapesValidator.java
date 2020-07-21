package com.ilyabuglakov.shapes.validator;

import com.ilyabuglakov.shapes.model.Shape;

import java.util.Objects;

public class ShapesValidator implements Validator<Shape> {

    public boolean validate(Shape shape){
        return Objects.nonNull(shape.getPoints());
    }
}
