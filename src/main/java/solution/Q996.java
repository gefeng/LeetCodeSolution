package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Number of Squareful Arrays",
        difficulty = QDifficulty.HARD,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/number-of-squareful-arrays/"
)
public class Q996 {
    /**
     * Time:  O(N!)
     * Space: O(N + #perfect square number)
     * */
    int ans = 0;
    Set<Integer> perSqu = new HashSet<>();
    public int numSquarefulPerms(int[] nums) {
        if(nums.length == 1) {
            return 0;
        }


        for(long i = 0; i * i <= (long)2e9; i++) {
            perSqu.add((int)(i * i));
        }

        dfs(nums, 0, -1, 0);

        return ans;
    }

    private void dfs(int[] nums, int cur, int pre, int mask) {
        Set<Integer> seen = new HashSet<>();
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            if((mask & (1 << i)) == 0 && !seen.contains(nums[i])) {
                if(pre == -1) {
                    seen.add(nums[i]);
                    dfs(nums, cur + 1, nums[i], mask | (1 << i));
                } else {
                    if(perSqu.contains(pre + nums[i])) {
                        seen.add(nums[i]);
                        if(cur == n - 1) {
                            ans++;
                        } else {
                            dfs(nums, cur + 1, nums[i], mask | (1 << i));
                        }
                    }
                }
            }
        }
    }
}
