import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.QuickFindUF;

public class Percolation {
    /**
     * Boolean 2d array needs to be mapped to id grid with source and sink
     * 
     */
    int N; // integer input acting as size
    int source = N*N; // top
    int sink; // bottom
    boolean[][] systemState; // 2d system, monitors state
    int openSiteCount;

    // uf data struct representation of boolean system, will include sink and source
    WeightedQuickUnionUF intIDArr;

    public Percolation(int N) {
        this.N = N;
        // create N-by-N grid, with all sites initially blocked
        if (N <= 0)
            throw new IllegalArgumentException("Enter a positive integer value please!");
        // 'bottom' will always be (size of our grid + 1)
        sink = (N * N + 1);
        // grid includes our source and sink, hence the '+2' tiles
        // source and sink are virtual; not part of original system
        intIDArr = new WeightedQuickUnionUF(N * N + 2);
        // set booleans for our 2d array/grid; should be zero by default, i.e. blocked
        systemState = new boolean[N][N];
    }

    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        if (validateIndices(row,col)) {
            systemState[row][col] = true;
            openSiteCount++;
            //what if we are in the top row, we are connected to source
            if (row == 0) {
                intIDArr.union(fetchIntID(row,col), source);
            }
            //what if we are on the bottom row, we are connected to the sink
            if (row == (N-1)) {
                intIDArr.union(fetchIntID(row,col), sink);
            }
            //make sure the area we are checking is actually on the grid
            //look up
            if ((row-1)>= 0) {
                //we are on the grid, if this spot is open UNION
                if (isOpen(row-1,col)) {
                    intIDArr.union(fetchIntID(row-1,col),fetchIntID(row,col));
                }
            }
            //look down
            if ((row+1)< N) {
                //we are on the grid, if this spot is open UNION
                if (isOpen(row+1,col)) {
                    intIDArr.union(fetchIntID(row+1,col),fetchIntID(row,col));
                }
            }
            //look right
            if ((col+1)< N) {
                //we are on the grid, if this spot is open UNION
                if (isOpen(row,col+1)) {
                    intIDArr.union(fetchIntID(row,col+1),fetchIntID(row,col));
                }
            }
            //look left
            if ((col-1)>= 0) {
                //we are on the grid, if this spot is open UNION
                if (isOpen(row, col-1)) {
                    intIDArr.union(fetchIntID(row,col-1),fetchIntID(row,col));
                }
            } 
        } else {
            throw new java.lang.IndexOutOfBoundsException("Out of range!");
        }
    }

    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        if (validateIndices(row,col)) {
            return systemState[row][col];
        } else {
            throw new java.lang.IndexOutOfBoundsException("Out of range.");
        }
    }

    private int fetchIntID(int row, int col) {
       return (row*N)+col;
    }
    
    private boolean validateIndices(int row, int col) {
        return row>=0 && col>=0 && row <= N-1 && col<= N-1;
    }
 
    public boolean isFull(int row, int col) { // is the site (row, col) full?
        if (row < 0 || row > N || col < 0 || col > N) {
            throw new java.lang.IndexOutOfBoundsException("Out of range.");
        } else {
            return intIDArr.connected(fetchIntID(row,col), source);
        }
      }
      
    public int numberOfOpenSites() {
        return openSiteCount;
    }
     
    public boolean percolates() {
        return intIDArr.connected(source, sink);
    }

    public static void main(String[] args) {
          // unit testing (suggested) 
        /*
         * int N = Integer.parseInt(args[0]); Percolation p1 = new Percolation(N);
         * StdOut.print("N = " + N); StdOut.print('\n');
         * System.out.println(p1.fetchIntID(1,1)); StdOut.print("# of open sites = " +
         * p1.numberOfOpenSites() + '\n'); System.out.println(p1.validateIndices(1, 1));
         * System.out.println(p1.isOpen(0,0)); System.out.println(); p1.open(2,2);
         * System.out.println(p1.isOpen(0,1));
         */
          
          
    }
}