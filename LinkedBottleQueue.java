import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {

  private LinkedNode<Bottle> front;
  private LinkedNode<Bottle> back;
  
  private int size;
  private int capacity;
  
  public LinkedBottleQueue(int capacity) throws IllegalArgumentException {
   
    if (capacity <= 0) {
      throw new IllegalArgumentException("The capacity has to be positive");
    }
    
    this.front = null;
    this.back = null;
    this.size = 0;
    this.capacity = capacity;
    
  }
  
  public String toString() {
    String queueStatus = "";
    String fillStatus = "Empty";
    String capStatus = "Open";
    
    
    LinkedNode<Bottle> current = front;
    
    while (current != null) {
      if (current.getData().isFilled() == true) {
        fillStatus = "Filled";
      }
      
      if (current.getData().isCapped() == true) {
        capStatus = "Capped";
      }
      
      queueStatus = queueStatus + current.getData().getSerialNumber() + ":" +
                                  fillStatus + "/" + capStatus + "\n";
      
      current = current.getNext();
    }
    return queueStatus.trim();
  }
  
  public Bottle peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Empty Queue");
    }
    
    return front.getData();
  }
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  public boolean isFull() {
    return size == capacity;
  }
  
  public Bottle dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Empty Queue");
    }
    
    if (size == 1) {
      Bottle queueFront1 = front.getData();
      
      front = null;
      back = null;
      
      size = size - 1;
      
      return queueFront1;
    }
    else {
    Bottle queueFront2 = front.getData();
    
    front = front.getNext();
    
    size = size - 1;
    return queueFront2;
    }
  }
  
  public void enqueue(Bottle bottle) {
    if (bottle == null) {
      throw new NullPointerException("Null Bottle");
    }
    
    if (isFull()) {
      throw new IllegalStateException("Full Queue");
    }
    
    if (size == 0) {
    LinkedNode<Bottle> newNode = new LinkedNode<>(bottle);
    
    front = newNode;
    back = front;
    size = size + 1;
    }
    else {
      LinkedNode<Bottle> newNode = new LinkedNode<>(bottle);
      
      back = newNode;
      
      LinkedNode<Bottle> loop = front;
      
      for (int i = 0; i < size - 1; i++) {
        loop = loop.getNext();
      }
      
      loop.setNext(back);
      
      size = size + 1;
    }
  }
  
  public int size() {
    return size;
  }
  
  public Iterator<Bottle> iterator() {return new BottleQueueIterator(this); }
  
  public QueueADT<Bottle> copy() {
    LinkedBottleQueue returned = new LinkedBottleQueue(capacity);
    
    if (front != null) {
      LinkedNode<Bottle> arrow = front;
      
      while (arrow != null) {
        returned.enqueue(arrow.getData());
        arrow = arrow.getNext();
      }
     }
    return returned;
  }
  
}
