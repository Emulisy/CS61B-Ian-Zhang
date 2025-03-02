public class PopSort {
    public static int[] PopSort(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return arr;
        }
        for (int i = 0; i < n-1; i++) {
            for(int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
