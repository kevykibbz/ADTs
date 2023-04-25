## Lab 9 Breadth First Search(BFS) and Depth First Search (DFS) of Graphs

### Due Friday, April 21 end-of-day
_Zip all files and submit via Moodle_

<hr>

In this lab, you will create the ADT Queue and the ADT Stack using Java's basic array data structure. Both will be implementing the QueueInterface.

The Queue should be implemented using a **Circular Array**, which uses indices to mark the "head" and "tail" of the queue that is stored in the array. You should also use a **dummy space** in this implementation, meaning an extra space that is a buffer between the end of the array and the head (it is always 1 space "before" the head).

> If the indices used for the head and tail of the Queue are equal, then the Queue is empty. If the tail index + 1 would be equal to the head index of the Queue, then the Queue is full.

The Stack should be implemented using an array. For the implementation, both push and pop operate at the end of the elements within the array (not to be confused with array.length).

There are 3 text files provided that represent a maze. Each node is a decision point. Nodes without any children are deadends. One node in the maze is an exit node (there is a boolean _isExit_ set to true for the exit). You will determine the path through the maze from "root" to "exit" using either a Breadth-First Search or a Depth-First Search. The only distinction between them is the type of queue that is used. As part of the assignment, you will keep track of how many nodes are placed on the queue before the exit is found.

<hr>

### Implementation of class Queue

The method descriptions for `public class Queue<T> implements QueueInterface<T>` are:

- `public void push(T value)` : add a value to the end of the Queue.
- `public T pop()` : remove the first element of the Queue.
- `public T peek()` : return the first element of the Queue, but do not remove it.
- `public boolean isEmpty()` : true if Queue is empty.
- `public String toString()` : override Object toString

Your class Queue should have the following private member variables. You may include more than these.
- `private int head` : index of the first element in the Queue
- `private int tail` : index of the location to place the next added value

>Note: you might find it useful to have a helper function that returns the "next" index.

<hr>

### Implementation of class Stack

The method descriptions for `public class Stack<T> implements QueueInterface<T>` are:

- `public void push(T value)` : add a value to the "top" of the Stack.
- `public T pop()` : remove the "top" of the Stack.
- `public T peek()` : return the "top" of the Stack, but do not remove it.
- `public boolean isEmpty()` : true if Stack is empty.
- `public String toString()` : override Object toString

Your class Stack should have the following private member variable. You may include more than these.
- `private int count` : number of elements currently in the stack. All operations occur at the end.

<hr>

### Implementation of class MazeSolver

The method descriptions for `public class MazeSolver` are:
- `public static ArrayList<MazeNode> solve(MazeNode root, String q)`
- `public static int count()`

```
NOTE: Remeber to reset the count at the start of solve!
```

The Breadth-First Search (bfs) and Depth-First Seach (dfs) algorithms are identical except that bfs uses an FIFO Queue and dfs uses a LIFO Queue. Here is the algorithm taking this into account:

```Java
// Create the queue, appropriate for the type of traversal
QueueInterface<MazeNode> queue;
if (q.equals("bfs")) {
    queue = new Queue<>();
} else {
    queue = new Stack<>();
}
// start with the root of the maze on the queue
// while the queue is not empty
    // pop from the queue
    // for each child of that popped node
        // if the exit node
            // set previous
            // return the path from root to this node (using previouses)
        // if not yet visited (previous will be null)
            // push onto the queue
            // set previous
            // increment count
```

The `Main` class provides tests for your maze solver.

<hr>

### Documentation

- Javadocs are professional and complete. They do not describe implementation -- only input and output.
- Comment the code. Not too much, not too little.
- Style compliant.

<hr>

### QUESTIONS

The quality of your answers matter. Please take the time to provide thoughtful, well-written answers.

1. Explain the benefits of using a Circular Array for implementation of the Queue. Why not also use this for a stack?

2. Report on the number of nodes visited when solving the 3 mazes. For some of you, there will be a difference between bfs and dfs in the number of nodes pushed onto the queue (it depends on the ordering in the hash table). Why might one algorithm have to explore fewer nodes than the other to discover the exit?
