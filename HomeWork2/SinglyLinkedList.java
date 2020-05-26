/**
 * Your implementation of a SinglyLinkedList
 *
 * @author DANIEL TADESSE
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is negative or"
                    + " index >= size");
        }
        if (data == null) {
            throw new IllegalArgumentException("Error due to Null Data Value");
        }
        LinkedListNode<T> newNode = new LinkedListNode<>(data);
        if (index == 0) {
            if (size == 0) {
                head = newNode;
                tail = head;
            } else {
                newNode.setNext(head);
                head = newNode;
            }
        } else if (index == size) {
            if (size == 0) {
                tail = newNode;
                head = tail;
            } else if (size == 1) {
                tail = newNode;
                head.setNext(tail);
            } else {
                tail.setNext(newNode);
                tail = newNode;
            }
        } else {
            LinkedListNode<T> currentNode = head;
            LinkedListNode<T> previousNode = null;
            for (int i = 0; i < index; i++) {
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            previousNode.setNext(newNode);
            newNode.setNext(currentNode);
        }
        size++;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error due to Null Data Value");
        }
        LinkedListNode<T> newHead = new LinkedListNode(data);
        if (size == 0) {
            head = newHead;
            tail = head;
        } else {
            newHead.setNext(head);
            head = newHead;
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error due to Null Data Value");
        }
        LinkedListNode<T> newTail = new LinkedListNode(data);
        if (size == 0) {
            tail = newTail;
            head = tail;
        } else if (size == 1) {
            tail = newTail;
            head.setNext(tail);
        } else {
            tail.setNext(newTail);
            tail = newTail;
        }
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is negative or"
                    + " index >= size");
        }
        if (isEmpty()) {
            return null;
        }
        LinkedListNode<T> currentNode = head;
        LinkedListNode<T> previousNode = null;
        for (int i = 0; i < index; i++) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        if (index == 0) {
            head = head.getNext();
            if (size <= 2) {
                tail = head;
            }
        } else if (index == size - 1) {
            tail = previousNode;
            previousNode.setNext(null);
        } else {
            previousNode.setNext(currentNode.getNext());
        }
        size--;
        return currentNode.getData();
    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }
        T removedData = head.getData();
        head = head.getNext();
        if (size == 1) {
            tail = head;
        }
        size--;
        return removedData;
    }

    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        }
        LinkedListNode<T> currentNode = head;
        LinkedListNode<T> previousNode = null;
        while (currentNode.getNext() != null) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        if (size == 1) {
            head = null;
            tail = head;
        } else {
            tail = previousNode;
            previousNode.setNext(null);
        }
        size--;
        return currentNode.getData();
    }

    @Override
    public T removeFirstOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error due to Null Data Value");
        }
        LinkedListNode<T> currentNode = head;
        LinkedListNode<T> previousNode = null;

        for (int i = 0; i < size; i++) {
            if (currentNode.getData().equals(data)) {
                if (i == 0) {
                    head = head.getNext();
                    if (size == 1) {
                        tail = head;
                    }
                }  else if (i == size - 1) {
                        tail = previousNode;
                        previousNode.setNext(null);
                } else {
                    previousNode.setNext(currentNode.getNext());
                }
                size--;
                return currentNode.getData();
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        throw new java.util.NoSuchElementException("Data value is not found");
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is negative or"
                    + " index >= size");
        }
        if (index == 0) {
            return head.getData();
        }
        if (index == size - 1) {
            return tail.getData();
        }
        LinkedListNode<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();
    }

    @Override
    public Object[] toArray() {
        LinkedListNode<T> position = head;
        Object[] myArray = new Object[size];
        for (int i = 0; i < size; i++) {
            myArray[i] = position.getData();
            position = position.getNext();
        }
        return myArray;
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
        if (size != 0) {
            head = null;
            tail = null;
            size = 0;
        }
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
    @Override
    public String toString() {
        LinkedListNode<T> position = head;
        String string = "[";
        while (position != null) {
            string = string + position.getData();
            position = position.getNext();
        }
        string = string + "]";
        return string;
    }
}
