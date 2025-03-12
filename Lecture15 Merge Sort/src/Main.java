import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 3, 7, 2, 5, 9, 111, 23, 75, 34, 15, 99};
        int[] result1 = MergeSortV1.mergeSort(arr);
        int[] result2 = MergeSortV2.mergeSort(arr);
        int[] result3 = PopSort.PopSort(arr);
        System.out.println(Arrays.toString(result1) + "\n" + Arrays.toString(result2) + "\n" + Arrays.toString(result3));
    }
}