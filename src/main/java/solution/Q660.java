package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Remove 9",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/remove-9/"
)
public class Q660 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public int newInteger(int n) {
        List<Integer> base9 = new ArrayList<>();
        int ans = 0;

        while(n > 0) {
            base9.add(n % 9);
            n /= 9;
        }

        for(int i = base9.size() - 1; i >= 0; i--) {
            ans = ans * 10 + base9.get(i);
        }

        return ans;
    }
}
