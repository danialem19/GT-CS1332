/**
 * Your implementation of a linked stack.
 *
 * @author DANIEL TADESSE
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The Stack Is Empty");
        }
        T popped = head.getData();
        head = head.getNext();
        size--;
        return popped;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is Null");
        }
        LinkedNode<T> newHead = new LinkedNode(data);
        if (size > 0) {
            newHead.setNext(head);
        }
        head = newHead;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this stack.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}