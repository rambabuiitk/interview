/*
Given an array of pairwise distinct positive integers arr=[1,9,7,8,2,6] populate the result array by returning minimum peaks .
a[i-1]<a[i]>a[i+1]
First min peak= 6 [1,9,7,8,2]
Second min peak=8 [1,9,7,2]
Third min peak=9 [1,7,2]
Fourth min peak=7 [1,2]
Fifth min peak= 2 [1]
Sixth min peak=1
*/

import java.util.Comparator;
import java.util.PriorityQueue;

// "static void main" must be defined in a public class.
public class MinPeaks {

    Node front ;
    Node rear ;
    PriorityQueue<Node> pq ;

    public MinPeaks(){
        front = new Node(Integer.MIN_VALUE);
        rear = new Node(Integer.MIN_VALUE);
        front.next = rear ;
        rear.prev = front ;
        pq = new PriorityQueue<Node>(new Comparator<Node>(){
            public int compare(Node n1 , Node n2){
                return n1.n - n2.n ;
            }
        });
    }

    public int[] getMinHeaps(int[] nums){
        if(nums == null || nums.length == 0)
            return nums ;

        int[] result = new int[nums.length];

        for(int i = 0 ; i < nums.length ; i++){
            Node node = new Node(nums[i]);
            Node prev = rear.prev ;
            prev.next = node ;
            node.prev = prev ;
            rear.prev = node ;
            node.next = rear ;
            if(i == 0 && nums[i+1] < nums[i]){
                pq.add(node);
            } else if(i == nums.length - 1 && nums[i] > nums[i-1]){
                pq.add(node);
            } else if((i > 0 && i < nums.length - 1) && nums[i] > nums[i-1] && nums[i] > nums[i+1]){
                pq.add(node);
            }
        }

        int j = 0 ;
        while(!pq.isEmpty()){
            Node minPeak = pq.poll();
            result[j] = minPeak.n ;
            j++;

            Node prev = minPeak.prev ;
            Node next = minPeak.next ;
            prev.next = next ;
            next.prev = prev ;

            if(isPeak(prev)){
                pq.add(prev);
            }
            if(isPeak(next)){
                pq.add(next);
            }
        }

        return result ;
    }

    public boolean isPeak(Node node){
        if(node == front)
            return false ;
        if(node == rear)
            return false ;
        Node prev = node.prev ;
        Node next = node.next ;
        if(node.n > prev.n && node.n > next.n)
            return true ;
        return false ;
    }

    public static void main(String[] args) {
        MinPeaks obj = new MinPeaks();
        int[] nums = {1,9,7,8,2,6};
        int[] minPeaks = obj.getMinHeaps(nums);
        for(int mp : minPeaks){
            System.out.print(mp + ",");
        }
    }
}

class Node {
    int n ;
    Node next ;
    Node prev ;
    public Node(int n){
        this.n = n ;
    }
}