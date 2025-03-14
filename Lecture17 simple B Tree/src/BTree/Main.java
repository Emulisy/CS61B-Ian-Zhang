package BTree;

public class Main {
    public static void main(String[] args) {
        BTree tree = new BTree();
        tree.addNode(5);
        tree.addNode(4);
        tree.addNode(6);
        tree.addNode(7);
        tree.addNode(3);
        tree.addNode(2);
        tree.addNode(1);

        System.out.println(tree);
    }
}
