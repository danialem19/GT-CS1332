import java.util.*;

/**
 This class implements a heap.
 */
public class heapTest
{
    private Comparable[] elements;
    private  int size;

    /**
     Constructs an empty heap.
     */
    public heapTest()
    {
        size = 0;
        elements = new Comparable[10];
        elements[0] = null;
    }

    /**
     Adds a new element to this heap.
     @param newElement the element to add
     */
    public void add(Comparable newElement)
    {
        // Add a new leaf
        elements[++size] = newElement;
        int index = size - 1;

        // Demote parents that are Smaller than the new element
        while (index > 1 && elements[index].compareTo(newElement) < 0)
        {
            elements[index] = elements[index / 2];
            elements[index / 2] = newElement;
            index = index / 2;
        }
    }

    /**
     Removes the minimum element from this heap.
     @return the minimum element
     */
    public Comparable remove()
    {
        Comparable maximum = elements[1];

        // Remove last element
        int lastIndex = size - 1;
        Comparable last = elements[lastIndex];
        elements[lastIndex] = null;

        if (lastIndex > 1)
        {
            elements[1] = last;
            fixHeap();
        }

        return maximum;
    }

    /**
     Turns the tree back into a heap, provided only the root
     node violates the heap condition.
     */
    private void fixHeap()
    {
        Comparable root = elements[1];
        int index = 1;
        boolean more = true;
        while (more)
        {
            int childIndex = index * 2;
            if (childIndex <= size)
            {
                Comparable childL = elements[index * 2];
                Comparable childR = elements[index * 2 + 1];
                if ((index * + 1) <= size && elements[index * 2 + 1].compareTo(childL) > 0)
                {
                    childIndex = index * 2 + 1;
                    childL = childR;
                }

                if (childL.compareTo(root) > 0)
                {
                    elements[index] = childL;
                    index = childIndex;
                } else {
                    more = false;
                }
            } else {
                more = false;
            }
        }
        elements[index] = root;
    }

    public boolean empty()
    {
        return elements.length == 1;
    }

    public String myArray() {
        String arr = "";
        for(int i = 1; i < size; i++) {
            arr = arr + elements[i] + ",";
        }
        return arr;
    }

}
