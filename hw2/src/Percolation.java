import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/*The library has the following method:
void union(int p, int q): Unions two items.
boolean connected(int p, int q): Returns true if items are connected.
int find(int p): Returns set number of a given item.
**/

public class Percolation {
    // TODO: Add any necessary instance variables.
    public static WeightedQuickUnionUF grid;
    public static WeightedQuickUnionUF gridWithBackWash;
    /*The grid contains a virtual top and bottom(n and n+1)
    **/
    private static int n;
    public static int[] openArr;// an array that monitors if the cell is open(0 is closed and 1 is open)
    private int openSites;//number of open sites

    public Percolation(int N) {// create N-by-N grid, with all sites initially blocked
        // TODO: Fill in this constructor.
        n = N*N;
        grid = new WeightedQuickUnionUF(n + 1);
        gridWithBackWash = new WeightedQuickUnionUF(n + 2);
        openArr = new int[n];
        for (int i = 0; i < n; i++) {
            openArr[i] = 0;
        }
    }

    public void open(int row, int col) {//(0,0) is up left. open the site (row, col) if it is not open already
        // TODO: Fill in this method.
        int N =  (int) (Math.sqrt(n));
        int index = (row * N + col);
        if(openArr[index] == 0) {
            openArr[index] = 1;
            openSites++;
            int[] connectedSites = new int[4];
            connectedSites[0] = index - 1;//left
            connectedSites[1] = index + 1;//right
            connectedSites[2] = index - N;//up
            connectedSites[3] = index + N;//down
            if(col == 0){
                connectedSites[0] = -1;
            }
            if(col == N - 1){
                connectedSites[1] = -1;
            }
            if(row == 0){
                connectedSites[2] = n;
            }
            if(row == N - 1){
                connectedSites[3] = n + 1;
            }
            for(int i:connectedSites){
                if(i >= 0 && i < n){
                    if(openArr[i] == 1) {
                        grid.union(i, index);
                        if(!percolates()) {
                            gridWithBackWash.union(i, index);
                        }
                    }
                }
                else if(i == n && row == 0) {//left bottom site index + N == n
                    grid.union(n, index);
                    if(!percolates()) {
                        gridWithBackWash.union(n, index);
                    }
                }
                else if(i == n + 1 && row == N - 1 && !percolates()){
                    gridWithBackWash.union(n + 1, index);
                    /*to handle back wash, the gridWithBackWash has a virtual bottom
                    and the last row are connected to it
                    **/
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {// is the site (row, col) open?
        // TODO: Fill in this method.
        int index = (int) (row * Math.sqrt(n) + col);
        return openArr[index] == 1;
    }

    public boolean isFull(int row, int col) {
        /* is the site (row, col) full?
        full means the root is top(n)
        **/
        // TODO: Fill in this method.
        int index = (int) (row * Math.sqrt(n) + col);
        return grid.connected(index, n) && openArr[index] == 1;
    }

    public int numberOfOpenSites() {// number of open sites
        // TODO: Fill in this method.
        return openSites;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return gridWithBackWash.connected(n,n+1);

    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
