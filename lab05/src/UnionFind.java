public class UnionFind {
    //Union Find ADT
    public int[] numList;
    // TODO - Add instance variables?

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    /* O(N) time in WQU*/
    public UnionFind(int n) {
        // TODO
        numList = new int[n];//the value of root is negated value of the size of its set
        for(int i = 0; i < numList.length; i++) {
            numList[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= numList.length || vertex < 0) {
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        this.validate(v1);
        int root = this.find(v1);
        return Math.abs(numList[root]);
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    /* Aka Find */
    public int parent(int v1) {
        // TODO
        this.validate(v1);
        return numList[v1];
    }

    /* O(log N) time in WQU*/
    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        this.validate(v1);
        this.validate(v2);
        return find(v1) == find(v2);
    }

    /* O(log N) time in WQU*/
    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if(this.connected(v1, v2)) {
            // TODO

        } else {
            int root1 = find(v1);
            int root2 = find(v2);
            int size1 = Math.abs(this.parent(root1));
            int size2 = Math.abs(this.parent(root2));
            if (size1 > size2) {
                numList[root1] -= size2;
                numList[root2] = root1;
            } else if (size2 < size1) {
                numList[root2] -= size1;
                numList[root1] = root2;
            } else {
                numList[root2] -= size1;
                numList[root1] = root2;
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        this.validate(vertex);
        int r = vertex;
        while(this.parent(r) >= 0) { // this line causes ArrayIndexOutOfBoundsException in parent()
            r = this.parent(r);
        }
        return r;
    }

}