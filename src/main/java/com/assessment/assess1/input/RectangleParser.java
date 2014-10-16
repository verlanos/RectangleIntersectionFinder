package com.assessment.assess1.input;

import com.assessment.assess1.exception.InsufficientPointsException;
import com.assessment.assess1.model.Rectangle;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Class for building Rectangle instances from parsed Strings
 *
 * @author sefverl.balasingam@outlook.com
 */
public class RectangleParser {
    private static final String VALUE_SEP = " ";

    public List<Rectangle> parse(Iterable<String> toParse) throws InsufficientPointsException {
        List<Rectangle> rectangles = Lists.newArrayList();
        Rectangle.Builder rectangleBuilder;
        for(String line : toParse) {
            rectangleBuilder = new Rectangle.Builder();
            String[] values = line.split(VALUE_SEP);
            int x = 0;
            int y;
            for (int index = 1; index < values.length+1; index++) {
                if((index % 2) == 0) {
                    y = Integer.parseInt(values[index - 1]);
                    if (index == 8) {
                        rectangleBuilder.pointFour(x, y);
                    } else if (index == 6) {
                        rectangleBuilder.pointThree(x, y);
                    } else if (index == 4) {
                        rectangleBuilder.pointTwo(x, y);
                    } else {
                        rectangleBuilder.pointOne(x, y);
                    }
                }
                else {
                    x = Integer.parseInt(values[index-1]);
                }
            }
            rectangles.add(rectangleBuilder.build());
        }
        return rectangles;
    }
}
