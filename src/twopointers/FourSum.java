import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public static void main(String[] args) {
        int[] input = new int[]{1, 0, -1, 0, -2, 2};
        int target = 0;
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("target: " + target);
        List<List<Integer>> output = fourSum(input, target);
        System.out.println("Output: " + output);
    }

    // time: O(N ^ (k-1)) == O(N^3)
    private static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    private static List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (start == nums.length || nums[start] * k > target
                || target > nums[nums.length - 1] * k)
            return res;

        if (k == 2) {
            return twoSum(nums, target, start);
        }

        for (int i = start; i < nums.length; i++)
            if (i == start || nums[i - 1] != nums[i])
                for (List<Integer> set : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(set);
                }
        return res;
    }

    private static List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target || (left > start && nums[left] == nums[left - 1])) {
                ++left;
            } else if (sum > target || (right < nums.length - 1 && nums[right] == nums[right + 1])) {
                --right;
            } else {
                res.add(Arrays.asList(nums[left++], nums[right--]));
            }
        }
        return res;
    }
}
