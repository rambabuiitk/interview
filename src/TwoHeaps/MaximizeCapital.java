package TwoHeaps;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given a set of investment projects with their respective profits, we need to find the most profitable projects. We are given an
 * initial capital and are allowed to invest only in a fixed number of projects. Our goal is to choose projects that give us the
 * maximum profit. Write a function that returns the maximum total capital after selecting the most profitable projects
 *
 * We can start an investment project only when we have the required capital.
 * Once a project is selected, we can assume that its profit has become our capital.
 * Input:
 * capitals = [0, 1, 2]
 * profits = [1, 2, 3]
 * numOfProjects = 2
 * initialCapital = 1
 *
 * Output:
 *
 * Explanation:
 * With initial capital of ‘1’, we will start the second project which will give us profit of ‘2’. Once we selected our first project,
 * our total capital will become 3 (profit + initial capital).
 * With ‘3’ capital, we will select the third project, which will give us ‘3’ profit.
 * After the completion of the two projects, our total capital will be 6 (1+2+3).
 */

public class MaximizeCapital {
    public static void main(String[] args) {
        int[] capitals = new int[]{0, 1, 2};
        int[] profits = new int[]{1, 2, 3};
        int numOfProjects = 2;
        int initialCapital = 1;
        System.out.println("capitals: " + Arrays.toString(capitals));
        System.out.println("profits: " + Arrays.toString(profits));
        System.out.println("numOfProjects: " + numOfProjects);
        System.out.println("initialCapital: " + initialCapital);
        int result = findMaximumCapital(capitals, profits, numOfProjects, initialCapital);
        System.out.println("Output: " + result);
    }

    private static int findMaximumCapital(final int[] capital,
                                          final int[] profits,
                                          final int numOfProjects,
                                          final int initialCapital) {

        int n = profits.length;
        // here we are going to store the index of capital array as minheap
        PriorityQueue<Integer> minCapitalHeap = new PriorityQueue<>(n, (i1, i2) -> capital[i1] - capital[i2]);
        // here we are going to store the index of profit array as maxheap
        PriorityQueue<Integer> maxProfitHeap = new PriorityQueue<>(n, (i1, i2) -> profits[i2] - profits[i1]);

        // insert all project capitals to a min-heap
        for (int i = 0; i < n; i++) {
            minCapitalHeap.add(i);
        }

        // let's try to find a total of 'numberOfProjects' best projects
        int availableCapital = initialCapital;

        for (int i = 0; i < numOfProjects; i++) {
            // find all projects that can be selected within the available capital and insert them in a max-heap
            while (!minCapitalHeap.isEmpty() && capital[minCapitalHeap.peek()] <= availableCapital) {
                maxProfitHeap.add(minCapitalHeap.poll());
            }
            // terminated if we are not able to find an project that can be completed within availableCapital
            if (maxProfitHeap.isEmpty()) {
                break;
            }

            // select the project with the maximum profit
            availableCapital = availableCapital + profits[maxProfitHeap.poll()];
        }
        return availableCapital;
    }

}

/**
 * Time Complexity: O(N logN + K logN) where N is total number of projects and K is total projects we select.
 * Space Complexity: O(N)
 */
