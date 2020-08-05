package com.ilyabuglakov.shapes.service;


import com.ilyabuglakov.shapes.model.line.Line;
import com.ilyabuglakov.shapes.model.line.AnyLine;
import com.ilyabuglakov.shapes.model.shape.Point;
import com.ilyabuglakov.shapes.model.shape.Shape;
import com.ilyabuglakov.shapes.model.line.VerticalLine;
import com.ilyabuglakov.shapes.repository.ShapesCrudRepository;
import com.ilyabuglakov.shapes.validator.PointsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShapeService {

    @Autowired
    private PointsValidator pointsValidator;

    public Shape removeExcessPoints(Shape shape) {                                                  //Remove points that don't change the lines of the shape
        List<Point> result = new LinkedList<>(new LinkedHashSet<>(shape.getPoints().stream()        //LinkedHashSet removes identical points and don't brake the order in collection
                .filter(point -> pointsValidator.validate(point)).collect(Collectors.toList())));   //All points go through validation
        int length = result.size();
        if(length<3)                                                                                //There are no excess points in dots and lines
            return new Shape(result);
        Map<Line, List<Point>> collapsed = new HashMap<>();                                         //Map contain Lines and points, that are placed on that line

        for (int i = 0; i < length; ++i) {                                                          //i, j and k - indexes of three dots
            int j = (i+1)%length;
            int k = (i+2)%length;
            if(pointsOnOneLine(result.get(i), result.get(j), result.get(k))){
                Line line = Line.valueOf(result.get(i), result.get(j));                                 //if three points are on the same line, this line can be formed from any 2 of them
                collapsed.put(line, Arrays.asList(result.get(i), result.get(j), result.get(k)));
            }
        }

        leaveMiddlePoints(collapsed);
        for(List<Point> points: collapsed.values()){
            result.removeAll(points);
        }
        return new Shape(result);
    }

    private void leaveMiddlePoints(Map<Line, List<Point>> collapsed){                               //Method removes only extreme points
        for(Line key: collapsed.keySet()){
            List<Point> current = collapsed.get(key);
            Comparator<Point> comparatorByY = (p1, p2) -> (int)Math.ceil((p1.getY()-p2.getY())),
                    comparatorByX = (p1, p2) -> (int)Math.ceil((p1.getX()-p2.getX()));
            if(key instanceof VerticalLine)
                current.sort(comparatorByY);
            else
                current.sort(comparatorByX);
            collapsed.put(key, current.subList(1, current.size()-1));
        }
    }

    private boolean pointsOnOneLine(Point... points) {
        //2 points are always on the same line, 1 point too
        if (points.length < 3)
            return true;
        AnyLine anyLine = new AnyLine(points[0], points[1]);
        for (Point point : points) {
            if (!anyLine.contains(point))
                return false;
        }
        return true;
    }
}
