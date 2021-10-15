import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniqueSubsets {


    public static List<List<Integer>> findUniqueSubsets(int[] arr, int sum) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        combinationUtil(arr, sum, 0, 0, tempList, result);
        return result;
    }

    private static void combinationUtil(int[] arr, int sum, int currSum, int start, List<Integer> tempList, List<List<Integer>> result) {

        if (currSum == sum) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        int prevElement = -1;
        for (int i = start;i<arr.length;i++) {
            if (prevElement != arr[i]) {
                if (currSum + arr[i] > sum) {
                    break;
                }
                tempList.add(arr[i]);
                combinationUtil(arr, sum, currSum + arr[i], i+1, tempList, result);
                tempList.remove(tempList.size() - 1);
                prevElement = arr[i];
            }
        }
    }

    public static void main(String[] args) {
        int [] arr = {6,2,7,8,2,4,1,3,7,5};
        int sum = 8;
        List<List<Integer>> result = findUniqueSubsets(arr, 8);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }
}
