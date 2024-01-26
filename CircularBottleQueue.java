import java.util.NoSuchElementException;
import java.util.Iterator;

public class CircularBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {
  private Bottle[] bottles;
  private int front;
  private int back;
  private int size;

  public CircularBottleQueue(int capacity) {
      if (capacity <= 0) {
          throw new IllegalArgumentException("Invalid capacity: " + capacity);
      }
      
      bottles = new Bottle[capacity];
      front = -1;
      back = -1;
      size = 0;
  }


  public void enqueue(Bottle bottle) {
    if (size == bottles.length) {
      throw new IllegalArgumentException("Full Queue");
    }
    if (bottle == null) {
      throw new NullPointerException("Bottle can not be null");
    }
    
    if (size == 0) {
    bottles[size] = bottle;
    
    size = size + 1;
    front = 0;
    back = 0;
    }
    else {
      if (back == bottles.length - 1) back = 0;
      else back = back + 1;
      
      bottles[back] = bottle;
      size = size + 1;
      
    }
    
    
  }

@Override
  public Bottle dequeue() {
  if (size == 0) {
    throw new NoSuchElementException("Empty Queue");
  }
  if (size == 1) { 
    Bottle temp = bottles[front];
    bottles[front] = null;
    front = -1;
    back = -1;
    size = size - 1;
    return temp;
    
  } 
  else { 
    Bottle temp = bottles[front];
    bottles[front] = null;
    if (front == bottles.length - 1) front = 0;
    else front = front + 1;
    size = size - 1;
    return temp;
    }
  }


  public Bottle peek() throws NoSuchElementException {
      if (isEmpty()) {
          throw new NoSuchElementException("Empty Queue");
      }
      return bottles[front];
  }


  public int size() {
      return size;
  }


  public boolean isEmpty() {
      return size == 0;
  }
  
  public String toString() {
    if (isEmpty()) {
        return "[]";
    }
    String result = "[";
    for (int i = front; i != back; i = (i + 1) % bottles.length) {
        result += bottles[i] + ", ";
    }
    result += bottles[back] + "]";
    return result;
    }
  
  public Iterator<Bottle> iterator() {return new BottleQueueIterator(this); }
  
  public QueueADT<Bottle> copy() {
    CircularBottleQueue copy = new CircularBottleQueue(bottles.length);
    
    for (int i = 0; i < bottles.length; i++) {
      if (bottles[i] != null) copy.enqueue(bottles[i]);
    }
    return copy;
  }
  
  public boolean isFull() {
    return size == bottles.length;
    }
}
