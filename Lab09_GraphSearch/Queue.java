import java.util.*;


public class Queue<T> implements QueueInterface<T> {
    private int head;
    private int tail;
    private T[] items;

    public Queue(int capacity) {
        items = (T[]) new Object[capacity];
        head = 0;
        tail = 0;
    }

    public void push(T value) {
        if (tail == items.length) {
            // If the tail has reached the end of the array, wrap around to the beginning
            tail = 0;
        }
        items[tail++] = value;
    }


    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T item = items[head];
        items[head++] = null;
        if (head == items.length) {
            // If the head has reached the end of the array, wrap around to the beginning
            head = 0;
        }
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return items[head];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = head; i != tail; i = nextIndex(i)) {
            sb.append(items[i]);
            if (nextIndex(i) != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private int nextIndex(int i) {
        return (i + 1) % items.length;
    }

    

    
    public void clear() {
        head = 0;
        tail = 0;
        items = (T[]) new Object[items.length];
    }

    public int size() {
        return tail >= head ? tail - head : items.length - head + tail;
    }

    @Override
    public void enqueue(T element) {
        push(element);
    }

    @Override
    public T dequeue() {
        return pop();
    }

    @Override
    public void add(T element) {
        push(element);
    }
}
