package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Permutation Sequence",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/permutation-sequence/"
)
public class Q60 {
    /**
     * Math problem, obtain kth permutation from factorial system.
     * I might forget this in two days.
     *
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public String getPermutation(int n, int k) {
        int[] factorials = new int[n];
        List<Integer> digits = new ArrayList<>();

        for(int i = 1; i <= n; i++) {
            digits.add(i);
        }

        factorials[0] = 1;
        for(int i = 1; i < n; i++) {
            factorials[i] = factorials[i - 1] * i;
        }

        StringBuilder sb = new StringBuilder();
        k--;
        for(int i = n - 1; i >= 0; i--) {
            int idx = k / factorials[i];

            sb.append(digits.get(idx));

            k -= idx * factorials[i];

            digits.remove(idx);
        }

        return sb.toString();
    }
}
