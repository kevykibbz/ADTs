public interface QueueInterface<T> {
    void enqueue(T element);
    T dequeue();
    void clear();
    int size();
    void add(T element);
    boolean isEmpty();
}