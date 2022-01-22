package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Strong Password Checker",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/strong-password-checker/"
)
public class Q420 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2)
     * */
    public int strongPasswordChecker(String password) {
        int n = password.length();
        int ans = n;
        char[] c = password.toCharArray();
        int[][] digits = new int[n][n];
        int[][] lower = new int[n][n];
        int[][] upper = new int[n][n];
        int[][] replace = new int[n][n];

        for(int i = 0; i < n; i++) {
            int d = 0, l = 0, u = 0;
            for(int j = i; j < n; j++) {
                if(c[j] >= '0' && c[j] <= '9') {
                    d = 1;
                }
                if(c[j] >= 'a' && c[j] <= 'z') {
                    l = 1;
                }
                if(c[j] >= 'A' && c[j] <= 'Z') {
                    u = 1;
                }

                int r = 0;
                for(int a = i; a <= j; ) {
                    int b = a;
                    while(b <= j && c[a] == c[b]) b++;
                    r += (b - a) / 3;
                    a = b;
                }

                digits[i][j] = d;
                lower[i][j] = l;
                upper[i][j] = u;
                replace[i][j] = r;
            }
        }

        if(n < 6) {
            int need = 3 - (digits[0][n - 1] + lower[0][n - 1] + upper[0][n - 1]);
            int mustdo = 6 - n;
            ans = mustdo + Math.max(need - mustdo, 0);
        } else if(n <= 20) {
            for(int len = 1; len <= n; len++) {
                for(int i = 0; i + len - 1 < n; i++) {
                    int need = 3 - (digits[i][i + len - 1] + lower[i][i + len - 1] + upper[i][i + len - 1]);
                    int mustdo = replace[i][i + len - 1];
                    ans = Math.min(ans, Math.max(need, mustdo) + n - len);
                }
            }
        } else {
            int tot = 0;
            int need = 3 - (digits[0][n - 1] + lower[0][n - 1] + upper[0][n - 1]);
            int delete = n - 20;
            tot += delete;
            Queue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a % 3));
            for(int i = 0; i < n; ) {
                int j = i;
                while(j < n && c[j] == c[i]) {
                    j++;
                }
                if(j - i > 2) pq.offer(j - i);
                i = j;
            }

            while(!pq.isEmpty()) {
                if(pq.peek() % 3 + 1 > delete) break;

                int len = pq.poll();
                delete -= len % 3 + 1;
                len -= len % 3 + 1;
                if(len > 2)
                    pq.offer(len);
            }
            int r = 0;
            while(!pq.isEmpty()) {
                r += pq.poll() / 3;
            }
            tot += Math.max(r, need);
            ans = tot;
        }

        return ans;
    }
}
