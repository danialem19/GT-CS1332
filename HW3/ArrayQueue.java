
/**
 * Your implementation of an array-backed queue.
 *
 * @author DANIEL TADESSE
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {
    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int back;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        front = 0;
        back = 0;
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you must not
     * explicitly reset front or back to 0.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The Queue is Empty");
        }
        T removedData = backingArray[front];
        backingArray[front] = null;
        front = (front + 1) % backingArray.length;
        size--;
        return removedData;
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to (double the current length) + 1; in essence, 2n + 1, where n
     * is the current capacity. If a regrow is necessary, you should copy
     * elements to the front of the new array and reset front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null Data Value");
        }
        if (size == backingArray.length) {
            T[] newArray = (T[]) new Object[(2 * backingArray.length) + 1];
            for (int i = 0; i < backingArray.length; i++) {
                newArray[i] = backingArray[(front + i) % backingArray.length];
            }
            backingArray = newArray;
            front = 0;
            back = size;
        }
        backingArray[back] = data;
        back = (back + 1) % backingArray.length;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY!
        return backingArray;
    }

    @Override
    public String toString() {
        String string = "[";
        for (int i = 0; i < size; i++) {
            string = string + (backingArray[(front + i) % backingArray.length]);
        }
        string = string + "]";
        return string;
    }

    /**
     *
     * @return front index
     */
    public int getFront() {
        return front;
    }

    /**
     *
     * @return back
     *
     */

    public int getBack() {
        return  back;
    }

}