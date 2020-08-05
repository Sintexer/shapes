package com.ilyabuglakov.shapes.validator;

import com.ilyabuglakov.shapes.model.shape.Point;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PointsValidator implements Validator<Point> {

    public boolean validate(Point point){
        return Objects.nonNull(point.getX()) && Objects.nonNull(point.getY());
    }
}
