Name: Brandon Wong  
JHED: bwong19  
Email: bwong19@jhu.edu  

## Part A: RPN Calculator

__Errors__
* __divide/remainder by 0:__ If the user tries to divide by 0 or take the remainder by 0, the operation should fail. This is because division (and, for the same reasons, remainder) by 0 is not defined. I discovered this error when trying to undergo division and remainder for various numbers.
* __not enough arguments:__ If there are less than two operands in the stack when completing an operation, the program would normally throw an EmptyException. To handle this, I checked to see whether the stack was empty when popping. I discovered this when continually trying to call operations even after the stack was only size 1.
* __empty stack:__ This error was similar to the "not enough arguments" error, but I decided to change the error message for a more specific case: the "not enough arguments" error makes sense only if an operation is attempted; if the user just tries to pop something off the stack (using the . token) and not perform an operation, it technically isn't an "argument", so I decided to output the message "empty stack". I found this when trying to pop something off of an empty stack, which resulted in an EmptyException.
* __bad token:__ Although this error did not throw an exception, inputting random tokens caused incorrect pushes and pops in my stack, so I decided to throw an error and continue running operations. I noticed this error when attempting to break the code with tokens like "blah" and "1.0", as given by the examples.


## Part B: Hacking Growable Deques

* I initially had trouble determining how to write the toString() method, as I have never used StringBuilder before. However, I looked at ListDeque226.java as a reference and was able to figure it out.
