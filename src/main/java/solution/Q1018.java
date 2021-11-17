package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Binary Prefix Divisible By 5",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/binary-prefix-divisible-by-5/"
)
public class Q1018 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> ans = new ArrayList<>();

        int cur = 0;
        for(int x : nums) {
            cur = (cur << 1 | x) % 5;;
            ans.add(cur % 5 == 0);
        }

        return ans;
    }
}
