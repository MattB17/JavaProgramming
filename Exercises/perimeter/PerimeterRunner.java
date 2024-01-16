import edu.duke.*;
import java.io.File;

public class PerimeterRunner {
    public int getNumPoints(Shape s) {
      // Start with numPoints = 0;
      int numPoints = 0;
      // For each point currPt in the shape,
      for (Point currPt : s.getPoints()) {
        // Increase the numPoints count by 1
        numPoints = numPoints + 1;
      }
      // numPoints is the answer
      return numPoints;
    }

    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public double getLargestSide(Shape s) {
        double longestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > longestSide) {
                longestSide = currDist;
            }
            prevPt = currPt;
        }
        return longestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = 0.0;
        for (Point currPt : s.getPoints()) {
            if (currPt.getX() > largestX) {
                largestX = currPt.getX();
            }
        }
        return largestX;
    }

    public double getAverageLength(Shape s) {
        // get the perimeter
        double perim = getPerimeter(s);
        // get the number of points (which is the same as number of sides)
        double numPoints = getNumPoints(s);
        // the average length is the ratio of the two
        return perim / ((double) numPoints);
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int pointCount = getNumPoints(s);
        System.out.println("Number of points = " + pointCount);
        double averageLength = getAverageLength(s);
        System.out.println("Average side length = " + averageLength);
        double longestSide = getLargestSide(s);
        System.out.println("Largest side = " + longestSide);
        double largestX = getLargestX(s);
        System.out.println("Largest X = " + largestX);
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        for (File f : dr.selectedFiles()) {
          FileResource fr = new FileResource(f);
          Shape s = new Shape(fr);
          double currPerimeter = getPerimeter(s);
          if (currPerimeter > largestPerimeter) {
              largestPerimeter = currPerimeter;
          }
        }
        return largestPerimeter;
    }

    public void testGetLargestPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter: " + largestPerimeter);
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        String fileName = "";
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerimeter = getPerimeter(s);
            if (currPerimeter > largestPerimeter) {
                largestPerimeter = currPerimeter;
                fileName = f.getName();
            }
        }
        return fileName;
    }

    public void testGetFileWithLargestPerimeter() {
        String largestPerimFile = getFileWithLargestPerimeter();
        System.out.print("File with largest perimeter: " + largestPerimFile);
    }

    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        // pr.testPerimeter();
        // pr.testGetLargestPerimeterMultipleFiles();
        pr.testGetFileWithLargestPerimeter();
    }
}
