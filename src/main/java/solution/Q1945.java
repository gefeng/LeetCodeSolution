package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Sum of Digits of String After Convert",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/sum-of-digits-of-string-after-convert/"
)
public class Q1945 {
    /**
     * Time:  O(N * K)
     * Space: O(N)
     * */
    public int getLucky(String s, int k) {
        int n = s.length();
        Queue<Integer> digits = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a' + 1;
            if(c > 9) {
                digits.offer(c % 10);
                digits.offer(c / 10);
            } else {
                digits.offer(c);
            }
        }

        int res = 0;
        while(k > 0) {
            int sum = 0;

            while(!digits.isEmpty()) {
                sum += digits.poll();
            }

            res = sum;

            while(sum != 0) {
                digits.offer(sum % 10);
                sum /= 10;
            }

            k--;
        }

        return res;
    }
}
