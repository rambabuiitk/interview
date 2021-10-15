package numbersmath;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DotProductOfTwoSparseVector {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 0, 0, 2, 3};
        int[] arr2 = new int[]{0, 3, 0, 4, 0};
        System.out.println("Vector1: " + Arrays.toString(arr1));
        System.out.println("Vector2: " + Arrays.toString(arr2));
        SparseVector vec1 = new SparseVector(arr1);
        SparseVector vec2 = new SparseVector(arr2);
        int output = vec1.dotProduct(vec2);
        System.out.println("Dot Product: " + output);

    }

    private static class SparseVector {
        // only store the values > 0
        // Map<index, value> vector index and its value
        Map<Integer, Integer> map = new HashMap<>();

        // complexity of constructor
        // time: O(N) where N is elements in the vector
        // space: O(N) to store index,value in map
        private SparseVector(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                // only store if value is not 0
                if (nums[i] != 0) {
                    map.put(i, nums[i]);
                }
            }
        }

        // time: (O(min (M , N))
        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {

            // if either of the two vectors are empty return 0
            if (map.size() == 0 || vec.map.size() == 0) {
                return 0;
            }

            // use the smaller map inside dot product
            //so we can loop only smaller map
            // this reduces complexity to O(min (M , N))
            if (map.size() > vec.map.size()) {
                return vec.dotProduct(this);
            }

            int productSum = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int index = entry.getKey();
                // if similar index element is not there in other map
                if(vec.map.containsKey(index)) {
                    Integer vecValue = vec.map.get(index);
                    int product = entry.getValue() * vecValue;
                    productSum = productSum + product;
                }
            }
            return productSum;
        }
    }
}
