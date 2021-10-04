package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Number of Pairs of Strings With Concatenation Equal to Target",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target/"
)
public class Q2023 {
    /**
     * Time:  O(N * len(target))
     * Space: O(N)
     * */
    public int numOfPairs(String[] nums, String target) {
        int ans = 0;

        Map<String, Integer> cntMap = new HashMap<>();
        for(String s : nums) {
            cntMap.put(s, cntMap.getOrDefault(s, 0) + 1);
        }

        for(String prefix : nums) {
            if(target.startsWith(prefix)) {
                String suffix = target.substring(prefix.length(), target.length());
                int cnt = cntMap.getOrDefault(suffix, 0);

                ans += prefix.equals(suffix) ? cnt - 1 : cnt;
            }
        }

        return ans;
    }
}
