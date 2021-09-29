package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Kids With the Greatest Number of Candies",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/"
)
public class Q1431 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int n = candies.length;
        List<Boolean> ans = new ArrayList<>();
        int max = 0;

        for(int c : candies) {
            max = Math.max(max, c);
        }

        for(int i = 0; i < n; i++) {
            if(candies[i] + extraCandies >= max) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }

        return ans;
    }
}
