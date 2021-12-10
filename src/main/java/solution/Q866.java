package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Prime Palindrome",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/prime-palindrome/"
)
public class Q866 {
    /**
     * Time:  O(9 ^ 4 * sqrt(N))
     * Space: O(9 ^ 4)
     * */
    List<Integer> pal = new ArrayList<>();
    public int primePalindrome(int n) {
        char[] c = Integer.toString(n).toCharArray();

        for(int i = 1; i <= 9; i++) {
            gen(i, 0, 0, 0, 1);
        }

        int ans = 0;
        for(int x : pal) {
            if(x >= n && isPrime(x)) {
                ans = x;
                break;
            }
        }
        return ans;
    }

    private void gen(int n, int len, int l, int r, int d) {
        if(len == n / 2) {
            if(n % 2 == 0) {
                pal.add(l * d + r);
            } else {
                for(int i = 0; i < 10; i++) {
                    pal.add((l * 10 + i) * d + r);
                }
            }
            return;
        }

        int st = len == 0 ? 1 : 0;
        for(int i = st; i < 10; i++) {
            gen(n, len + 1, l * 10 + i, i * d + r, d * 10);
        }
    }

    private boolean isPrime(int x) {
        if(x == 1) return false;
        if(x == 2) return true;
        for(int i = 2; i * i <= x; i++) {
            if(x % i == 0) return false;
        }
        return true;
    }
}
