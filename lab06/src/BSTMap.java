import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V>  implements Map61B<K, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private Node parent;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
        return the node if key is K
        return the left or right child unless it has no child
        **/
        public Node find(K key) {
            if (key.equals(this.key)) {
                return this;
            }
            else if (key.compareTo(this.key) < 0) {
                if (this.left == null) {
                    return null;
                }
                return left;
            }
            else{
                if (this.right == null) {
                    return null;
                }
                return right;
            }
        }
    }

    public BSTMap() {}

    /**
    insert the key value pair to the BST map
    **/
    @Override
    public void put(K key, V value) {
        Node newNode = new Node(key, value);
        if (root == null) { //if no root exist
            root = newNode;
            size++;
        }
        else {
            Node current = root;
            while (true) {
                if(current.find(key) == null) {
                    //if current node's key is not K and has no legitimate child
                    if(key.compareTo(current.key) < 0) {
                        current.left = newNode;
                    }
                    else {
                        current.right = newNode;
                    }
                    newNode.parent = current;
                    size = size+1;
                    break;
                }
                else if(current.key.equals(key)) {
                    //if current key is K
                    current.value = value;
                    break;
                }
                else {
                    //if key is not K and have legitimate child
                    current = current.find(key);
                }
            }
        }
    }

    /**
    get the value of the key if the key is valid
    **/
    @Override
    public V get(K key) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value;
            }
            else {
                currentNode = currentNode.find(key);
            }
        }
        return null;
    }

    /**
    check if the key is in the BST
    **/
    @Override
    public boolean containsKey(K key) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return true;
            }
            else {
                currentNode = currentNode.find(key);
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }


    /**
     * Returns a Set view of the keys contained in this map.
     * */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        collectKeys(root, keys);
        return new TreeSet<>(keys);
    }

    /**
    collect the key of current node and call the method recursively
    **/
    void collectKeys(Node n, Set<K> set) {
        if (n != null) {
            collectKeys(n.left, set);
            set.add(n.key);
            collectKeys(n.right, set);
        }
    }

    /**
     * find the least node of the branch
    **/
    Node findMin(Node n) {
        if (n.left == null) {
            return n;
        }
        return findMin(n.left);
    }

    /**
     * find the largest node of the branch
     * **/
    Node findMax(Node n) {
        if (n.right == null) {
            return n;
        }
        return findMax(n.right);
    }

    /**
     * remove leaf and set the parent's corresponding position as null
     * **/
    V removeLeaf(Node n) {
        if(n.parent.left == n) {
            n.parent.left = null;
        }
        else {
            n.parent.right = null;
        }
        return n.value;
    }

    /**
    remove the key value node from the BST using Hibbard deletion
    **/
    @Override
    public V remove(K key) {
        Node currentNode = root;
        while (currentNode != null) { //find the node with key
            if (currentNode.key.equals(key)) {//if found the key
                size -= 1;
                break;
            }
            else {
                currentNode = currentNode.find(key);
            }
        }
        if (currentNode == null) { //if key not found
            return null;
        }
        if (currentNode == root) { //the node is root
            if(currentNode.left == null && currentNode.right == null) { //if root has no child
                clear();
                return currentNode.value;
            }
            else if(currentNode.right == null) { //if the root has no left or right child
                root = currentNode.left;
                return currentNode.value;
            }
            else if(currentNode.left == null) {
                root = currentNode.right;
                return currentNode.value;
            }
            //if root has both children
            return replaceMinNode(currentNode);
        }
        else if(currentNode.left == null && currentNode.right == null) { //have no child(is leaf)
            return removeLeaf(currentNode);
        }
        else if(currentNode.right == null) {//have left child
            Node maxNode = findMax(currentNode.left);
            if(maxNode == currentNode.left) {//if max node is the left child
                removeLeaf(maxNode);
                currentNode.left = maxNode.left;
            }
            else {
                removeLeaf(maxNode);
                maxNode.parent.right = maxNode.left; //max node have no right child but may have left child
            }
            V returnValue = currentNode.value;
            currentNode.key = maxNode.key;
            currentNode.value = maxNode.value;
            return returnValue;
        }
        else { //have right child or have both child
            return replaceMinNode(currentNode);
        }
    }

    /**
     * replace the current node's key value set with the min node
     * **/
    private V replaceMinNode(Node currentNode) {
        Node minNode = findMin(currentNode.right);
        if(minNode == currentNode.right) { //if the min node is the right child
            removeLeaf(minNode);
            currentNode.right = minNode.right;
        }
        else {
            removeLeaf(minNode);
            minNode.parent.left = minNode.right; //minNode has no left child but may have right child
        }
        V returnValue = currentNode.value;
        currentNode.key = minNode.key;
        currentNode.value = minNode.value;
        return returnValue;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        private Iterator<K> iterator;
        private BSTMapIterator() {
            this.iterator = BSTMap.this.keySet().iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public K next() {
            return iterator.next();
        }
    }
}
