package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Problem(
        title = "Longest Consecutive Sequence",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/longest-consecutive-sequence/"
)
public class Q128 {
    public int longestConsecutive(int[] nums) {
        return optimizedHashSetSolution(nums);
    }

    private int unionFindSolution(int[] nums) {
        int n = nums.length;
        int[] parent = new int[n];
        Map<Integer, Integer> idxMap = new HashMap<>();

        for(int i = 0; i < n; i++) {
            idxMap.put(nums[i], i);
        }

        for(int i = 0; i < n; i++) {
            if(idxMap.containsKey(nums[i] - 1)) {
                parent[i] = idxMap.get(nums[i] - 1);
            } else {
                parent[i] = i;
            }
        }

        int max = 0;
        for(int i = 0; i < n; i++) {
            int head = find(parent, i);
            max = Math.max(max, nums[i] - nums[head] + 1);
        }

        return max;
    }

    private int find(int[] parent, int i) {
        if(parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    private int optimizedHashSetSolution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            set.add(n);
        }

        int max = 0;
        for(int num : set) {
            if(!set.contains(num - 1)) {    // new start
                int len = 1;
                while(set.contains(num + 1)) {
                    num++;
                    len++;
                }
                max = Math.max(max, len);
            }
        }

        return max;
    }
}
