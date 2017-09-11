/* Frederick Ngo
 * Week 3 Algorithms
 */


import java.util.*;
import edu.princeton.cs.algs4.*;

public class FastCollinearPoints {
   private ArrayList<LineSegment> lines;
   
   public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
   {
      lines = new ArrayList<LineSegment>();
      Point[] copy = points.clone();
      
      for (int i = 0; i < points.length - 1; i++) {
        for (int j = i + 1; j < points.length; j++) {
          if (points[i].compareTo(points[j]) == 0) {
            throw new IllegalArgumentException();
          }
        }
      }
      
     for (Point origin: points)
     {
       Arrays.sort(copy, origin.slopeOrder());
       ArrayList<Point> pointList = new ArrayList<Point>();
      
      double slope = 0;
      double prevSlope = Double.NEGATIVE_INFINITY;
      
      for (int i = 1; i < copy.length; i++) {
        slope = origin.slopeTo(copy[i]);
        
        if (slope == prevSlope)
          pointList.add(copy[i]);
        else {
          if (pointList.size() >= 3) {
            pointList.add(origin);
            Collections.sort(pointList);
            addNew(new LineSegment(pointList.get(0), pointList.get(pointList.size() - 1)));
          }
          pointList.clear();
          pointList.add(copy[i]);
        }
        prevSlope = slope;
      }
      
      if (pointList.size() >= 3) {
        pointList.add(origin);
        Collections.sort(pointList);
        addNew(new LineSegment(pointList.get(0), pointList.get(pointList.size() - 1)));
      }
     }
   }
   private void addNew(LineSegment seg)
   {
      boolean unique = true;
      for (LineSegment l : lines){
      if(l.toString().equals(seg.toString())) unique = false;
      }
      if(unique) lines.add(seg);
   }
   public           int numberOfSegments()        // the number of line segments
   { return lines.size();   }
   public LineSegment[] segments()                // the line segments
   {
     LineSegment[] segs = new LineSegment[lines.size()];
     for(int i = 0; i < segs.length; i++){
       segs[i] = lines.get(i); 
     }
     return segs;
   }
   public static void main (String[]args)
   {
     // read the n points from a file
     In in = new In(args[0]);
     int n = in.readInt();
     Point[] points = new Point[n];
     for (int i = 0; i < n; i++) {
       int x = in.readInt();
       int y = in.readInt();
       points[i] = new Point(x, y);
     }
     
     // draw the points
     StdDraw.enableDoubleBuffering();
     StdDraw.setXscale(0, 32768);
     StdDraw.setYscale(0, 32768);
     for (Point p : points) {
       p.draw();
     }
     StdDraw.show();
     
     // print and draw the line segments
     FastCollinearPoints collinear = new FastCollinearPoints(points);
     for (LineSegment segment : collinear.segments()) {
       StdOut.println(segment);
       segment.draw();
     }
     StdDraw.show();
   }
}