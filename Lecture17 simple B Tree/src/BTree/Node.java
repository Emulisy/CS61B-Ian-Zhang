package BTree;

public class Node {
    private int data;
    private Node left;
    private Node right;
    private Node middle;

    public Node(int x) {
        data = x;
    }

    public int getData(){
        return data;
    }
    public Node getLeft() {
        return left;
    }
    public Node getRight() {
        return right;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public boolean isLeaf() {
        return left == null && right == null;
    }

    public Node find(int x) {
        if (data == x) {
            return this;
        }
        else if (data < x) {
            if (left != null) {
                return left.find(x);
            }
            else {
                return null;
            }
        }
        else {
            if (right != null) {
                return right.find(x);
            }
            else {
                return null;
            }
        }
    }

    public void addNode(int x){
        if (data == x) {
            throw new IllegalArgumentException("Node already exists");
        }
        else {
            if(x < data) {
                if (left == null) {
                    left = new Node(x);
                }
                else {
                    left.addNode(x);
                }
            }
            else {
                if (right == null) {
                    right = new Node(x);
                }
                else {
                    right.addNode(x);
                }
            }
        }
    }

    public String toString(){
        String str = data + " ";
        if(left != null) {
            str += left.toString() + " ";
        }
        if(right != null) {
            str += right.toString() + " ";
        }
        return str;
    }
}
