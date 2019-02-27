public interface Array<T> {
    /** Get the declared capacity of the array.
	@return the length
    */
    int length();

    /** Get the value of the array at a particular index.
	@param index the position of the value requested
	pre-conditions: index >= 0 and index < length()
	@return the value at that position
    */
    T get(int index);

    /** Put a value into the array at a particular position.
	@param t the value being saved
	@param index the position it goes
	pre-conditions: index >= 0 and index < length()
    */
    void put(T t, int index);
}