package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Distribute Candies to People",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/distribute-candies-to-people/"
)
public class Q1103 {
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int given = 1;
        while(candies != 0) {
            ans[(given - 1) % num_people] += (candies - given >= 0 ? given : candies);
            candies = Math.max(0, candies - given);
            given++;
        }
        return ans;
    }
}
