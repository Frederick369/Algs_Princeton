/**
 *
 * @author Frederick
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;

public class PercolationStats {
    private StdStats stats;
    private double mean, dev;
    private double[] runs, stddev;
    private int numRuns;
    private int length;
    private StdRandom random;
    public PercolationStats(int n, int trials) {
        
        runs = new double[trials];
        stddev = new double[trials];
        numRuns = trials;
        length = n;
        for (int i = 0; i < trials; i++)
        {
            Percolation perc = new Percolation(n);
            while (!perc.percolates())
            {
                int r = random.uniform(length)+1;
                int c = random.uniform(length)+1;
                perc.open(r, c);
            }
            runs[i] = perc.numberOfOpenSites()/(double) (length*length);
        }
        mean = stats.mean(runs);
        for (int i = 0; i < trials; i++)
        { stddev[i] = runs[i]  - mean;
          // System.out.println(runs[i] + "  " + stddev[i]);
        }
        dev = stats.stddev(stddev);
    }    // perform trials independent experiments on an n-by-n grid
    public double mean()                       // sample mean of percolation threshold
    {
        return mean;
    }
    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return dev;
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mean - 1.96*dev/Math.sqrt(numRuns);
    }
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return mean + 1.96*dev/Math.sqrt(numRuns);
    }
   public static void main(String[] args)        // test client (described below)
   {     // TODO code application logic here
       int size, trials;
       PercolationStats percstats;
       StdIn sc = null;
       size = sc.readInt();
       trials = sc.readInt();
       if (size < 1 || trials < 1) throw new IllegalArgumentException();
       percstats = new PercolationStats(size, trials);       
       System.out.print("% java PercolationStats "+size+ " " +trials);
       System.out.print("\nmean                    = " + percstats.mean());
       System.out.print("\nstddev                  = " + percstats.stddev());
       System.out.print("\n95% confidence interval = [" + percstats.confidenceLo() + ", " + percstats.confidenceHi()+"]\n\n");
       
   }
    
}
