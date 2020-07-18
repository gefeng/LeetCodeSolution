package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;

@Problem(
        title = "4Sum II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/4sum-ii/"
)
public class Q454 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for(int a : A) {
            for(int b : B) {
                map.put(a + b, map.getOrDefault(a + b, 0) + 1);
            }
        }

        for(int c : C) {
            for(int d : D) {
                count += map.getOrDefault(0 - c - d, 0);
            }
        }

        return count;
    }

    /*follow up will be design a generic approach to return kSum
    * The idea is to separate given lists to half half.
    * Then build hash map using lists from 0 - i / 2 and count using
    * list from i / 2 + 1 - i*/
    public int kSum(int[][] lists) {
        HashMap<Integer, Integer> map = new HashMap<>();
        addToHash(lists, map, 0, 0);
        int count = countSum(lists, map, lists.length / 2, 0);
        return count;
    }

    private void addToHash(int[][] lists, HashMap<Integer, Integer> map, int i, int sum) {
        if(i == lists.length / 2)
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        else {
            for(int n : lists[i])
                addToHash(lists, map, i + 1, sum + n);
        }
    }

    private int countSum(int[][] lists, HashMap<Integer, Integer> map, int i, int target) {
        if(i == lists.length)
            return map.getOrDefault(target, 0);
        int count = 0;
        for(int n : lists[i]) {
            count += countSum(lists, map, i + 1, target - n);
        }
        return count;
    }
}
