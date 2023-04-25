class Node<T extends Comparable<T>> {
	T data;
	int height;
    Node<T> leftChild;
    Node<T> rightChild;
    Node<T> parent;
	Node<T> next; 


    public Node(T data) {
		this.data = data;
        this.parent = null; // initialize parent to null
        this.height = 1; // initialize height to 1
		this.next = null; // initialize next to null

    }
}