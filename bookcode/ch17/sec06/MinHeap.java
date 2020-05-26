import java.util.*;

/**
   This class implements a heap.
*/
public class MinHeap <T extends Comparable<? super T>>
{
   private T[] backingArray;
   private int size;

   /**
      Constructs an empty heap.
   */
   public MinHeap()
   {
      backingArray = (T[]) new Comparable[9];
      backingArray[0] = null;
      size = 1;
   }

   /**
      Adds a new element to this heap.
      @param newElement the element to add
   */
   public void add(T newElement)
   {
      boolean available = false;
      for (int i = 1; i <= size - 1; i++) {
         if(backingArray[i].compareTo(newElement) == 0) {
            available = true;
            i = size - 1;
         }
      }
      if(!available) {
         if (size == backingArray.length) {
            T[] newArray = (T[]) new Comparable[(2 * backingArray.length) + 1];
            for (int i = 0; i < backingArray.length; i++) {
               newArray[i] = backingArray[i];
            }
            backingArray = newArray;
         }

         backingArray[size] = newElement;
         size++;
         int index = size - 1;
         while (index > 1 && backingArray[index / 2].compareTo(newElement) < 0)
         {
            backingArray[index] = backingArray[index / 2];
            backingArray[index / 2] = newElement;
            index = index / 2;
         }
      }
   }

   /**
      Gets the minimum element stored in this heap.
      @return the minimum element
   */
//   public Comparable peek()
//   {
//      return elements.get(1);
//   }

   /**
      Removes the minimum element from this heap.
      @return the minimum element
   */
   public T remove()
   {
      T maximum = backingArray[1];

      // Remove last element
      //int lastIndex = size - 1;
      T last = backingArray[size - 1];
      backingArray[size - 1] = null;
      size--;

      if ((size - 1) > 1) {
         backingArray[1] = last;
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
      T root = backingArray[1];

      //int lastIndex = size - 1;
      // Promote children of removed root while they are smaller than root

      int index = 1;
      boolean more = true;
      while (more)
      {
         int childIndex = index * 2;
         if (childIndex <= (size - 1))
         {
            // Get smaller child
            // Get left child first
            T child = backingArray[childIndex];

            // Use right child instead if it is smaller
            int rightChildIndex = (index * 2)  + 1;
            if (rightChildIndex <= (size - 1))
            {
               T rightChild = backingArray[rightChildIndex];
               if(rightChild.compareTo(child) > 0) {
                  childIndex = rightChildIndex;
                  child = rightChild;
               }
            }

            // Check if smaller child is smaller than root
            if (child.compareTo(root) > 0)
            {
               //Promote child
               backingArray[index] = child;
               index = childIndex;
            }
            else
            {
               // Root is smaller than both children
               more = false;
            }
         }
         else
         {
            // No children
            more = false;
         }
      }

      // Store root element in vacant slot
      backingArray[index] = root;
   }

   public String toString() {
      String str = "";
      for(int i = 1; i < size; i++) {
         str = str + " " + backingArray[i];
      }
      return str;
   }
}

