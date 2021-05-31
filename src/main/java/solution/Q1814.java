package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Count Nice Pairs in an Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/count-nice-pairs-in-an-array/"
)
public class Q1814 {
    private static final int MODULO = (int)1e9 + 7;
    public int countNicePairs(int[] nums) {
        Map<Integer, Integer> diffCnt = new HashMap<>();
        int cnt = 0;
        for(int num : nums) {
            int rnum = rev(num);
            int diff = num - rnum;
            cnt = (cnt + diffCnt.getOrDefault(diff, 0)) % MODULO;
            diffCnt.put(diff, diffCnt.getOrDefault(diff, 0) + 1);
        }

        return cnt;
    }

    private int rev(int x) {
        int rx = 0;
        while(x != 0) {
            rx = rx * 10 + x % 10;
            x /= 10;
        }
        return rx;
    }
}
