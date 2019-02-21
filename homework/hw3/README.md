Name: Brandon Wong  
JHED: bwong19  
Email: bwong19@jhu.edu  

## Part A: Measured Arrays

* An iterator for MeasuredArray should only affect accesses and mutation counts if it obtains or modifies array data. In count(), I iterated through the array using a for loop, then I called get(i) each time I wanted to examine the array's contents. Likewise, an iterator should increment the accesses count whenever it gets the current or next item in the array.
* If you wanted to include next() and/or hasNext() in measured statistics, it would not be possible to inherit ArrayIterator. This is because ArrayIterator is private. MeasuredArray is a subclass of SimpleArray, so it cannot access its private methods.


## Part B: All Sorts of Sorts

* When running bubble sort on ascending.data, I expected it to break out of the sort after the first iteration because the data appeared to be sorted correctly. However, bubble sort was not instantaneous. Additionally, all of the sorts had more than 1 mutation, which should not happen because the array should already be sorted. I realized that this is because the data are Strings and not integers, so a String like "10" would appear before "2", "3", and "9", which means ascending.data is not correctly sorted. This was the error in the provided data.
* Once I determined this error, I printed the sorted data into a new file and ran the sorting algorithms again. All sorts then had 0 mutations, and bubble sort was indeed the fastest algorithm and required the least amount of accesses.

1. The actual run times did not always correspond exactly to what I would expect. Some of the sorts did not seem to be exactly O(n^2). For example, when sorting ascending.data, bubble sort completed 200 elements in 0.001833 seconds, while it completed 400 elements in 0.004011 seconds. This is roughly twice as slow, which means that between 200 and 400 elements, bubble sort is roughly O(n) rather than O(n^2). For larger data sets, it did seem to grow approximately quadratically in time.
2. Because of the different ways the sorting algorithms work, different sorts may work better than others in certain cases. For example, since bubble sort has a smart break when the array is already sorted, it runs very quickly for arrays that are already sorted. Gnome sort runs similarly to bubble sort in the best and worst cases, and runs more similarly to insertion and selection sort in the random case. Although selection sort and insertion sort also perform well for arrays that are already sorted, they perform similarly to each other, but worse than bubble sort because they do not have break statements. Their worst and best cases differ because of the way they function, namely the comparisons and assignments they make.
3. It does matter what kind of data is being sorted. Bubble sort should perform faster when the data is in ascending order since this is its best case, and it breaks out of the loop whereas the other sorts do not. In the tests that I have done, all sorts have ascending data as the best case and descending data as the worst case most of the time. However, selection and insertion sort perform similarly between all three data sets, whereas bubble sort performs much slower with random and descending data than with ascending data. Bubble sort in its best case grows more slowly than selection and insertion sort, but grows more quickly in less optimal cases. Gnome sort performs similarly to bubble sort with ascending and descending data, and performs similarly to selection and insertion sort with random data.


## Part C: Analysis of Selection Sort

* Let n = a.length. Lines 1 and 2 perform no operations. Line 3 performs 1 assignment in the beginning, n - 1 comparisons and n - 1 subtraction operations, and n - 1 assignments, for a total of n - 1 comparisons, n assignments, and n - 1 operations. Line 4 makes 1 assignment per iteration, and there are n - 1 iterations, for a total of n - 1 assignments. Line 5 makes 1 assignment and 1 addition operation which happen n - 1 times, and 1 comparison and 1 assignment each time the inner looper is iterated through, which is 1 + 2 + ... + n - 1 = n (n - 1) / 2 iterations; line 5 has a total of (n - 1) + n (n - 1) / 2 assignments, n (n - 1) / 2 comparisons, and n - 1 arithmetic operations. Line 6 has 1 comparison each iteration, for a total of n (n - 1) / 2 comparisons. In the worst case, line 7 has 1 assignment each iteration, for a total of n (n - 1) / 2 assignments. Lines 8 and 9 have no operations. Lines 10 through 12 account for 3 assignments, which each happen once per iteration of the outer loop, for a total of 3 (n - 1) assignments.
* Counting the total number of comparisons, we get (n - 1) + n (n - 1) / 2 + n (n - 1) / 2 = (n - 1) + n (n - 1) = (n + 1) (n - 1) = n^2 - 1 comparisons. Therefore, **C(n) = n^2 - 1**.
* Counting the total number of assignments, we get 2 + n + (n - 1) + (n - 1) + n (n - 1) / 2 + n (n - 1) / 2 + 3 (n - 1) = 3n - 2 + n (n - 1) + 3n - 3 = 6n - 5 + n^2 - n = n^2 + 5n - 5 assignments. Therefore, **A(n) = n^2 + 5n - 5**.
* Counting the total number of arithmetic operations, we get (n - 1) + (n - 1) = 2n - 2.
* For total time, we add these three numbers up and obtain T(n) = n^2 - 1 + n^2 + 5n - 5 + 2n - 2 = 2n^2 + 7n - 8. This is O(n^2) with c = 3 and n = 7. This is also Omega(n^2) with c = 1 and n = 1, so it is Theta(n^2).
