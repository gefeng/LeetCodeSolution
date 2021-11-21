package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of k-Mirror Numbers",
        difficulty = QDifficulty.HARD,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/sum-of-k-mirror-numbers/"
)
public class Q2081 {
    /**
     * The time complexity should be more tight by only generating n smallest numbers.
     *
     * Time:  O(10 ^ N)
     * Space: O(N)
     * */
    long ans = 0;
    int n;
    public long kMirror(int k, int n) {
        this.n = n;

        for(int i = 1; this.n > 0; i++) {
            dfs(i, 0, 0, 0, 1, k);
        }


        return ans;
    }

    private void dfs(int len, int cur, long l, long r, long d, int k) {
        if(n == 0) {
            return;
        }
        if(cur == len / 2) {
            if(len % 2 == 0) {
                if(isMirror(Long.toString(l * d + r, k))) {
                    ans += l * d + r;
                    n--;
                }

            } else {
                for(int i = 0; i < 10; i++) {
                    long x = (l * 10 + i) * d + r;
                    if(x != 0 && isMirror(Long.toString(x, k))) {
                        ans += x;
                        n--;
                        if(n == 0) {
                            break;
                        }
                    }
                }
            }
            return;
        }

        int st = cur == 0 ? 1 : 0;
        for(int i = st; i < 10; i++) {
            dfs(len, cur + 1, l * 10 + i, i * d + r, d * 10, k);
        }
    }

    private boolean isMirror(String s) {
        for(int l = 0, r = s.length() - 1; l < r; l++, r--) {
            if(s.charAt(l) != s.charAt(r)) {
                return false;
            }
        }
        return true;
    }
}
