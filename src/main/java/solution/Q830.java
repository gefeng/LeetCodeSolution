package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Positions of Large Groups",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/positions-of-large-groups/"
)
public class Q830 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = s.length();

        for(int i = 0; i < n;) {
            int j = i;
            while(j < n && s.charAt(i) == s.charAt(j)) {
                j++;
            }

            if(j - i >= 3 ) {
                ans.add(Arrays.asList(i, j - 1));
            }

            i = j;
        }

        return ans;
    }
}
