package DesignAlgoJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomizedCollection {
    public static void main(String[] args) {
        RandomizedCollection obj = new RandomizedCollection();
        System.out.println("obj.insert(1): " + obj.insert(1));
        System.out.println("obj.insert(1): " + obj.insert(1));
        System.out.println("obj.insert(2): " + obj.insert(2));
        // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
        System.out.println("obj.getRandom(): " + obj.getRandom());
        System.out.println("obj.remove(1): " + obj.remove(1));
        // getRandom should return 1 and 2 both equally likely.
        System.out.println("obj.getRandom(): " + obj.getRandom());
    }

    // map <values, set of indexes> for set we will use LinkedHashSet
    Map<Integer, LinkedHashSet<Integer>> map;
    // store all elements at the end of list
    List<Integer> list;
    // to generate random number
    Random random;

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    /** Inserts a value to the collection.
     *  Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        // if element is not here in map
        if(!map.containsKey(val)) {
            map.put(val, new LinkedHashSet<>());
        }
        map.get(val).add(list.size()); // map add the current index from list to the set.
        // add the val to last of the list
        list.add(list.size(), val);
        return map.get(val).size() == 1; // validate if map does not contain element return false
    }

    /** Removes a value from the collection.
     * Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        // if it is not in map return false
        if(!map.containsKey(val)) {
            return false;
        }
        // Get arbitary index of the ArrayList that contains val
        // here we are just getting the first value
        int indexToReplace = map.get(val).iterator().next();

        // Obtain the set of the number in the last place of the ArrayList
        int numAtLastPlace = list.get(list.size() - 1);
        LinkedHashSet<Integer> replaceWith = map.get(numAtLastPlace);

        // Replace val at arbitary index with very last number
        list.set(indexToReplace, numAtLastPlace);

        // Remove appropriate index
        map.get(val).remove(indexToReplace);

        // Don't change set if we were replacing the removed item with the same number
        if(indexToReplace != list.size() - 1) {
            replaceWith.remove(list.size() - 1);
            replaceWith.add(indexToReplace);
        }
        list.remove(list.size() - 1);

        // Remove map entry if set is now empty, then return
        if(map.get(val).isEmpty()) {
            map.remove(val);
        }
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }

}
