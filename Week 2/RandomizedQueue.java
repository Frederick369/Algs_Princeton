/* Frederick Ngo
 * Week 2 Algorithms
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

 public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] list;
   private int start, size;
   public RandomizedQueue(){                 // construct an empty randomized queue
     list = (Item[]) new Object[1];
     start = 0;
     size = 0;
   }
   public boolean isEmpty(){ return size == 0;}                 // is the queue empty?
   public int size() { return size; }                        // return the number of items on the queue
   private void expand() // expands the array
   {
     Item[] oldList = list;
     list = (Item[]) new Object[list.length*2];
     for(int i = 0; i < size; i++)
     { 
       list[i] = oldList[i+start];
     }
     start = 0;
   }
   private void retract() // lowers size of array
   {
     Item[] oldList = list;
     list = (Item[]) new Object[list.length/2];
     for(int i = 0; i < size; i++)
     {
       list[i] = oldList[i+start];
     }
     start = 0;
   }
   public void enqueue(Item item)           // add the item
   {
     if (item == null) throw new NullPointerException();
       
     if (start + size + 1 >= list.length) expand();
     
     list[size+start] = item;
     size++;
   }
   public Item dequeue()                    // remove and return a random item
   {
     if(isEmpty()) throw new NoSuchElementException();
     // swap with first element and move "start" over 1 place
     int rand = (int)(StdRandom.uniform(size,size+start+1));
     Item temp = list[rand];
     list[rand] = list[start];
     list[start] = null;
     start++;
     size--;
     
     if(start+size == list.length/4) retract();
     return temp;
   }
   public Item sample()                     // return (but do not remove) a random item
   {
     if(isEmpty()) throw new NoSuchElementException();
     int rand = (int)(StdRandom.uniform(size,size+start+1));
     return list[rand];
   }
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   {
     return new QueueIterator();
   }
   private class QueueIterator implements Iterator<Item>
   {
     private int[] order;
     private int current;
     
     public QueueIterator(){
       order = StdRandom.permutation(size);
       for(int i = 0; i < size; i++) order[i] += start;
       current = 0;
     }
     public boolean hasNext() { return current < order.length; }
     public void remove() { throw new UnsupportedOperationException(); }
     public Item next()
     {
       if (!hasNext()) throw new NoSuchElementException();
       return (Item)(list[order[current++]]);
     }
   }
   public static void main(String[] args)   // unit testing (optional)
   {}
}