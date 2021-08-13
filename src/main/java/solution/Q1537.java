package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Get the Maximum Score",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/get-the-maximum-score/"
)
public class Q1537 {
    private static final int MOD = (int)1e9 + 7;
    public int maxSum(int[] nums1, int[] nums2) {
        return twoPointersSol(nums1, nums2);
    }

    /**
     * Time:  O(M * N)
     * Space: O(M + N)
     * */
    private int topDownDpSol(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        for(int i = 0; i < m; i++) {
            map1.put(nums1[i], i);
        }
        for(int i = 0; i < n; i++) {
            map2.put(nums2[i], i);
        }

        long score1 = dfs(nums1, nums2, map1, map2, 0, 0, new Long[Math.max(m, n)][2]);
        long score2 = dfs(nums1, nums2, map1, map2, 0, 1, new Long[Math.max(m, n)][2]);

        return (int)(Math.max(score1, score2) % MOD);
    }

    private long dfs(int[] nums1, int[] nums2, Map<Integer, Integer> map1, Map<Integer, Integer> map2,
                     int i, int curArr, Long[][] memo) {
        int m = nums1.length;
        int n = nums2.length;
        if((curArr == 0 && i == m) || (curArr == 1 && i == n)) {
            return 0;
        }

        if(memo[i][curArr] != null) {
            return memo[i][curArr];
        }

        long score = (long)dfs(nums1, nums2, map1, map2, i + 1, curArr, memo) + (curArr == 0 ? nums1[i] : nums2[i]);

        if(curArr == 0 && map2.containsKey(nums1[i])) {
            score = Math.max(score, (long)dfs(nums1, nums2, map1, map2, map2.get(nums1[i]) + 1, 1, memo) + nums1[i]);
        } else if(curArr == 1 && map1.containsKey(nums2[i])) {
            score = Math.max(score, (long)dfs(nums1, nums2, map1, map2, map1.get(nums2[i]) + 1, 0, memo) + nums2[i]);
        }

        return memo[i][curArr] = score;
    }

    /**
     * calculate two path sum by,
     * 1. if nums1[i] < nums2[j] then path1 += nums1[i++]
     * 2. if nums1[i] > nums2[j] then path2 += nums2[j++]
     * 3. if nums1[i] == nums2[j] then path1 = path2 = Math.max(path1, path2) + nums1[i++]/nums2[j++]
     *
     * Time:  O(M + N)
     * Space: O(1)
     * */
    private int twoPointersSol(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        long path1 = 0;
        long path2 = 0;
        int i = 0;
        int j = 0;

        while(i < m || j < n) {
            if(i > m - 1 || (j < n && nums1[i] > nums2[j])) {
                path2 += nums2[j++];
            } else if(j > n - 1 || (i < m && nums1[i] < nums2[j])) {
                path1 += nums1[i++];
            } else {
                long max = Math.max(path1, path2);
                path1 = max + nums1[i++];
                path2 = max + nums2[j++];
            }
        }

        return (int)(Math.max(path1, path2) % MOD);
    }
}
