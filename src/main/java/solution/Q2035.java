package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Partition Array Into Two Arrays to Minimize Sum Difference",
        difficulty = QDifficulty.HARD,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/"
)
public class Q2035 {
    /**
     * Time:  O((N + log(2 ^ N)) * 2 ^ N)
     * Space: O(2 ^ N)
     * */
    public int minimumDifference(int[] nums) {
        int ans = Integer.MAX_VALUE;
        int n = nums.length / 2;
        int sum = 0;
        List<List<Integer>> sumMap = new ArrayList<>();

        for(int x : nums) {
            sum += x;
        }

        for(int i = 0; i <= n; i++) {
            sumMap.add(new ArrayList<>());
        }

        for(int i = 0; i < (1 << n); i++) {
            int cnt = Integer.bitCount(i);
            int tot = 0;
            for(int j = 0; j < n; j++) {
                if((i & (1 << j)) != 0) {
                    tot += nums[j];
                }
            }

            sumMap.get(cnt).add(tot);
        }

        for(List<Integer> l : sumMap) {
            Collections.sort(l);
        }

        for(int i = 0; i < (1 << n); i++) {
            int cnt = Integer.bitCount(i);
            int tot = 0;
            for(int j = 0; j < n; j++) {
                if((i & (1 << j)) != 0) {
                    tot += nums[n + j];
                }
            }

            List<Integer> l = sumMap.get(n - cnt);

            int idx = Collections.binarySearch(l, sum / 2 - tot);
            if(idx >= 0) {
                ans = Math.min(ans, Math.abs(sum - 2 * (l.get(idx) + tot)));
            } else {
                idx = -idx - 1;
                if(idx < l.size()) {
                    ans = Math.min(ans, Math.abs(sum - 2 * (l.get(idx) + tot)));
                }
                if(idx > 0) {
                    ans = Math.min(ans, Math.abs(sum - 2 * (l.get(idx - 1) + tot)));
                }
            }
        }

        return ans;
    }
}
