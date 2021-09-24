package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Simplified Fractions",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/simplified-fractions/submissions/"
)
public class Q1447 {
    /**
     * Time:  O(N ^ 2 * logN)
     * Space: O(N ^ 2)
     * */
    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();

        for(int i = 2; i <= n; i++) {
            for(int j = 1; j < i; j++) {
                if(gcd(i, j) == 1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(j).append("/").append(i);
                    ans.add(sb.toString());
                }
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        if(a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
}
