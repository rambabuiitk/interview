import java.util.Arrays;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] input = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Input: " + Arrays.toString(input));
        int output = maxArea(input);
        System.out.println("Output: " + output);
    }

    private static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            // area = height * length
            // Here we get minimum length betwen left and right
            // and multiply it with distance between them to get the currentArea
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, currentArea);
            // if height of left is smaller move to next element of left
            if (height[left] < height[right]) {
                left++;
            } else { // if the height of right is smaller move to next element of right
                right--;
            }
        }
        return maxArea;
    }
}