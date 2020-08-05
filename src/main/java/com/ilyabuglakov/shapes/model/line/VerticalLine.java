package com.ilyabuglakov.shapes.model.line;

import com.ilyabuglakov.shapes.model.shape.Point;

public class VerticalLine extends Line {

    public VerticalLine(Point begin, Point end){
        super(begin, end);
    }

    @Override
    public boolean contains(Point point) {
        return Double.compare(point.getX(), this.begin.getX())==0;
    }
}
