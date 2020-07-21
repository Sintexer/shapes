package com.ilyabuglakov.shapes.service;


import com.ilyabuglakov.shapes.model.Line;
import com.ilyabuglakov.shapes.model.Point;
import com.ilyabuglakov.shapes.model.Shape;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShapeService {
    public Shape removeExcessPoints(Shape shape) {
        List<Point> result = new LinkedList<>();
        result.addAll(new LinkedHashSet<>(shape.getPoints()));
        int length = result.size();
        if(length<3)
            return new Shape(result);
        Map<Line, List<Point>> collapsed = new HashMap<>();

        for (int i = 0; i < length; ++i) {
            int j = (i+1)%length;
            int k = (i+2)%length;
            if(pointsOnOneLine(result.get(i), result.get(j), result.get(k))){
                Line line = new Line(result.get(i), result.get(j));
                collapsed.put(line, Arrays.asList(result.get(i), result.get(j), result.get(k)));
            }
        }

        collapsed = leaveMiddlePoints(collapsed);
        for(List<Point> points: collapsed.values()){
            System.out.println(points);
            result.removeAll(points);
        }
        return new Shape(result);
    }

    private Map<Line, List<Point>> leaveMiddlePoints( Map<Line, List<Point>> collapsed){
        for(Line key: collapsed.keySet()){
            List<Point> current = collapsed.get(key);
            Comparator<Point> comparatorByY = (p1, p2) -> (int)Math.ceil((p1.getY()-p2.getY())),
                    comparatorByX = (p1, p2) -> (int)Math.ceil((p1.getX()-p2.getX()));
            if(key.isXConst())
                current.sort(comparatorByY);
            else
                current.sort(comparatorByX);
            collapsed.put(key, current.subList(1, current.size()-1));
        }
        return collapsed;
    }

    private boolean pointsOnOneLine(Point... points) {
        //2 points are always on the same line, 1 point too
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
