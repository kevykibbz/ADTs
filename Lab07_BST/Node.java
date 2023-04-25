public class Node<T> {
    T data;
	int key;
	String name;

    Node<T> leftChild, rightChild;
    public int height;
    public Node<T> parent;
    public Object next;

    public Node(int value, String name) {
		this.key = value;
		this.name = name;
    }

    public Node(T item) {
        data = item;
        leftChild = rightChild = null;
    }

	public String toString() {

		// return name + " has the key " + key;
		 return name + " has popularity of " + key + "\nLeft Child: " + leftChild +
		 "\nRight Child: " + rightChild + "\n";

	}
}