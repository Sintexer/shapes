package com.ilyabuglakov.shapes.service;


import com.ilyabuglakov.shapes.model.Line;
import com.ilyabuglakov.shapes.model.Point;
import com.ilyabuglakov.shapes.model.Shape;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShapeService {
    public Shape removeExcessPoints(Shape shape) {
        List<Point> result = new ArrayList<Point>(shape.getPoints());
        int length = result.size();
        if(length<3)
            return shape;
        Map<Line, List<Point>> collapsed = new HashMap<>();

        for (int i = 0; i < length; ++i) {
            int j = (i+1)%length;
            int k = (i+2)%length;
            List<Point> points = Arrays.asList(result.get(i), result.get(j), result.get(k));
            if(pointsOnOneLine((Point[])points.toArray())){
                Line line = new Line(result.get(i), result.get(j));
                collapsed.put(line, points);
            }
        }
//        if (result.size() > 2) {
//            Line line;
//            Iterator<Point> it = shape.getPoints().iterator();
//            Point begin, middle, end;
//            begin = it.next();
//            middle = it.next();
//            while (it.hasNext()) {
//                end = it.next();
//                line = new Line(begin, end);
//                if(line.contains(middle)) result.remove(middle); else begin = middle;
//                middle = end;
//            }
//            if (result.size() > 2) {
//                end = result.get(0);
//                line = new Line(begin, end);
//                if (line.contains(middle)) result.remove(middle);
//                else begin = middle;
//                middle = end;
//            }
//            if (result.size() > 2) {
//                end = result.get(1);
//                line = new Line(begin, end);
//                if (line.contains(middle)) result.remove(middle);
//            }
//
//        }
        return new Shape(result);
    }

    private Map<Line, List<Point>> leaveMiddlePoints( Map<Line, List<Point>> collapsed){

    }

    private boolean pointsOnOneLine(Point... points) {
        //2 points  are always on the same line, 1 point too
        if (points.length < 3)
            return true;
        Line line = new Line(points[0], points[1]);
        for (Point point : points) {
            if (!line.contains(point))
                return false;
        }
        return true;
    }
}
