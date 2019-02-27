public class SimpleArray<T> implements Array<T> {
    
    private T[] data;

    /** Constructor takes a length and a starting value
	@param len the capacity of the array
	@param val the starting value
    */
    public SimpleArray(int len, T val) {
	data = (T[]) new Object[len];
	for (int i = 0; i < len; ++i) {
	    data[i] = val;
	}
    }

    /** Get the declared capacity of the array.
	@return the length
    */
    public int length() {
	return data.length;
    }

    /** Get the value of the array at a particular index.
	@param index the position of the value requested
	pre-conditions: index >= 0 and index < length()
	@return the value at that position
    */
    public T get(int index) {
	if (index >= 0 && index < length()) {
	    return data[index];
	}
	throw new ArrayIndexOutOfBoundsException();
    }

    /** Put a value into the array at a particular position.
	@param t the value being saved
	@param index the position it goes
	pre-conditions: index >= 0 and index < length()
    */
    public void put(T t, int index) {
	if (index >= 0 && index < length()) {
	    data[index] = t;
	} else {
	    throw new ArrayIndexOutOfBoundsException();
	}
    }
}