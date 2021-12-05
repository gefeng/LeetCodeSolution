package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Finding 3-Digit Even Numbers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/finding-3-digit-even-numbers/"
)
public class Q2094 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int[] findEvenNumbers(int[] digits) {
        List<Integer> ans = new ArrayList<>();

        int[] cnt = new int[10];
        for(int i = 0; i < digits.length; i++) {
            cnt[digits[i]]++;
        }

        for(int i = 100; i < 1000; i += 2) {
            int[] t = new int[10];
            int x = i;
            while(x != 0) {
                t[x % 10]++;
                x /= 10;
            }

            boolean valid = true;
            for(int j = 0; j < 10; j++) {
                if(cnt[j] < t[j]) {
                    valid = false;
                    break;
                }
            }
            if(valid) {
                ans.add(i);
            }
        }

        int[] ret = new int[ans.size()];
        for(int i = 0; i < ret.length; i++) {
            ret[i] = ans.get(i);
        }

        return ret;
    }
}
