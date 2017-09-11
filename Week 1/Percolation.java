/**
 *
 * @author Frederick
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int[][] map;
    private WeightedQuickUnionUF grid;
    private int length, open;
      
    public Percolation(int n) // create n-n grid, with all sites blocked
    {
        if (n < 1) throw new IllegalArgumentException();
        map = new int[n][n];
        length = n;
        open = 0;
        grid = new WeightedQuickUnionUF(n*n+2); // two more for 2 virtual sites

        for (int r = 0; r < n; r++)
        {
            for (int c = 0; c < n; c++)
            {
                map[r][c] = -1; // -1 for blocked
            }
        }
    }
    public void open(int row, int col)    // open site (row, col) if it is not open already
    { 
        if (row < 1 || col < 1 || row > length || col > length)
          throw new IndexOutOfBoundsException();
        if (map[row-1][col-1] == -1)
        {
          map[row-1][col-1] = 1; // open on map
          open++;
          if (row == 1) grid.union((row-1)*length+(col-1), length*length); // connect to top virtual port
          if (row == length) { grid.union((row-1)*length+(col-1), length*length+1); } // connect to bot virtual port
          // check surroundings, while checking for outofbounds errors
          if (row < length && map[row][col-1] != -1) grid.union((row-1)*length+(col-1), (row)*length+(col-1));
          if (row > 1 && map[row-2][col-1] != -1) grid.union((row-1)*length+(col-1), (row-2)*length+(col-1));
          if (col < length && map[row-1][col] != -1) grid.union((row-1)*length+(col-1), (row-1)*length+(col));
          if (col > 1 && map[row-1][col-2] != -1) grid.union((row-1)*length+(col-1), (row-1)*length+(col-2));      
        }
    }
    public boolean isOpen(int row, int col)  // is site (row, col) open?
    { return map[row-1][col-1] != -1; }
    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if (row < 1 || col < 1 || row > length || col > length)
          throw new IndexOutOfBoundsException();
        return grid.connected((row-1)*length+(col-1), length*length);
    }
    public int numberOfOpenSites()       // number of open sites
    {
        return open;
    }
    public boolean percolates()              // does the system percolate?
    {
        return grid.connected(length*length, length*length+1); // checking top/bot virtual ports
    }
    public static void main(String[] args) {
    }
    
}
