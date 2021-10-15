package karat;

import java.util.ArrayList;
import java.util.List;

public class Peaks {

    public static List<Integer> findPeaks(int[] arr) {
        List<Integer> peaks = new ArrayList<>();
        boolean rising = true;
        for(int i=1; i< arr.length; i++){
            if(arr[i] > arr[i-1]){
                rising = true;
            } else if(arr[i] < arr[i-1] && rising == true){
                peaks.add(arr[i-1]);
                rising = false;
            }
        }
        if(rising == true)
            peaks.add(arr[arr.length-1]);

        return peaks;
    }

    public static void main(String[] args) {
        int[] arr = //{6,8,8,8,4};
                //{6, 8, 8, 10, 4};
                 {2,4,3,3,3,1,5,8};
                 //{3,3,2,1,9};
                //{3,3,3,3,5,4};
                //{ 1, 4, 3, -1, 5, 5, 7, 4, 8 };

        System.out.println(findPeaks(arr));
    }
}
