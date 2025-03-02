public class WeightedQuickUnionDS implements DisjointSets {
    private int[] parent; // Stores the parent of each element
    private int[] size;   // Stores the size of each tree

    // Constructor: Initializes parent array and size array
    public WeightedQuickUnionDS(int num) {
        parent = new int[num];
        size = new int[num];

        // Initially, each element is its own root, and the size is 1
        for (int i = 0; i < num; i++) {
            parent[i] = i; // Each element is its own root initially
            size[i] = 1;   // Size of each tree is 1 initially
        }
    }

    // Find method with path compression
    private int find(int p) {
        // Find the root of p
        if (parent[p] != p) {
            parent[p] = find(parent[p]);  // Path compression
        }
        return parent[p];
    }

    // Connect two elements by uniting their sets
    @Override
    public void connect(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        // If they are already connected, no need to do anything
        if (rootP == rootQ) return;

        // Union by size: attach the smaller tree to the root of the larger tree
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;  // Make rootQ the root of rootP
            size[rootQ] += size[rootP];  // Update the size of rootQ's tree
        } else {
            parent[rootQ] = rootP;  // Make rootP the root of rootQ
            size[rootP] += size[rootQ];  // Update the size of rootP's tree
        }
    }

    // Check if two elements are in the same set
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);  // If the roots are the same, they're connected
    }
}
