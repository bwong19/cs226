/** Interface for our Array ADT.
    @param <T> the base type of the items in the array
*/
public interface Array<T> {

    /* Constructors should take a length and a starting value.
       Should throw LengthException if length <= 0.
    */

    /** Get the declared capacity of the array.
     *@return the length
    */
    int length();

    /** Get the value of the array at a particular index.
     *@param index the position of the value requested
     *pre-conditions: index >= 0 and index < length()
     *@return the value at that position
     *@throws IndexException if index is out of bounds
    */
    T get(int index) throws IndexException;

    /** Put a value into the array at a particular position.
     *@param t the value being saved
     *@param index the position it goes
     *pre-conditions: index >= 0 and index < length()
     *@throws IndexException if index is out of bounds
    */
    void put(T t, int index) throws IndexException;
}