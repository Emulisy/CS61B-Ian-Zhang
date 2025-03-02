public class QuickUnionDS_alternative implements DisjointSets{
    private int[] parent;

    public QuickUnionDS_alternative(int num) {
        parent = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = i;//the value of root is always its index
        }
    }

    private int find(int p) {
        int root = p;
        while (parent[root] != root) {
            root = parent[root];
        }
        parent[p] = root; //route compression
        return root;
    }

    @Override
    public void connect(int p, int q) {
        int i = find(p);
        int j= find(q);
        parent[i] = j;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
