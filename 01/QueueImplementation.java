import java.util.ArrayList;

public class QueueImplementation<E> implements Queue<E> {

  private ArrayList<E> queue;

  public QueueImplementation(){
    queue = new ArrayList<E>();
  }

  public boolean isEmpty(){
    return (queue.size()==0);
  }

  public void enqueue(E elem){
    queue.add(elem);
  }

  public E dequeue(){
    return queue.remove(0);
  }
}