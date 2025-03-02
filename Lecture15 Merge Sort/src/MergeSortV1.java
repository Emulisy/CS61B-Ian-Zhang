import java.util.ArrayList;
import java.util.Arrays;

public class MergeSortV1 {
    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        else {
            int mid = arr.length / 2;//floor of length
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);
            int[] sortedLeft = mergeSort(left);
            int[] sortedRight = mergeSort(right);
            return merging(sortedLeft, sortedRight);
        }
    }

    public static int[] merging(int[] left, int[] right) {
        ArrayList<Integer> leftArray = new ArrayList<>();
        ArrayList<Integer> rightArray = new ArrayList<>();
        for (int j : left) {
            leftArray.add(j);
        }
        for (int j : right) {
            rightArray.add(j);
        }
        ArrayList<Integer> resultArray = new ArrayList<>();
        while(true) {
            if(!leftArray.isEmpty() && !rightArray.isEmpty()) {
                if (leftArray.get(0) < rightArray.get(0)) {
                    resultArray.add(leftArray.remove(0));
                } else if (rightArray.get(0) < leftArray.get(0)) {
                    resultArray.add(rightArray.remove(0));
                }else if (leftArray.get(0) == rightArray.get(0)) {
                    resultArray.add(leftArray.remove(0));
                    resultArray.add(rightArray.remove(0));
                }
            }
            else{
                if(leftArray.isEmpty()) {
                    resultArray.addAll(rightArray);
                }
                else {
                    resultArray.addAll(leftArray);
                }
                break;
            }
        }
        return resultArray.stream().mapToInt(Integer::intValue).toArray();
    }
}
