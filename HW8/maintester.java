import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Danny on 4/8/17.
 */

public class maintester {


    /**
     * @param args argument
     */
    private static int[] s = {3, 6, 100, 2, 5, 8, 4};
    //private static int[] s = {1, 334, 23, 2, 12, 23, 6, 4};
    private static int[] m = {7, 6, 5, 4, 3, 2, 1};
    private static int[] q = {20, 19, 18, 17, 16, 15, 114};

    public static void main(String[] args) {
        selectionSort();
        //cocktailSort(s);
        //System.out.println("Selection sort: " + Arrays.toString(s));
//
//        mergeSort(m);
//        System.out.println("Merge sort: " + Arrays.toString(m));
//
//        quickSort();
//        System.out.println("Quick sort: " + Arrays.toString(q));

//        lsdRadixSort(s);
//        System.out.println("Radix S: " + Arrays.toString(s));
//
//        lsdRadixSort(m);
//        System.out.println("Radix M: " + Arrays.toString(m));
//
//        lsdRadixSort(q);
//        System.out.println("Radix Q: " + Arrays.toString(q));
        //insertionSort(s);
        //System.out.println("Insertion S: " + Arrays.toString(s));
    }

    public static void cocktailSort(int[] arr) {
        boolean go = true;
        int end = arr.length - 1;
        int beginning = 0;
        while (go) {
            go = false;
            for (int i = beginning; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    go = true;
                }
            }
            if (!go) {
                return;
            }
            go = false;
            end--;
            for (int j = (end - 1); j >= beginning; j--) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    go = true;
                }
            }
            beginning++;
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void lsdRadixSort(int[] arr) {
        java.util.LinkedList<Integer>[] buckets = new java.util.LinkedList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new java.util.LinkedList<>();
        }

        int divisor = 1;
        while (divisor <= pow(10, 9)) {
            for (int k = 0; k < arr.length; k++) {
                int index = (arr[k] / divisor) % 10;
                buckets[index + 9].add(arr[k]);
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
    }

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


    public static void quickSort() {
        Random rand = new Random();
        quickSortHelper(q, rand, 0, q.length);
    }

    private static void quickSortHelper(int[]a, Random rand, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotIndex = rand.nextInt(right - left) + left;
        int pivot = a[pivotIndex];
        a[pivotIndex] = a[left];
        a[left] = pivot;
        int leftIndex = left + 1;
        int rightIndex = right - 1;
        while (leftIndex < rightIndex) {
            while ((leftIndex < rightIndex) && (a[leftIndex] <= pivot)) {
                leftIndex = leftIndex + 1;
            }
            while ((leftIndex <= rightIndex) && (a[rightIndex] >= pivot)) {
                rightIndex = rightIndex - 1;
            }
            if (leftIndex < rightIndex) {
                int temp = a[leftIndex];
                a[leftIndex] = a[rightIndex];
                a[rightIndex] = temp;
                leftIndex = leftIndex + 1;
                rightIndex = rightIndex - 1;
            }
        }
        if (leftIndex !=  rightIndex) {
            int temp = a[left];
            a[left] = a[rightIndex];
            a[rightIndex] = temp;
        } else {
            if (a[rightIndex] < a[left]) {
                int temp = a[left];
                a[left] = a[rightIndex];
                a[rightIndex] = temp;
            }
        }
        quickSortHelper(a, rand, left, rightIndex);
        quickSortHelper(a, rand, rightIndex + 1, right);
    }


    public static void mergeSort(int[] a) {
        if (a.length < 2) {
            return;
        }
        int middleIndex = a.length / 2;
        int[] leftArray = new int[middleIndex];
        for (int i = 0; i < leftArray.length; i++) {
            leftArray[i] = a[i];
        }
        int[] rightArray = new int[a.length - middleIndex];
        for (int j = 0; j < rightArray.length; j++) {
            rightArray[j] = a[j + middleIndex];
        }
        mergeSort(leftArray);
        mergeSort(rightArray);

        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;
        while ((leftIndex < leftArray.length) && (rightIndex < rightArray.length)) {
            if (leftArray[leftIndex] < rightArray[rightIndex]) {
                a[currentIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                a[currentIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }

        while (leftIndex < leftArray.length) {
            a[currentIndex] = leftArray[leftIndex];
            leftIndex++;
            currentIndex++;
        }

        while (rightIndex < rightArray.length) {
            a[currentIndex] = rightArray[rightIndex];
            rightIndex++;
            currentIndex++;
        }
    }

    /**
     *
     */
    public static void selectionSort() {
        for (int i = 0; i < s.length - 1; i++) {
            System.out.println(Arrays.toString(s));
            int smallest = s[i];
            for (int j = i + 1; j < s.length; j++) {
                if (s[j] < smallest) {
                    int temp = smallest;
                    smallest = s[j];
                    s[j] = temp;
                }
            }
            s[i] = smallest;
        }
        System.out.println(Arrays.toString(s));
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            System.out.println(Arrays.toString(s));
            int unsorted = arr[i];
            int un = i;
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > unsorted) {
                    arr[j] =  arr[j - 1];
                    un--;
                } else {
                    j = 0;
                }
            }
            arr[un] = unsorted;
        }
        System.out.println(Arrays.toString(s));
    }
}
