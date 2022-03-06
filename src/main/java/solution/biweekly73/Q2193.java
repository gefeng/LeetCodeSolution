package solution.biweekly73;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of Moves to Make Palindrome",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/biweekly-contest-73/problems/minimum-number-of-moves-to-make-palindrome/"
)
public class Q2193 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int minMovesToMakePalindrome(String s) {
        int n = s.length();
        int ans = 0;
        char[] arr = s.toCharArray();

        for(int l = 0, r = n - 1; l < r; l++, r--) {
            int best = r - l + 1;
            int bestL = 0;
            int bestR = 0;
            for(char c = 'a'; c <= 'z'; c++) {
                int toL = -1;
                int toR = -1;

                for(int i = l; i <= r; i++) {
                    if(arr[i] == c) {
                        toL = i - l;
                        break;
                    }
                }

                for(int i = r; i >= l; i--) {
                    if(arr[i] == c) {
                        toR = r - i;
                        break;
                    }
                }

                if(toL == -1 || toL + l == r - toR) {
                    continue;
                }

                if(toL + toR < best) {
                    best = toL + toR;
                    bestL = toL + l;
                    bestR = r - toR;
                }
            }

            bubble(arr, bestL, l);
            bubble(arr, bestR, r);

            ans += best;
        }

        return ans;
    }

    private void bubble(char[] arr, int i, int j) {
        if(i > j) {
            for(int k = i; k > j; k--) {
                swap(arr, k, k - 1);
            }
        } else {
            for(int k = i; k < j; k++) {
                swap(arr, k, k + 1);
            }
        }
    }

    private void swap(char[] arr, int i, int j) {
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
    }
}
