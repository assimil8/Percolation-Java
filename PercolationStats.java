public class PercolationStats {
   int N;
   int T;
   
   double[] resultStorage;
    
   public PercolationStats(int N, int T) {  // perform T independent experiments on an N-by-N grid
       this.N = N;
       this.T = T;
       
       resultStorage = new double [T];
       
       if (N<=0|| T<=0) throw new java.lang.IllegalArgumentException("Both N and T must be greater than 0!");
       
       //iterate through 'random' spots on our grid and open them up
       for (int x = 0; x < T; x++) {
           Percolation p1 = new Percolation(N);
           
           //while the system hasn't percolated, continue to do the following
           while(!p1.percolates()) {
               //set bounds to our 'random' number(indices) generation/selection
               int row = StdRandom.uniform(0,N);
               int col = StdRandom.uniform(0,N);
               //if the spot isn't open, do such and increment counter
               if (!p1.isOpen(row, col)) {
                   p1.open(row,col);
               }
           }
           //so where we are in our array (index-wise) obtains the value of opensites/grid size
           // we will process this array mathematically with StdStats down the ine
           resultStorage[x] = (double) (p1.numberOfOpenSites()/ (double)(N*N));
       }
       
       
   }
   
   public double mean() {                   // sample mean of percolation threshold
       return StdStats.mean(resultStorage);
   }
   
   public double stddev() {                  // sample standard deviation of percolation threshold
       return StdStats.stddev(resultStorage);
   }
   
   public double confidenceLow() {
       // low  endpoint of 95% confidence interval
       return  mean() - ((1.96 * stddev())/Math.sqrt(resultStorage.length));
   }
  
   public double confidenceHigh() {
       // high endpoint of 95% confidence interval
       return  mean() + ((1.96 * stddev())/Math.sqrt(resultStorage.length));
   }
   
   public static void main(String[] args) {
       Stopwatch sw1 = new Stopwatch();
       PercolationStats ps1 = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
       double timeItTakes = sw1.elapsedTime();
       StdOut.print("Mean = " + ps1.mean() + '\n');
       StdOut.print("Standard Deviation = " + ps1.stddev() +'\n' );
       StdOut.print("ConfidenceLow = " + ps1.confidenceLow() + '\n');
       StdOut.print("ConfidenceHigh = " + ps1.confidenceHigh() + '\n');
       StdOut.print("Time elapsed: " + timeItTakes);
       StdOut.print('\n');
   }

   
}