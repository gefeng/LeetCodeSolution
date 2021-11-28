package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Number of People That Can Be Caught in Tag",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/maximum-number-of-people-that-can-be-caught-in-tag/"
)
public class Q1989 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int catchMaximumAmountofPeople(int[] team, int dist) {
        int n = team.length;
        int ans = 0;

        for(int i = 0, j = 0; i < n && j < n;) {
            while(i < n && team[i] != 0) i++;
            while(j < n && team[j] != 1) j++;
            if(i == n || j == n) {
                break;
            }
            if(Math.abs(i - j) <= dist) {
                ans++;
                i++;
                j++;
            } else {
                if(i < j) {
                    i++;
                } else {
                    j++;
                }
            }
        }

        return ans;
    }
}
