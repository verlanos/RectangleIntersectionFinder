package com.assessment.assess1.model;

import com.assessment.assess1.exception.InsufficientPointsException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Class that simulates a rectangle
 *
 * @author sefverl.balasingam@outlook.com
 */
public class Rectangle {
    private Point[] points;

    public Rectangle(Builder builder) {
        this.points = builder.points;
    }

    public List<Point> getPoints() {
        return Arrays.asList(points);
    }

    /**
     * Test if there is an intersection between this rectangle and another rectangle
     * @param other potential Rectangle that might intersect
     * @return true if there is an intersection, false otherwise
     */
    public boolean intersects(Rectangle other) {
        Rectangle[] rectangles = new Rectangle[]{this,other};
        // Uses Separating Axis Theorem
        for(Rectangle rectangle : rectangles) {
            List<Point> vertices = rectangle.getPoints();
            for(int i = 0; i<vertices.size(); i++) {
                int j = (i + 1) % vertices.size();
                Point p1 = vertices.get(i);
                Point p2 = vertices.get(j);

                Point normal = new Point(p2.y - p1.y,p2.x-p1.x);

                Double minA = Double.MIN_VALUE,maxA = null;
                for(Point p : this.getPoints()) {
                    double projected = normal.x * p.x + normal.y * p.y;
                    if(minA == null || projected < minA) {
                        minA = projected;
                    }
                    if(maxA == null || projected > maxA) {
                        maxA = projected;
                    }
                }

                Double minB = null, maxB = null;
                for(Point p : other.getPoints()) {
                    double projected = normal.x * p.x + normal.y * p.y;
                    if(minB == null || projected < minB) {
                        minB = projected;
                    }
                    if(maxB == null || projected > maxB) {
                        maxB = projected;
                    }
                }
                if(maxA < minB || maxB < minA) return false;
            }
        }
        return true;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(this,object,false);
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this,false);
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Point point : points) {
            stringBuilder.append(String.format("(%d,%d) ",point.x,point.y));
        }
        return stringBuilder.append("\n").toString();
    }
    /**
     * Builder pattern implementation for Rectangle
     */
    public static class Builder {
        protected Point points[];
        private boolean hasAllPoints[];
        public Builder() {
            points = new Point[4];
            hasAllPoints = new boolean[4];
        }

        /**
         * Set the first 2D Point
         * @param x x coordinate
         * @param y y coordinate
         * @return current instance of Builder
         */
        public Builder pointOne(int x, int y) {
            points[0] = new Point(x,y);
            hasAllPoints[0] = true;
            return this;
        }

        /**
         * Set the second 2D point
         * @param x x coordinate
         * @param y y coordinate
         * @return current instance of Builder
         */
        public Builder pointTwo(int x, int y) {
            points[1] = new Point(x,y);
            hasAllPoints[1] = true;
            return this;
        }

        /**
         * Set the third 2D point
         * @param x x coordinate
         * @param y y coordinate
         * @return current instance of Builder
         */
        public Builder pointThree(int x, int y) {
            points[2] = new Point(x,y);
            hasAllPoints[2] = true;
            return this;
        }

        /**
         * Set the fourth 2D point
         * @param x x coordinate
         * @param y y coordinate
         * @return current instance of Builder
         */
        public Builder pointFour(int x, int y) {
            points[3] = new Point(x,y);
            hasAllPoints[3] = true;
            return this;
        }

        /**
         * Builds a Rectangle from given 2D points
         * @return an instance of a Rectangle
         * @throws InsufficientPointsException if the builder has not been provided with all four points
         */
        public Rectangle build() throws InsufficientPointsException {
            for(int index = 0; index<hasAllPoints.length; index++) {
                if(!hasAllPoints[index]) {
                    throw new InsufficientPointsException("Point "+index+" not given");
                }
            }
            return new Rectangle(this);
        }
    }
}


