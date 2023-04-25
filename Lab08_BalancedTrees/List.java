import java.util.*;
import java.util.function.Function;

class List<T extends Comparable<T>> {

    /** Count of elements in the List */
    int count = 0;

    /** Height of the tree */
    private int height = 0;

    /** Root node of the tree */
    private Node<T> root = null;


    /** Comparator for ordering tree. Uses compareTo of T by default */
    Comparator<T> ordering = new Comparator<T>() {
        @Override
        public int compare(T object1, T object2) {
            return object1.compareTo(object2);
        }
    };

    
    /**
     * Returns the height of the tree.
     */
    public int height() {
        return height;
    }
    
    /**
     * Updates the height of a node and the height of the tree if necessary.
     */
    private void updateHeight(Node<T> node) {
        if (node == null) {
            return;
        }

        int leftHeight = (node.leftChild == null) ? -1 : node.leftChild.height;
        int rightHeight = (node.rightChild == null) ? -1 : node.rightChild.height;

        node.height = 1 + Math.max(leftHeight, rightHeight);

        if (node.height > height) {
            height = node.height;
        }
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
    
        // If tree is empty, set newNode as root and increment height
        if (root == null) {
            root = newNode;
            height++;
            return;
        } 

        // Find the appropriate position for the new node
        Node<T> current = root;
        Node<T> parent = null;
        while (current != null) {
            parent = current;
            if (ordering.compare(newNode.data, current.data) < 0) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }

        // Set the parent of the new node
        newNode.parent = parent;
    
        // Insert the new node
        if (ordering.compare(newNode.data, parent.data) < 0) {
            parent.leftChild = newNode;
        } else {
            parent.rightChild = newNode;
        }
    
        // Update height of ancestors and check for balance
        Node<T> ancestor = parent;
        while (ancestor != null) {
            int leftHeight = (ancestor.leftChild != null) ? ancestor.leftChild.height : 0;
            int rightHeight = (ancestor.rightChild != null) ? ancestor.rightChild.height : 0;
            int newHeight = 1 + Math.max(leftHeight, rightHeight);
            if (newHeight == ancestor.height) {
                break; // No need to update height of ancestors further
            }
            ancestor.height = newHeight;
            if (Math.abs(leftHeight - rightHeight) > 10) {
                // Tree is unbalanced, rebalance it
                rebuildTree();
                break;
            }
            ancestor = ancestor.parent;
        }
    }

    
    /**
     * Rebuilds the tree to balance it.
     */
    private void rebuildTree() {
        List<T> list = new List<>();
        flattenTree(root); // Get a sorted list of all nodes in the tree
        root = buildTree(list, 0, list.count - 1); // Rebuild the tree from the sorted list
    }

    /**
     * Flattens the tree into a sorted list of nodes.
     */
    private Node<T> flattenTree(Node<T> node) {
        if (node == null) {
            return null;
        }
        Node<T> left = flattenTree(node.leftChild);
        Node<T> right = flattenTree(node.rightChild);
        node.leftChild = null;
        node.rightChild = null;
        if (left != null) {
            left = addToList(left, node);
        } else {
            left = node;
        }
        if (right != null) {
            return addToList(left, right);
        } else {
            return left;
        }
    }
    

    private Node<T> addToList(Node<T> head, Node<T> node) {
        if (head == null) {
            return node;
        }
        Node<T> current = head;
        while (current.next != null) {
            current = (Node<T>) current.next;
        }
        current.next = node;
        return head;
    }
    /**
     * Builds a balanced binary search tree from a sorted list of nodes.
     */
    private Node<T> buildTree(List<T> list, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node<T> node = new Node<T>(list.get(mid));

        node.leftChild = buildTree(list, start, mid - 1);
        node.rightChild = buildTree(list, mid + 1, end);
        node.height = Math.max((node.leftChild != null) ? node.leftChild.height : 0, (node.rightChild != null) ? node.rightChild.height : 0) + 1;
        return node;
    }

    /**
     * Returns the element at the specified index.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public T get(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> node = getNodeAtIndex(root, index);
        return node.data;
    }

    private Node<T> getNodeAtIndex(Node<T> node, int index) {
        int leftCount = getCount(node.leftChild);
        if (index < leftCount) {
            return getNodeAtIndex(node.leftChild, index);
        } else if (index == leftCount) {
            return node;
        } else {
            return getNodeAtIndex(node.rightChild, index - (leftCount + 1));
        }
    }

    private int getCount(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return getCount(node.leftChild) + getCount(node.rightChild) + 1;
        }
    }

    /** Sentinal for leaf nodes. This node should be both the left and right child of all leaf nodes */
    public List() {
       
    
    }

    public List(Comparator<T> c) {
        ordering = c;
    }

    
    public Node<T> find(T value) {
        return null;
    }
    
    public T find(T value, Comparator<T> comp) {
        return null;
    }
    
    public T min() {
        return null;
    }

    public T max() {
        return null;
    }

    public T[] toArray(Comparator<T> comp) {
        // Create an array of the appropriate size
        T[] array = (T[]) new Comparable[count];
    
        // Fill the array using the existing toArray() method
        toArray(root, array, 0);
    
        // Sort the array using the specified comparator
        Arrays.sort(array, comp);
    
        return array;
    }
    
    private int toArray(Node<T> node, T[] array, int index) {
        if (node != null) {
            // In-order traversal of the tree
            index = toArray(node.leftChild, array, index);
            array[index++] = node.data;
            index = toArray(node.rightChild, array, index);
        }
        return index;
    }
    
    /**
     * Reorder the tree using the specified comparator.
     */
    public void reorder(Comparator<T> comp) {
        ordering = comp;
        balance(null, count, count, comp);
    }

    /**
     * Balance the tree.
     * @param object
     * @param j
     * @param i
     * @param array2
     * @return 
     */
    private Node<T> balance(T[] array2, int i, int j, Object object) {
        T[] array = toArray(ordering);
        return root = balance(array, 0, array.length - 1, null);
    }


    public ArrayList<T> query(Function<T, Boolean> fn) {
        ArrayList<T> result = new ArrayList<>();
        queryHelper(root, fn, result);
        return result;
    }
    
    private void queryHelper(Node<T> node, Function<T, Boolean> fn, ArrayList<T> result) {
        if (node == null) {
            return;
        }
        queryHelper(node.leftChild, fn, result);
        if (fn.apply(node.data)) {
            result.add(node.data);
        }
        queryHelper(node.rightChild, fn, result);
    }

    public Node<T> predecessor(T value) {
        Node<T> node = find(value);
        if (node == null) {
            return null;
        }
    
        if (node.leftChild != null) {
            node = node.leftChild;
            while (node.rightChild != null) {
                node = node.rightChild;
            }
            return node;
        }
    
        Node<T> parent = node.parent;
        while (parent != null && node == parent.leftChild) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    public T successor(T value) {
        Node<T> node = find(value);
        if (node == null) {
            return null;
        }
    
        // If right subtree is not null, then successor is the minimum of the right subtree.
        if (node.rightChild != null) {
            Node<T> temp = node.rightChild;
            while (temp.leftChild != null) {
                temp = temp.leftChild;
            }
            return temp.data;
        }
    
        // If right subtree is null, then successor is one of the ancestors.
        Node<T> parent = node.parent;
        while (parent != null && node == parent.rightChild) {
            node = parent;
            parent = parent.parent;
        }
        return parent != null ? parent.data : null;
    }

    private void balance() {
        ArrayList<T> orderedNodes = new ArrayList<>();
        inorderTraversal(root, orderedNodes);
        root = balanceSubtree(orderedNodes, 0, orderedNodes.size() - 1);
    }

        private void inorderTraversal(Node<T> node, ArrayList<T> orderedNodes) {
            if (node != null) {
                inorderTraversal(node.leftChild, orderedNodes);
                orderedNodes.add(node.data);
                inorderTraversal(node.rightChild, orderedNodes);
            }
        }

        private Node<T> balanceSubtree(ArrayList<T> orderedNodes, int start, int end) {
            if (start > end) {
                return null;
            }

            int mid = (start + end) / 2;
            Node<T> node = new Node<>(orderedNodes.get(mid));
            node.leftChild = balanceSubtree(orderedNodes, start, mid - 1);
            node.rightChild = balanceSubtree(orderedNodes, mid + 1, end);

            return node;
        }
    // if needed, add getters and/or setters

  } // end class List
