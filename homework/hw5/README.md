Name: Brandon Wong  
JHED: bwong19  
Email: bwong19@jhu.edu  

## Part A: Linking Lists & Sets

* For the test cases, I created tests with assert statements that ensured every method in List.java works. I also included some tests that checked that axioms were satisfied. Lastly, I created test cases for each exception that could be thrown.
* The three JUnit test cases that failed for me were as follows:
  1. **previousWorks:** Calling the previous(p) method threw a PositionException when it should not have.
  2. **previousInvalid:** When I tried to force previous(p) to force a PositionException (by calling previous on the front of the list), the exception would not be thrown. I then created a new test that called previous(p) so that it would reach the end of the list, and only then was a PositionException thrown. Thus, there is reason to believe that previous(p) actually accesses the next element instead of the previous element.
  3. **backwardsIteratorWorks:** When I tried to create a backwards iterator, it appeared that the hasNext() function did not work properly.
* After testing, I looked at and made changes to LinkedList.java:
  1. **previous(Position\<T\> p):** I noticed that the method was not throwing a PositionException correctly. The method should throw exceptions only if the p is the front of the list. Thus, I changed the check from (this.last(p)) to (this.first(p)).
  2. **hasNext():** Only forward iterators worked; backward iterators did not. After looking at the method, I saw that hasNext() did not account for whether the iterator was forward or backward. I created an if/else statement to fix the behavior accordingly.
* After fixing these two methods, all my tests ran successfully.
* When writing ListSet.java, I wrote a find(t) helper method to make the other methods much simpler. I originally forgot to check the empty list case, so I added the empty list check after I realized the mistake.
* I tested ListSet.java using Unique.java. In Unique.java, I created methods that generated arrays (size n, n up to 100000) of ascending, descending, and random values. Unique.java returned n unique values when I ran the program on ascending and descending arrays of size n, and fewer when I ran it on random values, which is expected. I also tested ListSet.java by inserting large amounts of random numbers modulo k, where k << n. I almost always received k unique values at the end, which was expected. When k was closer to n, the proportion of unique values began to decrease, which was also expected.
* In terms of time differences, with 100000 random numbers, ArraySet ran in 2.42 seconds, LinkedSet ran in 9.70 seconds, and ListSet ran in 9.52 seconds. There was a noticeable time difference between ArraySet and the other two sets, but there was no significant time difference between ListSet and LinkedSet. I did not expect a difference in time, since we determined that array-based sets and linked list-based sets should insert in approximately the same time.


## Part B: Terrific Heuristics

* The data set I used was 1000000 random numbers ranging in value from from 0 to 10000. I achieved the following times:
  * ArraySet: 4.08 seconds
  * TransposeArraySet: 3.89 seconds
  * ListSet: 16.51 seconds
  * MoveToFrontListSet: 21.87 seconds
* I discovered that TransposeArraySet was faster than ArraySet (but minimally). MoveToFrontListSet was noticeably slower than ListSet.
* I expected TransposeArraySet to be only be faster than ArraySet by a little because it was only swapping one spot at a time. TransposeArraySet may be significantly faster if one element appeared much more often than any other elements, as it would be moved to the front and accessed often; this access would be faster than a normal ArraySet.
* However, I did not expect MoveToFrontListSet to be slower than ListSet. It appears that moving to the front takes more calls to functions than it saves (every time an element is found, a call is made to remove(p) and insertFront(t)), at least with random data. With skewed data (when one value appears significantly more often than other values), the heuristic may improve the time, but in the general case, MoveToFrontListSet is slower than ListSet.


## Part C: More Set Operations

* Let S_N  be the set of size N and S_M be the set of size M.
* Union of unordered sets
  * To implement a union operation with arrays, suppose every element in S_M is added into a new array, which is O(M). I would then compare every element n in S_N to every element m in S_M and add to the new set only if it is not in S_M. To do so, each element n must undergo a search in S_M, which takes O(M) time. Since there are N elements in S_N, this results in a total of O(N * M + M) = O((N + 1) * M) = O(N * M) for the insertion of the elements in S_N into the new array. The total time is thus **O(N * M)**.
  * To implement a union operation with linked sets, the total time is also **O(N * M)**. This is because the search in S_M also takes O(M) time, and there are N elements that undergo the search.
* Union of ordered sets
  * In ordered sets using arrays, I would create loop variables (i for S_N, j for S_M). I would create a new array, and run through both arrays. If S_N[i] < S_M[j], I would add S_N[i] to the union and increment i. If S_N[i] > S_M[j], I would add S_M[j] to the union and increment j. If they are equal, then I would add either of them and increment both i and j. At the end, i increments N times and j increments M times, and we make one comparison approximately every time. Thus, our total time is **O(N + M)**.
  * In ordered sets using linked sets, I would undergo the same procedure as for arrays. This is valid since we're just iterating through each array. our total time is **O(N + M)**.
* Intersections
  * Performing union and intersection operations will take similar times. When iterating through each set for a union operation, we have to check what elements the sets have in common so that we do not overcount, and we add every other element in either set. This is essentially an intersection operation, in which we find which elements are in both sets, i.e. what elements the sets have in common. Thus, we have to make the same amount of iterations for intersections as we do for unions.
  * Intersection of unordered sets
    * Arrays: **O(N * M)**
    * Linked Sets: **O(N * M)**
  * Intersection of ordered sets
    * Arrays: **O(N + M)**
    * Linked Sets: **O(N + M)**