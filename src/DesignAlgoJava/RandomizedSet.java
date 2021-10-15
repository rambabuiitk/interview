package DesignAlgoJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {
    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        System.out.println("obj.insert(1): " + obj.insert(1));
        System.out.println("obj.remove(2): " + obj.remove(2));
        System.out.println("obj.insert(2): " + obj.insert(2));
        System.out.println("obj.getRandom(): " + obj.getRandom());
        System.out.println("obj.remove(1): " + obj.remove(1));
        System.out.println("obj.insert(2): " + obj.insert(2));
        System.out.println("obj.getRandom(): " + obj.getRandom());
    }
    // store the Map<value, index of element in list>
    Map<Integer, Integer> map;
    // store all new elements at the end of list
    List<Integer> list;
    // to generate random number
    Random random;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        // if element already contains value return false as value was not inserted again
        if(map.containsKey(val)) {
            return false;
        }
        // add to map the val and as we are storing it at last index of list which is same as list.size
        map.put(val, list.size());
        // add the val to last of the list
        list.add(list.size(), val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        // if it is not in map return false
        if(!map.containsKey(val)) {
            return false;
        }
        // move the last element to the place index of the element to delete
        int lastElement = list.get(list.size() - 1);
        // location of val to be removed
        int index = map.get(val);
        // update the location of element to be removed with lastElement
        list.set(index, lastElement);
        // update the location in map
        map.put(lastElement, index);
        // remove the lastElement as we have already added in between
        list.remove(list.size() - 1);
        // remove from map the val
        map.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }

}
