package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Sequential Digits",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ENUMERATION,
        url = "https://leetcode.com/problems/sequential-digits/"
)
public class Q1291 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<Integer> sequentialDigits(int low, int high) {
        int l = countDigits(low);
        int r = countDigits(high);
        List<Integer> ans = new ArrayList<>();

        for(int i = l; i <= r; i++) {
            for(int j = 1; j + i <= 10; j++) {
                int d = j;
                int len = 0;
                int num = 0;
                while(len != i) {
                    num = num * 10 + d;
                    len++;
                    d++;
                }
                if(num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }

        return ans;
    }

    private int countDigits(int x) {
        int cnt = 0;
        while(x != 0) {
            x /= 10;
            cnt++;
        }
        return cnt;
    }
}
