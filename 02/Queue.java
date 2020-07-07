public interface Queue<E> {
  boolean isEmpty();
  void enqueue(E elem);
  E dequeue();
}