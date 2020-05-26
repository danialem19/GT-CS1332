/**
 * Your implementation of an ArrayList.
 *
 * @author DANIEL TADESSE
 * @version 1
 */
@SuppressWarnings("unchecked")
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Increases the size of the backing array by two times if its size equals
     * the number of data elements exactly available in it
     */
    private void increaseBackingArray() {
        if (size == backingArray.length) {
            T[] newArray = (T[]) new Object[2 * backingArray.length];
            for (int i = 0; i < backingArray.length; i++) {
                newArray[i] = backingArray[i];
            }
            backingArray = newArray;
        }
    }
    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("ERROR due to index < 0 or"
                    + " index > size.");
        }
        if (data == null) {
            throw new IllegalArgumentException("ERROR: Data value cannot be "
                    + "Empty/Null");
        }
        increaseBackingArray();
        for (int i = size; i > index; i--) {
            backingArray[i] = backingArray[i - 1];
        }
        backingArray[index] = data;
        size++;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("ERROR: Data value cannot be "
                    + "Empty/Null");
        }
        increaseBackingArray();
        for (int i = size; i > 0; i--) {
            backingArray[i] = backingArray[i - 1];
        }
        backingArray[0] = data;
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("ERROR: Data value cannot be "
                    + "Empty/Null");
        }
        increaseBackingArray();
        backingArray[size] = data;
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("ERROR due to index < 0 or"
                    + " index >= size.");
        }
        T theRemoved = backingArray[index];
        if (index != size - 1) {
            for (int i = index; i < size - 1; i++) {
                    backingArray[i] = backingArray[i + 1];
            }
        }
        backingArray[size - 1] = null;
        size--;
        return theRemoved;
    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }
        T theRemoved = backingArray[0];
        for (int i = 1; i < size; i++) {
            backingArray[i - 1] = backingArray[i];
        }
        backingArray[size - 1] = null;
        size--;
        return theRemoved;
    }

    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        }
        T theRemoved = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return  theRemoved;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("ERROR due to index < 0 or"
                    + " index >= size.");
        }
        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                result = result + ",";
            }
            T item = backingArray[i];
            result = result + item;
        }
        result = result + "]";
        return result;
    }
}
