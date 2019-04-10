## Part A: Test-first development

* __AvlTreeMapTest:__ One of the problems I encountered was the fact that my rotation functions are all private. In order to test this, I created test cases that depended on the order in which elements are inserted (or removed), and performed certain rotations accordingly. Test cases that I coded were as follows (starred numbers are added last for insertion tests, and dashed numbers are only included and removed for remove tests):
  * Single right:
  
                   30                       25
                  /  \                    /    \
                 25   31                20      30
                /  \    \   --->          \    /  \
               20   26  -35-             *21* 26  31
                 \
                 *21*
             
  * Single left:
  
                   20                       25
                  /  \                    /    \
                16    25    --->        20      30
               /     /  \              /  \    /
             -15-   21   30           16  21 *26* 
                        /  
                      *26*  
                      
  * Double right left:
  
                   20                       25
                  /  \                    /    \
                 16  30                 20      30
                /   /  \    --->       /  \       \
              -15- 25   31            16  *21*     31
                  /    
                *21*         
                      
  * Double left right:
  
                   30                       25
                  /  \                    /    \
                 20   31                20      30
                /  \    \   --->       /       /  \
               16   25  -35-          16     *26*  31
                      \
                      *26*    
                  
* __TreapMapTest:__ Because nodes are typically assigned random priorities in Treaps, I tested the Treap by printing out the priorities (modifying the toString) and ensuring they satisfied the heap property. I removed various nodes (external nodes, internal nodes, root) to test the functionality of Treap. I also created a function that allows one to insert nodes with user inputted priorities. I coded the following examples:
  * Single right:
  
                 30,40                   *20,50*
                /     \     --->               \
            *20,50*  40,30                    30,40         
                                                 \
                                                40,30
                  
  * Single left:
  
                 30,40                   *40,50*
                /     \     --->         /
             20,30  *40,50*           30,40         
                                       /
                                    20,30
                                    
                                    
## Part D: Benching Word Counts

* In order to benchmark each of the maps, I performed tests on random data sets. I had three data sets: a randomly generated list of English words of size 1841, the script of _The Bee Movie_ of size 9478, and a lorem ipsum of size 100000; these three data sets vary by approximately one order of magnitude. For each of these data sets, I performed three tests on each type of map, resulting in a total of 36 total tests. I averaged the results of each test on each data set. The results are as follows:

| (all times in seconds) | random1841 | bee9478 | lorem100000 |
|------------------------|------------|---------|-------------|
| SimpleMap              |    0.061   |  0.184  |    0.241    |
| BinarySearchTreeMap    |    0.062   |  0.106  |    0.154    |
| AvlTreeMap             |    0.056   |  0.106  |    0.163    |
| TreapMap               |    0.060   |  0.107  |    0.178    |

* Although not shown in this table, the TreapMap tests within each average were the least consistent, which is likely because the priorities are randomly generated.
* SimpleMap is the only non-tree structure, so it is expected to grow slowly. We can observe that as the number of words increases, SimpleMap becomes slower than the other map implementations.
* BinarySearchTreeMap, AvlTreeMap, and TreapMap grow similarly, and it appears to grow in approximately O(logN) time. BinarySearchTreeMap is the fastest out of the tree map implementations for larger data sets. This seems unintuitive, as a balanced tree should theoretically result in lower worst case times, and AvlTreeMap is balanced and TreapMap is expected to be balanced.
* The best explanations I am able to come up with are that the data sets I provided are not worst case scenarios, or the rotations for AvlTreeMap and TreapMap take more time than they save for these data sets.
* Overall, however, AvlTreeMap and TreapMap seem to follow their expected time complexity.