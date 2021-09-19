package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Longest Subsequence Repeated k Times",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/longest-subsequence-repeated-k-times/"
)
public class Q2014 {
    /**
     * The constraints "2 <= n < k * 8" tells us there are at most 7 characters of result string.
     * Try all possible candidates.
     *
     * Time:  O(7! * N)
     * Space: O()
     * */
    public String longestSubsequenceRepeatedK(String s, int k) {
        String ans = "";
        Queue<String> queue = new ArrayDeque<>();

        queue.offer("");

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                String cur = queue.poll();

                ans = cur;

                for(int j = 0; j < 26; j++) {
                    String next = cur + (char)(j + 'a');

                    if(next.length() < 8 && isSubseq(s, next, k)) {
                        queue.offer(next);
                    }
                }
            }
        }

        return ans;
    }

    private boolean isSubseq(String s, String t, int k) {
        int i = 0, j = 0, repeat = 0;
        int m = s.length();
        int n = t.length();
        while(i < m && j < n) {
            if(s.charAt(i) == t.charAt(j)) {
                j++;
            }

            if(j == n) {
                repeat++;
                j = 0;
            }
            if(repeat == k) {
                return true;
            }

            // pruning 800ms -> 450ms
            if((k - repeat - 1) * n + n - j > m - i - 1) {
                return false;
            }

            i++;
        }

        return false;
    }
}
