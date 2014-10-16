package com.assessment.assess1;

import com.assessment.assess1.exception.InsufficientPointsException;
import com.assessment.assess1.input.RectangleParser;
import com.assessment.assess1.model.Rectangle;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class for handling command line parameters and returning rectangle intersections as console output
 *
 * @author sefverl.balasingam@outlook.com
 */

public class Main {

    private void notifyIntersections(List<Rectangle> rectangles) {
        for(int i = 0; i<rectangles.size(); i++) {
            for(int j = i; j<rectangles.size(); j++) {
                if(!rectangles.get(i).equals(rectangles.get(j)) && rectangles.get(i).intersects(rectangles.get(j))) {
                    System.out.println(String.format("Rectangle %d %s intersects Rectangle %d %s",
                            i+1,
                            rectangles.get(i).toString(),
                            j+1,
                            rectangles.get(j).toString()
                    ));
                }
            }
        }
    }

    private List<Rectangle> importRectangles(String filePath) {
        Path path = Paths.get(filePath);
        Charset charset = Charset.defaultCharset();
        RectangleParser rectangleParser = new RectangleParser();
        try {
            List<String> fileInput = Files.readAllLines(path, charset);
            List<Rectangle> rectangles = rectangleParser.parse(fileInput);
            return rectangles;
        } catch (IOException e) {
            System.err.println("File `" + filePath + "` could not be opened for reading");
        } catch (InsufficientPointsException e) {
            System.err.println("File `" + filePath + "` did not contain sufficient points to create rectangle(s)");
        }
        return Lists.newArrayList();
    }

    public static void main(String[] args) {
        Main main = new Main();
	    if(args.length < 1) {
            System.out.println("No file to parse!\nExiting...");
            System.exit(0);
        }
        List<Rectangle> rectangles = main.importRectangles(args[0]);
        main.notifyIntersections(rectangles);
    }
}
