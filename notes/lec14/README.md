**Objectives:** This in-class exercise has several objectives:

- Understand the List interface
- Read and write doubly linked list code
- Learn to develop a doubly linked list with sentinels
- Practice good testing techniques

**Provided code:** The zip includes the following files:

- List.java is the interface for abstract data type List
- Position.java is the interface for the Position abstraction
- EmptyException.java is the usual
- PositionException.java is the new Position exception class
- LinkedList.java is a partially written doubly linked list implementation of List.java
- ListTest.java is a partially written abstract base test class for the List interface
- LinkedListTest.java is a concrete subclass of ListTest.java base class

The only files you need to edit are LinkedList.java and
ListTest.java. You should start by thoroughly reviewing the provided
List.java interface, comparing it to our sketch of this from class on
Monday. Also pay particular attention to the Node class and the
convert helper method that enables our implementation to validate
Position objects and return them in their concrete Node form.

**Sentinel Nodes:** Your primary job is to first convert the skeleton
code for LinkedList.java to use sentinel nodes at the head and
tail. Here is a sketch of how that would look for a list with one
actual node (realNode):

```
           dummy                       dummy   
          +------+    +----------+    +------+   
head ---> | null |    | realNode |    | null | <--- tail   
          |    --+--->|        --+--->| null |   
          | null |<---+--        |<---+--    |   
          +------+    +----------+    +------+   
```

The first row of each node shows the .data inside. It is null for both
of our dummy sentinel nodes. The second row shows the .next links to
forward neighbors. The third row shows the .prev links to backwards
neighbors. It's important to realize that the sentinel nodes will
ALWAYS be there - even when the list is empty. The goal of using the
sentinel nodes is to eliminate lots of special cases when working at
the ends of the list.

**Other tasks:** The rest of the exercise is to implement the missing
methods in LinkedList and to write more tests in LinkedListTest.java
so you'll know if everything is working. Continue to use sentinel
nodes for the missing methods, and consider how to take advantage of
code reuse to simply methods as much as possible. For example,
insertFront(t) could be implemented as insertAfter(front(), t).

**References:** see lect13.pdf lecture notes, particularly the slides at
the end that we didn't review together in class on Monday.

**Suggested development workflow:**

- [optional] move the JUnit test files to subdirectory (for easier command-line compilation)
- compile and run tests on the provided skeleton code (expect half of them to fail because of missing code)
- change the provided LinkedList code to incorporate sentinel nodes at head and tail
- compile and run tests on your revised code (same ones should still fail)
- add code for the missing methods
- compile and run the original tests - all should pass
- add more tests to LinkedListTest.java
- compile and run your additional tests
