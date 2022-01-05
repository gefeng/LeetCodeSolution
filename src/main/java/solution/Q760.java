package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

@Problem(
        title = "Find Anagram Mappings",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/find-anagram-mappings/"
)
public class Q760 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] ans = new int[n];
        Map<Integer, Queue<Integer>> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            map.computeIfAbsent(nums2[i], k -> new ArrayDeque<>()).offer(i);
        }

        for(int i = 0; i < n; i++) {
            ans[i] = map.get(nums1[i]).poll();
        }

        return ans;
    }
}
