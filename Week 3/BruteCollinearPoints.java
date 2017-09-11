/* Frederick Ngo
 * Week 3 Algorithms
 */
import java.util.*;
import edu.princeton.cs.algs4.*;

public class BruteCollinearPoints{
    private ArrayList<LineSegment> lines;
    
    public BruteCollinearPoints(Point[] points) // finds all line segments containing 4 points
    {
      lines = new ArrayList<LineSegment>();
      Point[] copy = points.clone();
      Arrays.sort(copy);     
      
      for (int i = 0; i < copy.length - 1; i++) {
        for (int j = i + 1; j < copy.length; j++) {
          if (copy[i].compareTo(copy[j]) == 0) {
            throw new IllegalArgumentException();
          }
        }
      }
      
      for(int i = 0; i < copy.length-3; i++){
        for(int j = i +1; j < copy.length-2; j++){
          for(int k = j+1; k < copy.length-1; k++){
            for(int l = k+1; l < copy.length; l++){
              if(copy[i].slopeTo(copy[j]) == copy[i].slopeTo(copy[k]) && 
              copy[i].slopeTo(copy[j]) == copy[i].slopeTo(copy[l])) {
                addNew(new LineSegment(copy[i], copy[l]));}
            }
          }
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
    public int numberOfSegments() // the number of line segments
    { return lines.size(); }
    public LineSegment[] segments() // the line segments
    {
      LineSegment[] segs = new LineSegment[lines.size()];
      for (int i = 0 ; i < segs.length ; i++ ){ segs[i] = lines.get(i);}
      return segs;
    }
    public static void main(String[]args)
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
      BruteCollinearPoints collinear = new BruteCollinearPoints(points);
      for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
      }
      StdDraw.show();
    }
}