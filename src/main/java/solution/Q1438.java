package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

@Problem(
        title = "Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/"
)
public class Q1438 {
    public int longestSubarray(int[] nums, int limit) {
        return monoDequeSol(nums, limit);
    }

    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    private int treeMapSol(int[] nums, int limit) {
        int n = nums.length;
        int ans = 0;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int l = 0, r = 0; r < n; r++) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);

            while(map.lastKey() - map.firstKey() > limit) {
                int cnt = map.get(nums[l]);
                if(cnt == 1) {
                    map.remove(nums[l]);
                } else {
                    map.put(nums[l], cnt - 1);
                }

                l++;
            }

            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }

    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private int monoDequeSol(int[] nums, int limit) {
        int n = nums.length;
        int ans = 0;
        Deque<Integer> maxd = new ArrayDeque<>();
        Deque<Integer> mind = new ArrayDeque<>();

        for(int l = 0, r = 0; r < n; r++) {
            while(!maxd.isEmpty() && maxd.peekLast() < nums[r]) {
                maxd.pollLast();
            }
            while(!mind.isEmpty() && mind.peekLast() > nums[r]) {
                mind.pollLast();
            }

            maxd.offerLast(nums[r]);
            mind.offerLast(nums[r]);

            while(maxd.peekFirst() - mind.peekFirst() > limit) {
                if(maxd.peekFirst() == nums[l]) {
                    maxd.pollFirst();
                }
                if(mind.peekFirst() == nums[l]) {
                    mind.pollFirst();
                }
                l++;
            }

            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }
}
