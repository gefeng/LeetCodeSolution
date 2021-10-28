package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Longest Arithmetic Subsequence of Given Difference",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/"
)
public class Q1218 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int longestSubsequence(int[] arr, int difference) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for(int x : arr) {
            if(map.containsKey(x - difference)) {
                map.put(x, map.get(x - difference) + 1);
            } else {
                map.put(x, 1);
            }

            ans = Math.max(ans, map.get(x));
        }
        return ans;
    }
}
