package com.assessment.assess1.input;

import com.assessment.assess1.exception.InsufficientPointsException;
import com.assessment.assess1.model.Rectangle;
import org.junit.Test;

public class RectangleBuilderTest {

    @Test(expected = InsufficientPointsException.class)
    public void testBuildWithoutPoint() throws Exception {
        int x1 = 0;
        int y1 = 1;
        int x2 = 1;
        int y2 = 1;
        int x3 = 1;
        int y3 = 0;

        Rectangle.Builder succeedingBuilder = new Rectangle.Builder();
        Rectangle rectangle = succeedingBuilder
                .pointOne(x1,y1)
                .pointTwo(x2,y2)
                .pointThree(x3,y3)
                .build();
    }
}