package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "The kth Factor of n",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/the-kth-factor-of-n/"
)
public class Q1492 {
    /**
     * Time:  O(sqrt(N))
     * Space: O(sqrt(N))
     * */
    public int kthFactor(int n, int k) {
        List<Integer> f = new ArrayList<>();

        for(int i = 1; i * i <= n; i++) {
            if(n % i == 0) {
                k--;
                f.add(i);
            }
            if(k == 0) {
                return i;
            }
        }

        int last = f.get(f.size() - 1);
        int cnt = last * last == n ? f.size() - 1 : f.size();

        return k > cnt ? -1 : n / f.get(f.size() - k);
    }
}
