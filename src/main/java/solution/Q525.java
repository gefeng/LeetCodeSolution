package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Contiguous Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/contiguous-array/"
)
public class Q525 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int res = 0;
        int cnt = 0;
        Map<Integer, Integer> cntMap = new HashMap<>();
        cntMap.put(0, -1);

        for(int i = 0; i < n; i++) {
            cnt = nums[i] == 0 ? cnt - 1 : cnt + 1;

            Integer idx = cntMap.get(cnt);
            if(idx != null) {
                res = Math.max(res, i - idx);
            } else {
                cntMap.put(cnt, i);
            }
        }

        return res;
    }
}
