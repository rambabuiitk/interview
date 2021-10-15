import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShoppingOptions {

    public static long getNumberOfOptions(List<Integer> priceOfJeans, List<Integer> priceOfShoes,
            List<Integer> priceOfSkirts, List<Integer> priceOfTops, int dollars) {

        // Sort the lists to get rid of values greater than or equal to dollar amount
        Collections.sort(priceOfJeans);
        Collections.sort(priceOfShoes);
        Collections.sort(priceOfSkirts);
        Collections.sort(priceOfTops);

        // memory to store intermediate result
        //dp[0][i] number of ways to pick one jeans and one shoes with total price of i
        //dp[1][i] number of ways to pick one jeans, shoes and skirts with total price of i
        int[][] dp = new int[2][dollars +1];
        // total number of ways pick one jeans, shoes, skirts and tops with total price of i
        long noOfWays = 0;

        // jeans and shoes
        for (int i = 0;i < priceOfJeans.size(); i++) {
            for (int j = 0; j < priceOfShoes.size(); j++) {
                int sum = priceOfJeans.get(i) + priceOfShoes.get(j);
                if (sum >= dollars - 1) {
                    break;
                }
                dp[0][sum]++;
            }
        }

        // jeans shoes and skirts
        for (int i = 0;i < priceOfSkirts.size(); i++) {
            for (int j = 1; j < dollars; j++) {
                if (dp[0][j] == 0) continue;
                int sum = priceOfSkirts.get(i) + j;
                if (sum >= dollars) {
                    break;
                }
                dp[1][sum] += dp[0][j];
            }
        }

        // jeans, shoes, skirts and tops
        for (int i = 0;i < priceOfTops.size(); i++) {
            for (int j = 1; j < dollars; j++) {
                if (dp[1][j] == 0) continue;
                int sum = priceOfTops.get(i) + j;
                if (sum > dollars) {
                    break;
                }
                noOfWays += dp[1][j];
            }
        }

        return noOfWays;
    }

    public static void main(String[] args) {
        int[] jeans = {2, 3};
        int[] shoes = {4};
        int[] skirts = {2, 3};
        int[] tops = {1, 2};
        int dollars = 10;

        long res = getNumberOfOptions(toList(jeans),toList(shoes),toList(skirts),toList(tops),dollars);
        System.out.println("output" + res);


    }

    private static  List<Integer> toList(int[] intArr) {
        List<Integer> intList = new ArrayList<>(intArr.length);
        for (int i : intArr) {
            intList.add(i);
        }

        return intList;
    }


}
