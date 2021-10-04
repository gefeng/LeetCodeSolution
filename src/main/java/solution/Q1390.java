package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Four Divisors",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/four-divisors/"
)
public class Q1390 {
    /**
     * Time:  O(N * sqrt(N))
     * Space: O(1)
     * */
    public int sumFourDivisors(int[] nums) {
        int ans = 0;

        for(int num : nums) {
            List<Integer> div = getDivisors(num);
            if(div.size() == 4) {
                for(int d : div) {
                    ans += d;
                }
            }
        }

        return ans;
    }

    private List<Integer> getDivisors(int x) {
        List<Integer> div = new ArrayList<>();

        for(int i = 1; i * i <= x; i++) {
            if(x % i == 0) {
                div.add(i);
                if(i * i != x) {
                    div.add(x / i);
                }
            }
        }

        return div;
    }
}
