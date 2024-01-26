import java.util.Iterator;
import java.util.NoSuchElementException;

public class BottleQueueIterator implements Iterator<Bottle> {
  private QueueADT<Bottle> bottleQueue;

  public BottleQueueIterator(QueueADT<Bottle> queue) {
      if (queue == null) {
          throw new IllegalArgumentException("Queue cannot be null.");
      }
      
      this.bottleQueue = queue.copy();
      
  }
  
  @Override
  public boolean hasNext() {
      return !bottleQueue.isEmpty();
  }
  
  @Override
  public Bottle next() {
      if (!hasNext()) {
          throw new NoSuchElementException();
      }
      return bottleQueue.dequeue();
  }
  
}
