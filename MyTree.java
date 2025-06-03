package pokemon;


/**
 * This class represents a tree data structure.
 * It allows adding, removing, and searching for elements in the tree.
 * It also provides methods to check the size of the tree and the relationship between nodes.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class MyTree<T> implements Serializable {

    private class Node implements Serializable {
        T data; // data of the node
        ArrayList<Node> children; // list of children nodes

        Node (T data){
            this.data = data; // set the data of the node
            this.children = new ArrayList<>(); // initialize the list of children nodes
        }
    }
    private Node root; // root of the tree

    public MyTree(T element) {
        if (element == null) {
            throw new IllegalArgumentException(); // element cannot be null
        }
        root = new Node(element); // create the root node with the given element
    }

    public boolean remove(T element) {
        if(element == null || root == null){
            return false; // element cannot be null or tree is empty
        }
        if(root.data.equals((element))){
            throw new IllegalArgumentException(); // cannot remove the root node
        }
        if(root.children.isEmpty()){
            return false; // no children to remove
        }
        return removeRecursive(root, element); // call the recursive method to remove the element

    }

    private boolean removeRecursive(Node curr, T element){
        Iterator<Node> it = curr.children.iterator(); // iterate through the children nodes
        while(it.hasNext()){
            Node child = it.next(); // get the next child node
            if(child.data.equals(element)){
                it.remove(); // remove the child node
                return true; // element found and removed
            }
            if(removeRecursive(child, element)){ // recursively check the children nodes
                return true; // element found and removed
            }
        }
        return false; // element not found
    }

    private Node getNode(Node curr, T element){
        if(curr.data.equals(element)){
            return curr; // element found
        }
        for(Node child : curr.children){ // iterate through the children nodes
            Node result = getNode(child, element); // recursively check the children nodes
            if(result != null){
                return result; // element found
            }
        }
        return null; // element not found
    }
    private void copyTree(Node curr, Node next){
        if(curr == null || next == null){
            return; // cannot copy null nodes
        }
        next.data = curr.data; // copy the data of the current node
        for(Node child : curr.children){ // iterate through the children nodes
            Node newChild = new Node(child.data); // create a new child node
            next.children.add(newChild); // add the new child node to the next node's children
            copyTree(child, newChild); // recursively copy the children nodes
        }
    }
    public MyTree<T> get(T element) {
        if(element == null || root == null){
            return null; // element cannot be null or tree is empty
        }
        Node node = getNode(root, element); // get the node with the given element
        if(node == null){
            return null; // node not found
        }
        MyTree<T> subTree = new MyTree<>(node.data); // create a new tree with the node's data
        copyTree(node, subTree.root); // copy the children nodes to the new tree
        return subTree; // return the new tree
    }

    public boolean add(T parent, T element) {
        if(element == null || parent == null) {
            return false; // element or parent cannot be null
        }
        if(exists(element)){
            return false; // element already exists in the tree
        }
        Node parentNode = getNode(root, parent); // get the parent node
        if(parentNode == null){
            return false; // parent node not found
        }
        Node newNode = new Node(element); // create a new node with the given element
        parentNode.children.add(newNode); // add the new node to the parent node's children
        return true; // element added successfully
    }

    public boolean exists(T element) {
        if(element == null || root == null){
            return false; // element cannot be null or tree is empty
        }
        Node node = getNode(root, element); // get the node with the given element
        return node != null; // return true if node found, false otherwise
    }

    public boolean isSuccessorOf(T child, T parent) {
        if(child == null || parent == null){
            return false; // child or parent cannot be null
        }
        if(child.equals(parent)){
            return false; // child and parent cannot be the same
        }
        Node childNode = getNode(root, child); // get the child node
        Node parentNode = getNode(root, parent); // get the parent node
        if(childNode == null || parentNode == null){
            return false; // child or parent node not found
        }
        return isSuccessorOfRecursive(parentNode, childNode); // call the recursive method to check the relationship
    }
    private boolean isSuccessorOfRecursive(Node parent, Node child) {
        if (parent == null || child == null) {
            return false; // parent or child cannot be null
        }
        if (parent.data == child.data) {
            return true; // parent is the same as child
        }
        for (Node node : parent.children) { // iterate through the children nodes
            if (isSuccessorOfRecursive(node, child)) { // recursively check the children nodes
                return true; // child found in the children nodes
            }
        }
        return false; // child not found in the children nodes
    }

    public boolean isPredecessorOf(T parent, T child) {
        if(child == null || parent == null){
            return false; // child or parent cannot be null
        }
        Node childNode = getNode(root, child); // get the child node
        Node parentNode = getNode(root, parent); // get the parent node
        if(childNode == null || parentNode == null){
            return false; // child or parent node not found
        }
        return isSuccessorOfRecursive(parentNode, childNode); // call the recursive method to check the relationship
    }

    public int size() {
        if(root == null){
            return 0; // tree is empty
        }
        return sizeRecursive(root); // call the recursive method to get the size of the tree
    }
    private int sizeRecursive(Node curr) {
        if (curr == null) {
            return 0; // node is null
        }
        int size = 1; // count the current node
        for (Node child : curr.children) { // iterate through the children nodes
            size += sizeRecursive(child); // recursively get the size of the children nodes
        }
        return size; // return the total size
    }
     public T getData() {
        if(root == null){
            return null; // tree is empty
        }
        return root.data; // return the data of the root node
    }
}
