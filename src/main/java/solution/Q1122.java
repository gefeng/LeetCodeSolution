package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.TreeMap;

@Problem(
        title = "Relative Sort Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/relative-sort-array/"
)
public class Q1122 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        return countingSortSolution(arr1, arr2);
    }

    /*
    * good approach to sort a sequence if all the values are within a specific range
    * i.e. 0 <= arr1[i], arr2[i] <= 1000
    * Time: O(1)
    * Space: O(1)
    * */
    private int[] countingSortSolution(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        for(int x : arr1)
            count[x]++;

        int i = 0;
        for(int x : arr2) {
            while(count[x] != 0) {
                arr1[i++] = x;
                count[x]--;
            }
        }

        for(int j = 0; j < count.length; j++) {
            if(count[j] != 0)
                arr1[i++] = j;
        }

        return arr1;
    }

    /*
    * approach if no constraints
    * bunch of ways to do
    * Time: O(nlogn)
    * Space: O(n)
    * */
    private int[] sortingSolution(int[] arr1, int[] arr2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int x : arr1)
            map.put(x, map.getOrDefault(x, 0) + 1);

        int i = 0;
        for(int x : arr2) {
            int count = map.get(x);
            while(count != 0)
                arr1[i++] = x;
            map.remove(x);
        }

        for(int key : map.keySet()) {
            int count = map.get(key);
            while(count != 0)
                arr1[i++] = key;
        }

        return arr1;
    }
}
