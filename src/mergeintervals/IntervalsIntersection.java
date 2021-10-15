package mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalsIntersection {

    public static void main(String[] args) {
        Interval[] input1 = new Interval[]{
                new Interval(0, 2),
                new Interval(5, 10),
                new Interval(13, 23),
                new Interval(24, 25)
        };
        Interval[] input2 = new Interval[]{
                new Interval(1, 5),
                new Interval(8, 12),
                new Interval(15, 24),
                new Interval(25, 26),
        };
        System.out.println("arr1: " + Arrays.toString(input1));
        System.out.println("arr2: " + Arrays.toString(input2));
        Interval[] output = intersect(input1, input2);
        System.out.println("Output: " + Arrays.toString(output));
    }

    private static Interval[] intersect(final Interval[] arr1, final Interval[] arr2) {
        int i = 0;
        int j = 0;
        List<Interval> result = new ArrayList<Interval>();
        while (i < arr1.length && j < arr2.length) {
            // check if the interval arr1[i] intersects with arr2[j]
            // check if one of the interval's start time lies within the other interval
            // 1. arr2.start <= arr1.start <= arr2.end
            // 2. arr1.start <= arr2.start <= arr1.end
            if ((arr1[i].start >= arr2[j].start && arr1[i].start <= arr2[j].end)
                    || (arr2[j].start >= arr1[i].start && arr2[j].start <= arr1[i].end)) {
                result.add(new Interval(
                        Math.max(arr1[i].start, arr2[j].start), Math.min(arr1[i].end, arr2[j].end)));
            }
            // move next from the interval which is finishing first
            if (arr1[i].end < arr2[j].end) {
                i++;
            } else {
                j++;
            }
        }
        return result.toArray(new Interval[result.size()]);
    }

    private static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return "[" + this.start + "," + this.end + "]";
        }
    }
}