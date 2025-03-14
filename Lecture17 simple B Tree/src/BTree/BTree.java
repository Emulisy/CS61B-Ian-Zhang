package BTree;

public class BTree {
    private Node root;
    public BTree() {}

    public void addNode(int data) {
        if (root == null) {
            root = new Node(data);
        }
        else {
            root.addNode(data);
        }
    }

    public boolean contains(int data) {
        if (root == null) {
            return false;
        }
        else{
            return root.find(data) != null;
        }
    }

    public String toString(){
        return root.toString();
    }


}
