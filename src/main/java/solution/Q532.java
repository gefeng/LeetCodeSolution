package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "K-diff Pairs in an Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/k-diff-pairs-in-an-array/"
)
public class Q532 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int findPairs(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        for(Map.Entry<Integer, Integer> e : freqMap.entrySet()) {
            if(k == 0) {
                res = e.getValue() > 1 ? res + 1 : res;
            } else {
                res = freqMap.containsKey(e.getKey() - k) ? res + 1 : res;
            }
        }

        return res;
    }
}
