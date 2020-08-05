package com.ilyabuglakov.shapes.model.line;

import com.ilyabuglakov.shapes.model.shape.Point;

public abstract class Line {
    protected final Point begin;
    protected final Point end;

    public Line(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    public abstract boolean contains(Point point);

    public Point getBegin() {
        return begin;
    }

    public Point getEnd() {
        return end;
    }

    public static Line valueOf(Point begin, Point end) {
        if (Double.compare(begin.getX(), end.getX()) == 0)
            return new VerticalLine(begin, end);
        else
            return new AnyLine(begin, end);
    }
}
