/** Simple implementation of the Array interface
    using Java build-in array with generics.
    @param <T> the base type of the objects in the array
*/
public class SimpleArray<T> implements Array<T> {
    
    private T[] data;

    /** Constructor takes a length and a starting value.
     *@param len the capacity of the array
     *@param val the starting value
     *@throws LengthException if length <= 0
    */
    public SimpleArray(int len, T val) throws LengthException {
        if (len <= 0) {
            throw new LengthException();
        }
        data = (T[]) new Object[len];
        for (int i = 0; i < len; ++i) {
            data[i] = val;
	}
    }
    
    /** Get the declared capacity of the array.
     *@return the length
    */
    public int length() {
	return data.length;
    }

    @Override
    public T get(int index) throws IndexException {
	if (index >= 0 && index < length()) {
	    return data[index];
	}
	throw new IndexException();
    }

    @Override
    public void put(T t, int index) throws IndexException {
	if (index >= 0 && index < length()) {
	    data[index] = t;
	} else {
	    throw new IndexException();
	}
    }
}