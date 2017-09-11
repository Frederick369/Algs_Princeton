/* Frederick Ngo 
 * Week 2 Algorithms
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private Node first, last; 
  private int size = 0;
  
  private class Node
  {
    Item item;
    Node next, prev;
  }
  public Deque()                           // construct an empty deque
  {
     first = null;
     last = null;
     size = 0;
  }
  public boolean isEmpty()                 // is the deque empty?
  {return first == null;}
  public int size()                        // return the number of items on the deque
  { return size; }
  public void addFirst(Item item)          // add the item to the front
  {
     if (item == null) throw new NullPointerException();
     Node oldFirst = first;
     first = new Node();
     first.item = item;
     first.next = oldFirst;
     first.prev = null;
     if(size > 0) oldFirst.prev = first;
     else if (size == 0) last = first;
     size++;
  }
  public void addLast(Item item)           // add the item to the end
  {
     if (item == null) throw new NullPointerException();
     Node oldLast = last;
     last = new Node();
     last.item = item;
     last.next = null;
     last.prev = oldLast;
     if (size == 0) first = last;
     else if (size > 0) oldLast.next = last;
     size++;
  }  
  public Item removeFirst()                // remove and return the item from the front
  {
     if(isEmpty()) throw new NoSuchElementException();
     Item item = first.item;
     first = first.next;
     if(size > 1) first.prev = null;
     if(isEmpty()) last = null;
     size --;
     if(size == 0) {last = null; first = null;}
     return item;
  }
  public Item removeLast()                 // remove and return the item from the end
  {
     if(isEmpty()) throw new NoSuchElementException();
     Item item = last.item;
     last = last.prev;
     if (size > 1) last.next = null;
     else if (last == null) first = null;
     size --;
     if(size == 0){ last = null; first = null; }
     return item;
  }
  public Iterator<Item> iterator()         // return an iterator over items in order from front to end
  {
     return new ListIterator();
  }
  private class ListIterator implements Iterator<Item>
  {
     private Node current = first;
     public boolean hasNext() { return current != null; }
     public void remove() { throw new UnsupportedOperationException(); }
     public Item next()
     {
       if(!hasNext()) throw new NoSuchElementException();
       Item item = current.item;
       current = current.next;
       return item;
     }
  }
  public static void main(String[] args)   // unit testing (optional)
  {}
}