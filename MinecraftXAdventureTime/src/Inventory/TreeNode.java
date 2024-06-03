package Inventory;

public class TreeNode<E extends Comparable<E>> {
    E element;
    TreeNode<E> left;
    TreeNode<E> right;
    
    public TreeNode(E o) {
        element = o;
    }
    
    @Override
    public String toString() {
        return element.toString();
    }
}
