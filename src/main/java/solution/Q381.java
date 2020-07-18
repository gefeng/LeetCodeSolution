package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Insert Delete GetRandom O(1) - Duplicates allowed",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/"
)
public class Q381 {
    HashMap<Integer, Set<Integer>> map;
    List<Integer> nums;
    Random rand;

    /** Initialize your data structure here. */
    public Q381() {
        map = new HashMap<>();
        nums = new ArrayList<>();
        rand = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean exists = true;
        nums.add(val);
        if(!map.containsKey(val)) {
            map.put(val, new LinkedHashSet<>());
            exists = false;
        }
        map.get(val).add(nums.size() - 1);
        return !exists;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val))
            return false;

        Set<Integer> idxSet = map.get(val);
        int idx = idxSet.iterator().next();
        int lastIdx = nums.size() - 1;
        int lastNum = nums.get(lastIdx);

        nums.set(idx, lastNum);
        nums.remove(lastIdx);

        if(val != lastNum) {
            // reset index for last element
            map.get(lastNum).remove(lastIdx);
            map.get(lastNum).add(idx);
            idxSet.remove(idx);
        }
        else
            idxSet.remove(lastIdx);

        if(idxSet.size() == 0)
            map.remove(val);

        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}
