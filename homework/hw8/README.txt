Dikshith Kasimahanthi, Brandon Wong
At the beginning we decided to do seperate chaining to start off our base line HashMap. We were unable to get it to run quickly and so we decided to move on to linear probing. And this ran so much faster than separate chaining that
we decided not to try to optimize the separate chaining version. 

Then we started to do linear probing. In linear probing we initialized the array to a certain value and proceeded to just double the array size. Then we implemented all of the functions based on linear probing.
All of this probing was just done in our insert method. First we used Java's hash method to convert our key into an integer. Then we probed through our data to insert the method. We called our rehash method if the load
in the array was equal to or above 0.5 after the insert. In the rehash method we made a temporary array of entries that had increased capacity and then looped over the initial hashmap and copied over all the data. Then we set
our old hashmap equal to our new hashmap.

We changed from linear probing to quadratic, cubic and quartic and we found the following runtimes respectively: 1.294s, 1.280s, 1.319s, 1.295s (random164.txt). We decided that these benchmark times were too close so we just decided to benchmark all of them.

We decided to keep track of the elements in the array this time so when we copied over, instead of looping through the entire array we just looped until the total number of elements
in the array were copied over. When one was copied over we would increase our tracking variable by one until we equaled the number of elements in the array. We also decided to check if the data in the old array was null, and it if it was
we just immediately continued to the next iteration of the loop. This was like a smart cutoff so it wouldn't copy over null elements. 

In our find method we realized we initially accidentally made our key a tombstone. By this we mean that we check if the entry is null then we would immediately return: there is no element. If it wasn't null then we compared the key
and if the key matched we returned the entry in the array. This was a tombstone type of thing because if an entry existed there, then the entry itself wouldn't be null but the key would be null. If the key is null then our comparison of the key we
are searching for would return false and we would keep looping. If the entry was null itself then that means nothing was ever mapped here in the first place. By doing the comparison with the key, we check if we need to keep probing or not. The return
if the entry was null was a smart cut off and so we knew we didn't need to keep searching. This ends up helping our timing by a lot because our get, find, and has methods all rely on our find method. We benchmarked our linear
probing HashMap with SimpleMap. It took 10 minutes to index all of the apache.txt file but it took under two seconds for our linear probing hashmap. We realized our accidental implementation of this helped our time by a lot. 
We don't have benchmarking tests for without this tombstone because this is a smart cut off and if we were to go back and remove the tombstone, it will make the hashmap slower. 

When we were implementing our toString() method we initially didn't check if the key was null when we were looking for elements to print. This turned out to be a major problem because it turned out that we were initializing Entry elements
but we set the key and value of the element to null, but that entry object still existed. And since it existed toString() detected that and would print null and null. Once we realized this we checked if the key was null, which was also basically
checking our tombstone and seeing if it had a value or if it was null. If it was null we just continued and didn't print. This made it so we weren't wasting time processing incorrect data, but we also didn't waste the initialized space that actually
had values in them and weren't printing because we cut them off early.

The tombstone we knew we had to implement from in class when she mentioned it but we didn't realize we sort of accidentally implement it. 

Another thing we decided to do was initialize array sizes to only prime values. We also remembered this because it was mentioned in class. We couldn't find a reasonable way to generate primes so we initialized an array that would have primes that were
at least double the previous prime and we did this until maxInt. Then we made a variable that would keep track of which size the array was currently. We increased the index by one everytime we doubled. Our benchmark times for linear probing before we changed the
array size to only primes were (average of 5) 1.294s. After we changed the the arrays to have only a capacity that was prime had a (average of 5) benchmark time for linear, quadratic, cubic and quartic respectively: 1.061s, 0.989s, 1.155s, 1.183s. These benchmarks
were done for random164.txt. Again in the same order for newegg.txt: 0.349s, 0.336s, 0.353s, 0.319s. From these benchmarks it seems like quadratic probing is consistently faster than the rest of the probing methods.  



 



JHUgle:
For this implementation we started off by reading in the text file which would contain a list of URLs and their associated keywords. We decided to store
the keywords onto a stack so that when the user calls an operation it would apply to the most recent keywords. We then planned to store the URLs printed by these
two keywords for future reference. We didn't want to search the HashMap for the URLs again because any future operations would rely on these URLs anyway. We decided to
store these by making a map whose key was the keyword and the value was the list of URLs associated with that keyword.

We planned to do this by making a stack that would take in a list of URLs. We would ask the user to type in a word and we would immediately search the hashtable for the associated
url, store them in a list, and push them onto a stack. As long as two lists of URLs were in the stack, we would pop them off and compare them for the "and" or "or" operators called on them.
We had a few problems printing the elements of the stack because we had to pop the elements off the stack to print them, but the elements needed to still be saved even if we did this. We needed
another way to print the elements and save the URLs. 

Then we realized we made a mistake. We were only supposed to print the list of URLs at the top of the stack and not the URLs stored in the entire stack. This made our life so much easier and we realized
we wasted so much time. 