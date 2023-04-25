import java.util.*;

public class Stack<T> implements StackInterface<T> {
    private int count;
    private T[] items;

    public Stack(int capacity) {
        items = (T[]) new Object[capacity];
        count = 0;
    }

    public void push(T value) {
        if (count == items.length) {
            // If the stack is full, resize the array
            resizeArray(items.length * 2);
        }
        items[count++] = value;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T item = items[--count];
        items[count] = null; // Avoid memory leak
        if (count > 0 && count == items.length / 4) {
            // If the stack is less than 25% full, resize the array
            resizeArray(items.length / 2);
        }
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return items[count - 1];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < count; i++) {
            sb.append(items[i]);
            if (i < count - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void resizeArray(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < count; i++) {
            newArray[i] = items[i];
        }
        items = newArray;
    }

    @Override
    public void enqueue(T element) {
        push((T) element);
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        // Traverse the stack and find the first item of type MazeNode
        for (int i = count - 1; i >= 0; i--) {
            if (items[i] instanceof MazeNode) {
                MazeNode item = (MazeNode) items[i];
                // Remove the item from the stack and return it
                for (int j = i; j < count - 1; j++) {
                    items[j] = items[j + 1];
                }
                count--;
                items[count] = null; // Avoid memory leak
                return (T) item;
            }
        }
        throw new NoSuchElementException("Queue is empty");
    }

    @Override
    public void clear() {
        for (int i = 0; i < count; i++) {
            items[i] = null;
        }
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void add(T element) {
        push(element);
    }

}
