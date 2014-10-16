package com.assessment.assess1.input;

import com.assessment.assess1.exception.InsufficientPointsException;
import com.assessment.assess1.model.Rectangle;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RectangleTest {

    @Test
    public void testIntersectsWithAxisAlignedRectangles() throws Exception {
        List<Rectangle> intersectingAxisAlignedRectangles = createIntersectingAxisAlignedRectangles();
        Rectangle rec1 = intersectingAxisAlignedRectangles.get(0);
        Rectangle rec2 = intersectingAxisAlignedRectangles.get(1);
        boolean intersection = rec1.intersects(rec2);
        Assert.assertTrue(intersection);
    }

    @Test
    public void testNoIntersectsWithAxisAlignedRectangles() throws Exception {
        List<Rectangle> disjointAxisAlignedRectangles = createDisjointAxisAlignedRectangles();
        Rectangle rec1 = disjointAxisAlignedRectangles.get(0);
        Rectangle rec2 = disjointAxisAlignedRectangles.get(1);
        boolean intersection = rec1.intersects(rec2);
        Assert.assertFalse(intersection);
    }

    @Test
    public void testIntersectsWithNonAxisAlignedRectangles() throws Exception {
        List<Rectangle> intersectingNonAxisAlignedRectangles = createIntersectingNonAxisAlignedRectangles();
        Rectangle rec1 = intersectingNonAxisAlignedRectangles.get(0);
        Rectangle rec2 = intersectingNonAxisAlignedRectangles.get(1);
        boolean intersection = rec1.intersects(rec2);
        Assert.assertTrue(intersection);
    }

    @Test
    public void testNoIntersectsWithNonAxisAlignedRectangles() throws Exception {
        List<Rectangle> disjointNonAxisAlignedRectangles = createDisjointNonAxisAlignedRectangles();
        Rectangle rec1 = disjointNonAxisAlignedRectangles.get(0);
        Rectangle rec2 = disjointNonAxisAlignedRectangles.get(1);
        boolean intersection = rec1.intersects(rec2);
        Assert.assertFalse(intersection);
    }

    private List<Rectangle> createIntersectingAxisAlignedRectangles() throws InsufficientPointsException {
        List<Rectangle> rectangles = Lists.newArrayList();
        rectangles.add(new Rectangle.Builder()
                .pointOne(0, 1)
                .pointTwo(1, 1)
                .pointThree(1, 0)
                .pointFour(0, 0)
                .build());

        rectangles.add(new Rectangle.Builder()
                .pointOne(0, 2)
                .pointTwo(2, 2)
                .pointThree(2, 0)
                .pointFour(0, 0)
                .build());
        return rectangles;
    }

    private List<Rectangle> createDisjointAxisAlignedRectangles() throws InsufficientPointsException {
        List<Rectangle> rectangles = Lists.newArrayList();
        rectangles.add(new Rectangle.Builder()
                .pointOne(0, 3)
                .pointTwo(1, 3)
                .pointThree(3, 1)
                .pointFour(3, 0)
                .build());

        rectangles.add(new Rectangle.Builder()
                .pointOne(4, 2)
                .pointTwo(6, 2)
                .pointThree(6, 0)
                .pointFour(4, 0)
                .build());
        return rectangles;
    }

    private List<Rectangle> createIntersectingNonAxisAlignedRectangles() throws InsufficientPointsException {
        List<Rectangle> rectangles = Lists.newArrayList();
        rectangles.add(new Rectangle.Builder()
                .pointOne(1, 4)
                .pointTwo(4, 7)
                .pointThree(7, 4)
                .pointFour(4, 1)
                .build());

        rectangles.add(new Rectangle.Builder()
                .pointOne(3, 5)
                .pointTwo(5, 7)
                .pointThree(7, 5)
                .pointFour(5, 3)
                .build());
        return rectangles;
    }

    private List<Rectangle> createDisjointNonAxisAlignedRectangles() throws InsufficientPointsException {
        List<Rectangle> rectangles = Lists.newArrayList();
        rectangles.add(new Rectangle.Builder()
                .pointOne(1, 2)
                .pointTwo(2, 3)
                .pointThree(3, 2)
                .pointFour(2, 1)
                .build());

        rectangles.add(new Rectangle.Builder()
                .pointOne(2, 4)
                .pointTwo(4, 6)
                .pointThree(6, 4)
                .pointFour(4, 2)
                .build());

        return rectangles;
    }
}