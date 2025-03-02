public class BinarySearch {
    public static int binarySearch(int[] sortedArray, int key, int low, int high)
    /*takes in a sorted array of int, a key we want to find,
    and the range the key lies(low to high)
    **/
    {
        if (low > high) {
            return(-1);
        }
        int mid = (low + high)/2;// auto return floor of low + high
        if (key == sortedArray[mid]) {
            return mid;
        }
        if (key < sortedArray[mid]) {
            return binarySearch(sortedArray, key, low, mid - 1);
        }
        if (key > sortedArray[mid]) {
            return binarySearch(sortedArray, key, mid + 1, high);
        }
        return binarySearch(sortedArray, key, low, mid - 1);
    }
}
