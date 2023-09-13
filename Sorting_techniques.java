import java.util.Arrays;
import java.util.Scanner;

public class Sorting_techniques {

    static Scanner in = new Scanner(System.in);
    static long start, end;
    public static void main(String[] args) throws InterruptedException {
        System.out.print("Enter the size of the array: ");
        int n = in.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Selection Sort");
            System.out.println("3. Insertion Sort");
            System.out.println("4. Merge Sort");
            System.out.println("5. Quick Sort");
            System.out.println("6. Heap Sort");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int ch = in.nextInt();

            if (ch == 7) {
                System.out.println("Exiting...");
                break;
            }

            int[] Arr = Arrays.copyOf(arr, arr.length);

            switch (ch) {
                case 1: bubbleSort(Arr);
                        break;
                case 2: selectionSort(Arr);
                        break;
                case 3: insertionSort(Arr);
                        break;
                case 4: mergeSort(Arr, 0, Arr.length - 1);
                        break;
                case 5: quickSort(Arr, 0, Arr.length - 1);
                        break;
                case 6: heapSort(Arr);
                        break;
                default: System.out.println("Invalid choice. Please try again.");
            }

            System.out.print("Unsorted array: ");
            for (int value : arr) {
                System.out.print(value + " ");
            }
            System.out.println();

            System.out.print("Sorted array: ");
            for(int value : Arr) {
                System.out.print(value + " ");
            }
            System.out.println();
            System.out.print("Time take: " + (end - start) + " ns");

            Thread.sleep(1000);
        }
        in.close();
    }


    //Bubble Sort
    static void bubbleSort(int[] arr) {

        int n = arr.length;
        start = System.nanoTime();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        end = System.nanoTime();
    }

    //Selection sort
    static void selectionSort(int[] arr) {
        int n = arr.length;
        start = System.nanoTime();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        end = System.nanoTime();
    }

    //Insertion Sort
    static void insertionSort(int[] arr) {
        int n = arr.length;
        start = System.nanoTime();
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        end = System.nanoTime();
    }

    // Merge Sort
    static void mergeSort(int[] arr, int left, int right) {
        start = System.nanoTime();
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
        end = System.nanoTime();
    }

    static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    //Quick Sort
    static void quickSort(int[] arr, int low, int high) {
        start = System.nanoTime();
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
        end = System.nanoTime();
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    //Heap Sort
    static void heapSort(int[] arr) {
        int n = arr.length;
        start = System.nanoTime();
        for (int i = n / 2 - 1; i >= 0; i--) {
            heap(arr, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heap(arr, i, 0);
        }
        end = System.nanoTime();
    }

    static void heap(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heap(arr, n, largest);
        }
        end = System.nanoTime();
    }
}
