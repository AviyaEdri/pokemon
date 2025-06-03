package pokemon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class MyForest<T> implements Serializable {
    private ArrayList<MyTree<T>> forest; // list of trees in the forest

    public MyForest() {
        this.forest = new ArrayList<>(); // initialize the forest
    }

    public MyForest(MyForest<T> stringForest) {
        this.forest = new ArrayList<>(); // initialize the forest
        for(MyTree<T> tree : stringForest.forest) {
            T rootData = tree.getData(); // get the root data of the tree
            MyTree<T> newTree = new MyTree<>(rootData); // create a new tree with the root data
            this.forest.add(newTree); // add the new tree to the forest
        }
    }

    public boolean add(T element) {
        if ((element == null)) {
            return false; // element cannot be null
        }
        for (MyTree<T> tree : forest){
            if(tree.exists(element)){
                return false; // element already exists in the forest
            }
        }
        MyTree<T> newTree = new MyTree<>(element); // create a new tree with the given element
        forest.add(newTree); // add the new tree to the forest
        return true; // element added successfully
    }

    public boolean add(T parent, T element) {
        if(element == null){
            return false; // element cannot be null
        }
        if (parent == null){
            return add(element); // if parent is null, add the element as a new tree
        }
        for (MyTree<T> tree : forest){
            if(tree.exists((element))){
                return false; // element already exists in the forest
            }
        }
        for (MyTree<T> tree : forest) {
            if (tree.exists(parent)) {
                return tree.add(parent, element); // add the element to the tree with the given parent
            }
        }
        return false; // parent not found in any tree
    }

    public boolean remove(T element) {
        if(element == null){
            return false; // element cannot be null
        }
        Iterator<MyTree<T>> it = forest.iterator();
        while(it.hasNext()){
            MyTree<T> tree = it.next();

            if(tree.getData().equals((element))){
                it.remove(); // remove the tree if it is the root
                return true; // element found and removed
            }
            if(tree.remove(element)){
                return true; // element found and removed from the tree
            }
        }
        return false; // element not found in any tree
    }

    public boolean areRelated(T element1, T element2) {
        if(element1 == null || element2 == null){
            return false; // elements cannot be null
        }
        for(MyTree<T> tree : forest){
            if (tree.exists(element1) && tree.exists((element2))) {
                return tree.isSuccessorOf(element1, element2) || tree.isSuccessorOf(element2, element1);
            }
        }
        return false; // elements are not related in any tree
    }

    public boolean exists(T element) {
        if(element == null){
            return false; // element cannot be null
        }
        for(MyTree<T> tree : forest){
            if (tree.exists(element)) {
                return true; // element exists in the forest
            }
        }
        return false; // element not found in any tree
    }

    public MyTree<T> getTree(T element){
        if(element == null){
            return null; // element cannot be null
        }
        for (MyTree<T> tree : forest){
            if(tree.exists(element)){
                return tree; // return the tree that contains the element
            }
        }
        return null; // element not found in any tree
    }
}
