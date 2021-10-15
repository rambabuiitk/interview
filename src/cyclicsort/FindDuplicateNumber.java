import java.util.Arrays;

public class FindDuplicateNumber {

    public static void main(String[] args) {
        int[] input = new int[]{1, 4, 4, 3, 2};
        System.out.println("USing Cyclic Sort Pattern");
        System.out.println("Input: " + Arrays.toString(input));
        int duplicateNumber = findDuplicate(input);
        System.out.println("Output: " + duplicateNumber);

        System.out.println("USing Two Pointer Pattern");
        input = new int[]{1, 4, 4, 3, 2};
        System.out.println("Input: " + Arrays.toString(input));
        duplicateNumber = findNumber(input);
        System.out.println("Output: " + duplicateNumber);
    }

    // find using cyclicsort method or swap method
    public static int findDuplicate(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (i != nums[i] - 1) {
                if (nums[i] != nums[nums[i] - 1]) { // check the element which is ath that particular index.
                    swap(nums, i, nums[i] - 1);
                } else { // found the duplicate
                    return nums[i];
                }
            } else {
                i++;
            }
        }
        return -1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // find using fastandslow pointer method
    private static int findNumber(final int[] arr) {
        int slow = 0;
        int fast = 0;
        // two pointers to find if arrays has cycle or element is repeated again.
        // fast moves at arr[arr[i]] slow just moves to arr[i]
        do {
            slow = arr[slow];
            fast = arr[arr[fast]];
        } while (slow != fast);

        // at this point slow == fast means we found a duplicate
        // find cycle length
        int current = arr[slow];
        int cycleLength = 0;
        do {
            current = arr[current];
            cycleLength++;
        } while (current != arr[slow]);


        return findStart(arr, cycleLength);
    }

    private static int findStart(int[] arr, int cycleLength) {
        // initialize both slow and fast pointer
        int slow = arr[0];
        int fast = arr[0];
        // move fast pointer ahead by cycleLength
        while (cycleLength > 0) {
            fast = arr[fast];
            cycleLength--;
        }

        while (slow != fast) {
            slow = arr[slow];
            fast = arr[fast];
        }
        return slow;
    }

}
