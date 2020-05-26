import java.util.Comparator;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author DANIEL TADESSE
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Due to null Array or"
                    + " comparator check your data please");
        }
        boolean swapIsMade = true;
        int i = 0;
        while (swapIsMade) {
            swapIsMade = false;
            for (int j = 0; j < (arr.length - 1) - i; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapIsMade = true;
                }
            }
            i++;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Due to null Array or"
                    + " comparator check yur data please");
        }
        for (int i = 1; i < arr.length; i++) {
            T unsorted = arr[i];
            int un = i;
            for (int j = i; j > 0; j--) {
                if (comparator.compare(arr[j - 1], unsorted) > 0) {
                    arr[j] =  arr[j - 1];
                    un--;
                } else {
                    j = 0;
                }
            }
            arr[un] = unsorted;
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Due to null Array or"
                    + " comparator or random object check yur data please");
        }
        quickSortHelper(arr, comparator, rand, 0, arr.length - 1);
    }

    /**
     * This is recursive helper method to partition and sort left and right
     * partitions of the array.
     * @param arr Array to be sorted
     * @param comparator comparator object
     * @param rand random object used to generate pivot index
     * @param left starting index, which is the left most index
     * @param right ending index, which is the right most index
     * @param <T> type of array elements we are working with
     */
    private static <T> void quickSortHelper(T[] arr, Comparator<T> comparator,
                                            Random rand, int left, int right) {
        if (right > left) {
            int pivotIndex = rand.nextInt(right - left + 1) + left;
            int pivot = partition(arr, comparator, pivotIndex, left, right);
            if ((pivot - 1) > left) {
                quickSortHelper(arr, comparator, rand, left, pivot - 1);
            }
            if ((pivot + 1) < right) {
                quickSortHelper(arr, comparator, rand, pivot + 1, right);
            }
        }
    }

    /**
     * This is a helper method that for quickSortHelper method. It swaps
     * the pivot element and returns the index where the swap is performed.
     *
     * @param <T> the data type of the array elements to be sorted
     * @param arr the given array for soring
     * @param comparator the Comparator object used to compare the array values
     * @param left the min index
     * @param right the max index
     * @param pivotIndex the pivot element index
     * @return the index where pivot element was swapped
     */
    private static <T> int partition(T[] arr, Comparator<T> comparator,
                                     int pivotIndex, int left, int right) {
        T pivotValue = arr[pivotIndex];
        arr[pivotIndex] = arr[left];
        arr[left] = pivotValue;
        int leftIndex = left + 1;
        int rightIndex = right;
        T temp;
        while (leftIndex <= rightIndex) {
            while ((leftIndex <= rightIndex) && (comparator.
                    compare(arr[leftIndex], pivotValue)) <= 0) {
                leftIndex++;
            }
            while ((leftIndex <= rightIndex) && (comparator.
                    compare(arr[rightIndex], pivotValue)) >= 0) {
                rightIndex--;
            }
            if (leftIndex <= rightIndex) {
                temp = arr[leftIndex];
                arr[leftIndex] = arr[rightIndex];
                arr[rightIndex] = temp;
                leftIndex++;
                rightIndex--;
            }
        }
        temp = arr[rightIndex];
        arr[rightIndex] = arr[left];
        arr[left] = temp;
        return rightIndex;
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if ((arr == null) || (comparator == null)) {
            throw new IllegalArgumentException("array is null"
                    +  "or comparator is null check you data please");
        }
        mergeSortHelper(arr, comparator);
    }
    /**
     * This is a helper method for merge sort. It divides the given array into
     * left and right sub arrays recursively. Then, starts sorting when size < 2
     * @param <T> data type of the array items
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in array
     */
    private static <T> void mergeSortHelper(T[] arr, Comparator<T> comparator) {

        if (arr.length < 2) {
            return;
        }
        int middleIndex = arr.length / 2;
        T[] leftArray = (T[]) new Object[middleIndex];
        for (int i = 0; i < leftArray.length; i++) {
            leftArray[i] = arr[i];
        }
        T[] rightArray = (T[]) new Object[arr.length - middleIndex];
        for (int j = 0; j < rightArray.length; j++) {
            rightArray[j] = arr[j + middleIndex];
        }
        mergeSortHelper(leftArray, comparator);
        mergeSortHelper(rightArray, comparator);

        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;
        while ((leftIndex < middleIndex) && (rightIndex < (arr.length - middleIndex))) {
            if (comparator.compare(
                    leftArray[leftIndex], rightArray[rightIndex]) <= 0) {
                arr[currentIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                arr[currentIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }

        while (leftIndex < middleIndex) {
            arr[currentIndex] = leftArray[leftIndex];
            leftIndex++;
            currentIndex++;
        }

        while (rightIndex < (arr.length - middleIndex)) {
            arr[currentIndex] = rightArray[rightIndex];
            rightIndex++;
            currentIndex++;
        }
    }


    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Due to null array");
        }
        java.util.LinkedList<Integer>[] buckets = new java.util.LinkedList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new java.util.LinkedList<>();
        }

        int divisor = 1;
        while (divisor <= pow(10, 9)) {
            for (int i = 0; i < arr.length; i++) {
                int index = (arr[i] / divisor) % 10;
                buckets[index + 9].add(arr[i]);
            }
            int arrIndex = 0;
            for (int j = 0; j < buckets.length; j++) {
                while (!(buckets[j].isEmpty())) {
                    arr[arrIndex] = buckets[j].remove();
                    arrIndex++;
                }
            }
            divisor *= 10;
        }
        return arr;

    }

    /**
     * Implement MSD (most significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should:
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] msdRadixSort(int[] arr) {
        //Same as LSD, I couldn't finish MSD.
        if (arr == null) {
            throw new IllegalArgumentException("Due to null array");
        }
        java.util.LinkedList<Integer>[] buckets = new java.util.LinkedList[20];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new java.util.LinkedList<>();
        }

        int tmp;
        int divisor = 1;
        while (divisor <= pow(10, 9)) {
            for (Integer i : arr) {
                if (i < 0) {
                    tmp = i / divisor;
                    buckets[Math.abs(tmp % 10)].add(i);
                } else {
                    tmp = i / divisor;
                    buckets[(tmp % 10) + 10].add(i);
                }
            }
            int a = 0;
            for (int b = 9; b >= 0; b--) {
                while (!buckets[b].isEmpty()) {
                    arr[a++] = buckets[b].remove();
                }
            }

            for (int b = 10; b < 20; b++) {
                while (!buckets[b].isEmpty()) {
                    arr[a++] = buckets[b].remove();
                }
            }
            divisor *= 10;
        }
        return arr;
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * halfPow * base;
        }
    }
}
