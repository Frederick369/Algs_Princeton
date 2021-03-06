/* Frederick Ngo
 * Week 2 Algorithms
 */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Permutation{
  public static void main (String[]args){
    
    int k = Integer.parseInt(args[0]);
    
    RandomizedQueue<String> per = new RandomizedQueue<String>();
    while(!StdIn.isEmpty()){
      per.enqueue(StdIn.readString());
    }
    Iterator<String> iterate = per.iterator();
    
    for(int i = 0; i < k; i++)
    {
      StdOut.println(iterate.next());
    }
  }
}