package Inventory;

import java.util.ArrayList;
import java.util.List;

class BST<E extends Comparable<E>> {
    protected TreeNode<E> root;
    protected int size = 0;
    protected List<E> items = new ArrayList<>(); // List to store items in insertion order

    /** Create a default binary tree */
    public BST() {
    }

    /** Create a binary tree from an array of objects */
    public BST(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }

    /** Search for an element in the tree */
    public E search(E e) {
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left; // Go left
            } else if (e.compareTo(current.element) > 0) {
                current = current.right; // Go right
            } else
                return current.element; // Element is found
        }

        return null; // Element is not in the tree
    }

    /** Insert element e into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    return false; // Duplicate node not inserted
            }

            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        items.add(e); // Add to list of items in insertion order
        return true; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    public void inOrder(TreeNode<E> root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.println(root.element + " ");
        inOrder(root.right);
    }

    public List<E> getItems() {
        return items;
    }
}
