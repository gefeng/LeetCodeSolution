package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Distribute Repeating Integers",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/distribute-repeating-integers/"
)
public class Q1655 {
    /**
     * Time:  O(2^m * n * 2^m)
     * Space: O(2^m * n)
     * */
    public boolean canDistribute(int[] nums, int[] quantity) {
        int m = quantity.length;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int[] cnt = new int[freqMap.size()];
        int i = 0;
        for(int key : freqMap.keySet()) {
            cnt[i++] = freqMap.get(key);
        }
        return dfs(cnt, quantity, 0, (1 << m) - 1, new Boolean[1 << m][cnt.length]);
    }

    // init: 1 1 1 1 -> 0 0 0 0
    private boolean dfs(int[] cnt, int[] quantity, int idx, int state, Boolean[][] memo) {
        int m = quantity.length;
        int n = cnt.length;

        if(state == 0) {
            return true;
        }

        if(idx == cnt.length) {
            return false;
        }

        if(memo[state][idx] != null) {
            return memo[state][idx];
        }

        for(int mask = state; ; mask = (mask - 1) & state) {
            if(canDistribute(mask, state, cnt[idx], quantity)) {
                if(dfs(cnt, quantity, idx + 1, mask, memo)) {
                    return true;
                }
            }

            if(mask == 0) {
                break;
            }
        }

        return memo[state][idx] = false;
    }

    // oMask 1 0 1 1
    // nMask 0 0 0 1
    private boolean canDistribute(int nMask, int oMask, int total, int[] quantity) {
        for(int i = 0; i < quantity.length; i++) {
            if((nMask & (1 << i)) == 0 && (oMask & (1 << i)) != 0) {
                total -= quantity[i];
                if(total < 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
