/******************************************************************************
 *  Name: Kyle Morris    
 *    
 *
 *  Hours to complete assignment (optional): ~30
 *
 ******************************************************************************/

Programming Assignment 1: Percolation


/******************************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates? This should be something like 3-5 sentences.
 *****************************************************************************/
I used a 2d boolean array to represent system state (of size N), with their default initialization value of 0 representing a closed site (1 represnting open). I then created a UF object, that has our grid size of N, but plus 2, the plus two creates room for our virtual sites that act as source and sink (top and bottom). I continued on, mapping the 2d boolean array  to our 1d UF array. Once that was done, the rest of the methods were relatively self explanatory; returning a single variable or performing a bounds check. Except for the open() method. This method required that you 'look around' in all four directions (up, down, left, right) to verify whether or not adjacent sites are open as well. If they are, then you must union them in our QF structure appropriately. To check whether or not the system percolates, it was a single call to connected between source and sink, i.e. qf.connected(source,sink); If that is true, percolation!


/******************************************************************************
 *  Perform computational experiments to estimate the running time of
 *  PercolationStats.java for values values of n and T when implementing
 *  Percolation.java with QuickFindUF.java.
 *
 *  To do so, fill in the two tables below. Each table must have at least
 *  4 data points, ranging in time from around 0.1 seconds to at most a few
 *  minutes. Do not include data points that takes less than 0.1 seconds.
 *  Each data point should be double the size of the preceding data point
 *****************************************************************************/
T = 3
(keep T constant)

 n          time (seconds)
------------------------------
100		0.118
200		1.47
400		20.927
800		297.444

n = 100
(keep n constant)

 T          time (seconds)
------------------------------
3		0.115
6		0.209
9		0.339
12		0.426


/******************************************************************************
 *  Using the empirical data from the above two tables, give an estimate
 *  of the running time of PercolationStats.java as function of both n and 
 *  T, using asymptotic notation, like:
 *
 *       O( n^5.0 * T^1.5 )
 *
 *  Recall that with asymptotic notation, you are interested in the
 *  growth rate of run time as input sizes grow - you drop lower-order
 *  terms and constant factors. In class, we've discussed a way to 
 *  estimate polynomial factors from empirical run times.
 *
 *  How does this estimate compare to the theoretical run time growth
 *  rate, according to analysis of your code?
 *
 *****************************************************************************/

Running time as a function of n and T:  O( n^1.9 t^1.0  )

Compared to theory: O(n^4)

/******************************************************************************
 *  Repeat the previous two questions, but using WeightedQuickUnionUF
 *  (instead of QuickFindUF).
 *****************************************************************************/
T = 5
(keep T constant)

 n         time (seconds)
------------------------------
400		0.101
800		0.348
1600		2.016
3200		11.017


n = 100
(keep n constant)

 T          time (seconds)
------------------------------
90		0.107
180		0.181
360		0.298
720		0.518



Running time as a function of n and T:  O( n^1 * t^2.0  )

Compared to theory: O(log(n))


/**********************************************************************
 *  How much memory (in bytes) does a Percolation object (which uses
 *  WeightedQuickUnionUF.java) use to store an n-by-n grid? Use the
 *  64-bit memory cost model from Section 1.4 of the textbook and use
 *  tilde notation to simplify your answer. Briefly justify your
 *  answers.
 *
 *  Include the memory for all referenced objects (deep memory).
 **********************************************************************/

Objects are 32bytes, our WeightedObject has 2int arrays (@4n: so 8n^2) and an array of boolean state for 1 byte so 8n^2 + 1 = 9n^2 and it has an int count for 4bytes.
total: 32+9n^2+4






 
/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/
Back fill/back wash. Where if a site is open on the bottom of a percolating system it becomes filled, and it can fill upwards out of the bottom into sites that weren't part of the original downward percolation/connection. 



/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, TA,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/
I recieved crucial clarity from Addison Boyer (TA) on the serious problems I go on to mention below. He explained that I had left out this.N=N in my constructor and it was game changing. Did I feel like an absolute buffoon? You bet! But, I greatly appreciated his clarity on the issue and why that line of code has to happen. Such a silly mistake. 

Addison also helped me clarify a logical issue in my open() where I was checking whether or not I was still on the grid one cell to my left or right. Again, a silly mistake on my part, but both of these 'tiny' mistakes completely wrecked my entire program until they were resolved.

/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/
I tried to set my source to 0 in my QF struct instead of N*N, originally. This led to some absolutely hellish confusion, mathematically, later on when trying to implement my open() (mainly when you wanted to 'look around') and fetchIDInt() methods. I believe I ended up with ((i*n)+j+1) at first instead of the (way easier) (i*n)+j when converting from 2d coords to 1d. Talk about off by one errors...
 
The other serious problem was ridiculous. I didn't put this.N=N; in my constructor...this bamboozled me for way longer than it should have.


/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/
This is one of the few programming courses that has forced me into actual study for hours a week or show up to the study jams; it's hard. This assignment was...intimidating, to say the least. The lower level of understanding you need to have in order to work these algorithms out is fantastic, though. I've literally learned more about computer science in this one assignment then I have through out the entirety of other courses I've finished. I do truly believe this course will make all who take it a better programmer and a better computer scientist, but at the cost of some SERIOUS effort. There were two days where I damn near pulled all-nighters trying to debug and implement methods appropriately. This class is scary but also fascinating. 

