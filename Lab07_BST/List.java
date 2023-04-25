import java.util.*;

class List<T extends Comparable<T>> {

    /** Count of elements in the List */
    int count = 0;

    /** Root node of the binary tree */
    Node<T> root;
    
	private ArrayList<T> list;

    /** Comparator for ordering tree. Uses compareTo of T by default */
    Comparator<T> ordering = new Comparator<T>() {
        @Override
        public int compare(T object1, T object2) {
            return object1.compareTo(object2);
        }
    };

    /** Sentinal for leaf nodes. This node should be both the left and right child of all leaf nodes */
    

    public List() {

    }

    public List(Comparator<T> c) {
        ordering = c;
    }

    // create an object of BinaryTree
    public void add(Track t) {
       // create nodes of the tree
	   Node<T> dummy = new Node<T>(t.popularity, t.title);
	   // If there is no root this becomes root

	   if (root == null) {
		   root = dummy;
	   }else{
		// Set root as the Node we will start
			// with as we traverse the tree

			Node<T>  focusNode = root;

			// Future parent for our new Node

			Node<T>  parent;

			while (true) {

				// root is the top parent so we start
				// there

				parent = focusNode;

				// Check if the new node should go on
				// the left side of the parent node

				if (t.popularity < focusNode.key) {

					// Switch focus to the left child

					focusNode = focusNode.leftChild;

					// If the left child has no children

					if (focusNode == null) {

						// then place the new node on the left of it

						parent.leftChild = dummy;
						return; // All Done

					}

				} else { // If we get here put the node on the right

					focusNode = focusNode.rightChild;

					// If the right child has no children

					if (focusNode == null) {

						// then place the new node on the right of it

						parent.rightChild = dummy;
						return; // All Done

					}

				}

			}
	   }
    }
    
    public Node<T> find(int popularity) {
        // Start at the top of the tree

		Node<T> focusNode = root;

		// While we haven't found the Node
		// keep looking

		while (focusNode.key != popularity) {

			// If we should search to the left

			if (popularity < focusNode.key) {

				// Shift the focus Node to the left child

				focusNode = focusNode.leftChild;

			} else {

				// Shift the focus Node to the right child

				focusNode = focusNode.rightChild;

			}

			// The node wasn't found

			if (focusNode == null)
				return null;

		}

		return focusNode;
    }
    
    public T find(T value, Comparator<T> comp) {
        Node<T> current = root;

        while (current != null) {
            int cmp = comp.compare(value, current.data);

            if (cmp == 0) {
                return current.data;
            } else if (cmp < 0) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }

        return null;
    }
    
    public T min() {
        if (list.isEmpty()) {
            return null;
        }

        T min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            T current = list.get(i);
            if (current.compareTo(min) < 0) {
                min = current;
            }
        }
        return min;
    }

    public T max() {
        if (list.isEmpty()) {
            return null;
        }

        T max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            T current = list.get(i);
            if (current.compareTo(max) > 0) {
                max = current;
            }
        }
        return max;
    }

    T[] toArray() {
        // create an array to hold the tree elements
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Comparable[count()];
        int index = 0;
        
        // traverse the tree recursively in inorder
        inorder(root, array, index);
        
        return array;
    }

	private int inorder(Node<T> node, T[] array, int index) {
        if (node == null) {
            return index;
        }
        
        // traverse left subtree
        index = inorder(node.leftChild, array, index);
        
        // add current node to array
        array[index] = node.data;
        index++;
        
        // traverse right subtree
        index = inorder(node.rightChild, array, index);
        
        return index;
    }

	private int count() {
        // count the number of elements in the tree recursively
        return count(root);
    }
    
    private int count(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + count(node.leftChild) + count(node.rightChild);
        }
    }

	public void inOrderTraverseTree(Node<T> focusNode) {

		if (focusNode != null) {

			// Traverse the left node

			inOrderTraverseTree(focusNode.leftChild);

			// Visit the currently focused on node

			System.out.println(focusNode);

			// Traverse the right node

			inOrderTraverseTree(focusNode.rightChild);

		}

	}

	public void preorderTraverseTree(Node<T> focusNode) {

		if (focusNode != null) {

			System.out.println(focusNode);

			preorderTraverseTree(focusNode.leftChild);
			preorderTraverseTree(focusNode.rightChild);

		}

	}

	public void postOrderTraverseTree(Node<T> focusNode) {

		if (focusNode != null) {

			postOrderTraverseTree(focusNode.leftChild);
			postOrderTraverseTree(focusNode.rightChild);

			System.out.println(focusNode);

		}

	}

  } // end class List

