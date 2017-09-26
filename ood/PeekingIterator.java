// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
package ood;
import java.util.*;
/**
  * LC284: PeekingIterator
  * Tags: Design
  * Date: Apr 30, 2017
*/

/**
 * Iterations: firstly used a LinkedList to implement, solutions mostly used another iterator
*/

class PeekingIterator1 implements Iterator<Integer> {

    private List<Integer> list;

  	public PeekingIterator1(Iterator<Integer> iterator) {
  	    // initialize any member here.
  	    list = new LinkedList<>();
  	    while (iterator.hasNext()) list.add(iterator.next());
  	}

      // Returns the next element in the iteration without advancing the iterator.
  	public Integer peek() {
          return list.get(0);
  	}

  	// hasNext() and next() should behave the same as in the Iterator interface.
  	// Override them if needed.
  	@Override
  	public Integer next() {
  	    Integer i = list.get(0);
  	    list.remove(0);
  	    return i;
  	}

  	@Override
  	public boolean hasNext() {
  	    return !list.isEmpty();
  	}
}

public class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> it;
    private Integer n;

  	public PeekingIterator(Iterator<Integer> iterator) {
  	    // initialize any member here.
  	    it = iterator;
        if (it.hasNext()) n = it.next();
  	}

      // Returns the next element in the iteration without advancing the iterator.
  	public Integer peek() {
          return n;
  	}

  	// hasNext() and next() should behave the same as in the Iterator interface.
  	// Override them if needed.
  	@Override
  	public Integer next() {
  	    Integer res = n;
  	    if (it.hasNext()) n = it.next();
        else n = null; /// important! otherwise cannot get a null
  	    return res;
  	}

  	@Override
  	public boolean hasNext() {
  	    return n != null;
  	}
}
