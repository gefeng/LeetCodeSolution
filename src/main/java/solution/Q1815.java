package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Maximum Number of Groups Getting Fresh Donuts",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-number-of-groups-getting-fresh-donuts/"
)
public class Q1815 {
    public int maxHappyGroups(int batchSize, int[] groups) {
        int cntRem[] = new int[batchSize];
        int cntHappy = 0;

        for(int i = 0; i < groups.length; i++) {
            int rem = groups[i] % batchSize;
            if(rem == 0) {
                cntHappy++;
            } else {
                if(cntRem[batchSize - rem] > 0) {
                    cntRem[batchSize - rem]--;
                    cntHappy++;
                } else {
                    cntRem[rem]++;
                }
            }
        }
        return cntHappy + dfs(cntRem, 0, new HashMap<>());
    }

    private int dfs(int[] cntRem, int left, Map<String, Integer> memo) {
        int total = cntRem.length;

        String key = Arrays.toString(cntRem);
        if(memo.containsKey(key)) {
            return memo.get(key);
        }

        int sum = 0;
        for(int i = 0; i < total; i++) {
            sum += cntRem[i];
        }
        if(sum == 0) {
            return 0;
        }

        int maxCnt = 0;
        for(int i = 1; i < total; i++) {
            if(cntRem[i] == 0) {
                continue;
            }

            cntRem[i]--;
            if(left == 0) {
                maxCnt = Math.max(maxCnt, dfs(cntRem, (total + left - i) % total, memo) + 1);
            } else {
                maxCnt = Math.max(maxCnt, dfs(cntRem, (total + left - i) % total, memo));
            }
            cntRem[i]++;
        }

        memo.put(key, maxCnt);
        return maxCnt;
    }
}
