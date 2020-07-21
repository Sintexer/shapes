package com.ilyabuglakov.shapes.model;

public class Line {
    private final Point begin;
    private final Point end;

    //equation: y=kx+b
    private final double k;
    private final double b;
    private final boolean isXConst;

    public Line(Point begin, Point end) {
        if(begin.equals(end))
            throw new ArithmeticException("Can't calculate line from 1 point");
        this.begin = begin;
        this.end = end;
        if(Double.compare(begin.getX(), end.getX())==0){
            k = 0.0;
            b = 0.0;
            isXConst = true;
        }
        else {
            k = calculateK(begin, end);
            b = begin.getY() - k*begin.getX();
            isXConst = false;
        }
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
        if(isXConst){
            return Double.compare(begin.getX(), p.getX()) == 0;
        }
        return Double.compare((p.getY()-k*p.getX()-b), 0.0)==0;
    }

    public Point getBegin() {
        return begin;
    }

    public Point getEnd() {
        return end;
    }

}
