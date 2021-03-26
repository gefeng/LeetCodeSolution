package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Frog Jump",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/frog-jump/"
)
public class Q403 {
    public boolean canCross(int[] stones) {
        if(stones[1] - stones[0] > 1)
            return false;
        Boolean[][] memo = new Boolean[stones.length][stones.length];
        return dfs(stones, 1, 0, memo);
    }

    private boolean dfs(int[] stones, int idx, int prevIdx, Boolean[][] memo) {
        if(idx == stones.length - 1)
            return true;
        if(idx > stones.length - 1)
            return false;

        if(memo[prevIdx][idx] != null)
            return memo[prevIdx][idx];

        int prevJump = stones[idx] - stones[prevIdx];
        boolean res = false;
        for(int i = prevJump - 1; i <= prevJump + 1; i++) {
            int nextIdx = getNextPosition(stones, idx, i);
            if(nextIdx != -1) {
                if(dfs(stones, nextIdx, idx, memo)) {
                    res = true;
                    break;
                }
            }
        }

        memo[prevIdx][idx] = res;
        return res;
    }

    private int getNextPosition(int[] stones, int idx, int jump) {
        int target = stones[idx] + jump;
        int lo = idx + 1;
        int hi = stones.length - 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(stones[mid] == target)
                return mid;
            if(stones[mid] < target)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return -1;
    }
}
