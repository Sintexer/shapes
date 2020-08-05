package com.ilyabuglakov.shapes.model.line;

import com.ilyabuglakov.shapes.model.shape.Point;

public class AnyLine extends Line {

    //equation: y=kx+b
    private final double k;
    private final double b;

    public AnyLine(Point begin, Point end) {
        super(begin, end);
        k = calculateK(begin, end);
        b = begin.getY() - k*begin.getX();
    }

    public static double calculateK(Point p1, Point p2) {
        double result;
        if (Double.compare(p1.getY(), p2.getY()) == 0)
            return 0.0;
        if (Double.compare(p1.getX(), p2.getX()) == 0)
            return 0.0;
        double coef = p1.getX() - p2.getX();
        double y = p1.getY() - p2.getY();
        return y / coef;
    }

    public boolean contains(Point p) {
        return Double.compare((p.getY()-k*p.getX()-b), 0.0)==0;
    }

}
