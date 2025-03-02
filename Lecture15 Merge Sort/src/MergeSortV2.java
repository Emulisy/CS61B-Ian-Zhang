import java.util.Arrays;

public class MergeSortV2 {
    public static int[] mergeSort(int[] arr) {
        // Base case: If array is of length 1 or empty, it is already sorted
        if (arr.length <= 1) {
            return arr;
        }

        // Split the array into two halves
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        // Recursively sort both halves
        int[] sortedLeft = mergeSort(left);
        int[] sortedRight = mergeSort(right);

        // Merge the sorted halves and return the result
        return merge(sortedLeft, sortedRight);
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        // Merge both arrays
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                result[k++] = left[i++];
            } else if (left[i] > right[j]) {
                result[k++] = right[j++];
            } else {
                // If both elements are equal, add both
                result[k++] = left[i++];
                result[k++] = right[j++];
            }
        }

        // Copy any remaining elements from the left or right array
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }
}

