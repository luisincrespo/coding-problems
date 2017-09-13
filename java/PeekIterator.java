import java.util.Iterator;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Decorates an iterator to support one-element lookahead while iterating.
 */
class PeekIterator<T> implements Iterator<T> {
  private Iterator<T> iterator;
  private T current;

  public PeekIterator(Iterator<? extends T> iterator) {
    this.iterator = iterator;
    this.current = this.getNextCurrent();
  }

  @Override
  public boolean hasNext() {
    return this.current != null;
  }

  public T peek() {
    return this.current;
  }

  @Override
  public T next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException();
    }

    T prevCurrent = current;
    this.current = getNextCurrent();
    return prevCurrent;
  }

  private T getNextCurrent() {
    if (this.iterator.hasNext()) {
      return this.iterator.next();
    }
    return null;
  }

  public static void main(String args[]) {
    List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
    PeekIterator<Integer> peekIterator = new PeekIterator<Integer>(numbers.iterator());

    // Iterate over elements in numbers, using peek() before using next()
    while (peekIterator.hasNext()) {
      System.out.println("Peek: " + peekIterator.peek() + ", Next: " + peekIterator.next());
    }
  }
}
