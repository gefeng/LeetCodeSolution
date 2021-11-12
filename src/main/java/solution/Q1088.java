package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Confusing Number II",
        difficulty = QDifficulty.HARD,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/confusing-number-ii/"
)
public class Q1088 {
    /**
     * Time:  O(5 ^ 9)
     * Space: O(1)
     * */
    int ans = 0;
    int[] d1 = new int[] {0, 1, 6, 8, 9};
    int[] d2 = new int[] {0, 1, 9, 8, 6};

    public int confusingNumberII(int n) {
        dfs(n, 0, 0, 1);

        return ans;
    }

    private void dfs(long n, long a, long b, long base) {
        if(a > n) {
            return;
        }

        if(a != b) {
            ans += 1;
        }

        int st = a == 0 ? 1 : 0;

        for(int i = st; i < 5; i++) {
            dfs(n, a * 10 + d1[i], b + d2[i] * base, base * 10);
        }
    }
}
