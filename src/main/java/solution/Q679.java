package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "24 Game",
        difficulty = QDifficulty.HARD,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/24-game/"
)
public class Q679 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    private double exp = 1e-13;
    public boolean judgePoint24(int[] cards) {
        List<Double> res = solve(cards);
        for(double x : res) {
            if(x >= 24.0 - exp && x <= 24.0 + exp) return true;
        }
        return false;
    }

    private List<Double> solve(int[] cards) {
        int n = cards.length;
        List<Double> res = new ArrayList<>();

        if(n == 1) {
            res.add((double)cards[0]);
            return res;
        }

        for(int i = 1; i <= n / 2; i++) { // # cards on left
            for(int mask = 1; mask < (1 << n); mask++) {
                if(Integer.bitCount(mask) == i) {
                    int[] l = new int[i];
                    int[] r = new int[n - i];
                    int p1 = 0;
                    int p2 = 0;
                    for(int j = 0; j < n; j++) {
                        if((mask & (1 << j)) != 0) {
                            l[p1++] = cards[j];
                        } else {
                            r[p2++] = cards[j];
                        }
                    }

                    List<Double> lres = solve(l);
                    List<Double> rres = solve(r);

                    for(double x : lres) {
                        for(double y : rres) {
                            res.add(x + y);
                            res.add(x - y);
                            res.add(x * y);
                            if(y < 0.0 - exp || y > 0.0 + exp) res.add(x / y);
                        }
                    }
                }
            }
        }

        return res;
    }
}
