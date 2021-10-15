import java.util.Arrays;

public class TrappingRainWater {
    public static void main(String[] args) {
        int[] input = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Input: " + Arrays.toString(input));
        int output = computeTrapWater(input);
        System.out.println("Output: " + output);
    }
    
    // time complexity O(N)
    private static int computeTrapWater(int[] height) {
        int left = 0; // start of array
        int right = height.length - 1; // last index of the array
        int totalWater = 0;
        int leftMax = 0;
        int rightMax = 0;
        while(left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if(leftMax < rightMax) { // leftMax is smaller than rightMax,
                // so the (leftMax-height[left]) water can be stored on the left side of current left bar
                totalWater = totalWater + (leftMax - height[left]); // we store this water and move forward
                left++;
            } else { // rightMax is smaller than leftMax meaning
                // so the (rightMax-height[right]) water can be stored on the right side of current right bar
                totalWater = totalWater + (rightMax - height[right]);
                right--;
            }
        }
        return totalWater;
    }
}
