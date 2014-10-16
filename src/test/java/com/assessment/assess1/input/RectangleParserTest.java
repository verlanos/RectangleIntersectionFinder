package com.assessment.assess1.input;

import com.assessment.assess1.exception.InsufficientPointsException;
import com.assessment.assess1.model.Rectangle;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RectangleParserTest {
    List<String> rectangleInput;
    String samplePath = "sample.txt";
    RectangleParser parser;

    @org.junit.Before
    public void setUp() throws Exception {
        URL resourceUrl = getClass().getResource("/sample.txt");
        Path path = Paths.get(resourceUrl.toURI());
        Charset charset = Charset.defaultCharset();
        rectangleInput = Files.readAllLines(path,charset);
        parser = new RectangleParser();
    }

    @Test
    public void parseTest() throws Exception {
        List<Rectangle> expectedRectangles = createRectangles();

        List<Rectangle> actualRectangles = parser.parse(rectangleInput);
        assertEquals(expectedRectangles.get(0),actualRectangles.get(0));
        assertEquals(expectedRectangles.get(1),actualRectangles.get(1));
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    private List<Rectangle> createRectangles() throws InsufficientPointsException {
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
}