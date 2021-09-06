package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "The Number of Good Subsets",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/the-number-of-good-subsets/"
)
public class Q1994 {
    /**
     * Time:  O(N) (O(N * 2 ^ 18 * 18))
     * Space: O(1) (O(2 ^ 18 * 18))
     * */
    private static final int MOD = (int)1e9 + 7;
    private static final int[] p = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    private static final int[] pd = new int[] {6, 10, 14, 15, 21, 22, 26, 30};
    public int numberOfGoodSubsets(int[] nums) {
        int n = nums.length;
        int[] cnt = new int[31];

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            cnt[nums[i]]++;
            if(isValid(nums[i])) {
                set.add(nums[i]);
            }
        }

        int[] unique = new int[set.size()];
        int idx = 0;
        for(int num : set) {
            unique[idx++] = num;
        }

        int sz = unique.length;
        int res = dfs(unique, 0, 0, cnt, new Integer[sz][1 << sz]);

        for(int i = 0; i < cnt[1]; i++) {
            res = (int)((long)res * 2 % MOD);
        }

        return res;
    }

    private boolean isValid(int num) {
        boolean exist1 = false;
        for(int i = 0; i < p.length; i++) {
            if(num == p[i]) {
                exist1 = true;
                break;
            }
        }

        boolean exist2 = false;
        for(int i = 0; i < pd.length; i++) {
            if(num == pd[i]) {
                exist2 = true;
                break;
            }
        }

        return exist1 || exist2;
    }

    private boolean hasCommonDivisor(int x, int y) {
        for(int prime : p) {
            if(x % prime == 0 && y % prime == 0) {
                return true;
            }
        }
        return false;
    }

    private int dfs(int[] nums, int cur, int mask, int[] cnt, Integer[][] memo) {
        int n = nums.length;

        if(cur == n) {
            return mask == 0 ? 0 : 1;
        }

        if(memo[cur][mask] != null) {
            return memo[cur][mask];
        }

        long ret = dfs(nums, cur + 1, mask, cnt, memo);

        boolean canPick = true;
        for(int i = 0; i < n; i++) {
            if((mask & (1 << i)) != 0 && hasCommonDivisor(nums[i], nums[cur])) {
                canPick = false;
                break;
            }
        }

        if(canPick) {
            ret = (ret + (long)dfs(nums, cur + 1, mask | (1 << cur), cnt, memo) * cnt[nums[cur]] % MOD) % MOD;
        }

        return memo[cur][mask] = (int)ret;
    }
}
