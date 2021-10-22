package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Greatest Sum Divisible by Three",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/greatest-sum-divisible-by-three/"
)
public class Q1262 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int ans = 0;

        List<Integer> rem1 = new ArrayList<>();
        List<Integer> rem2 = new ArrayList<>();

        for(int x : nums) {
            ans += x;
            if(x % 3 == 1) {
                rem1.add(x);
            } else if(x % 3 == 2) {
                rem2.add(x);
            }
        }
        Collections.sort(rem1);
        Collections.sort(rem2);

        int i = 0;
        int j = 0;
        int len1 = rem1.size(), len2 = rem2.size();
        if(ans % 3 != 0) {
            if(ans % 3 == 1) {
                int cand1 = i < len1 ? rem1.get(i) : Integer.MAX_VALUE;
                int cand2 = j + 1 < len2 ? rem2.get(j) + rem2.get(j + 1) : Integer.MAX_VALUE;
                if(cand1 == Integer.MAX_VALUE && cand2 == Integer.MAX_VALUE) {
                    return 0;
                }
                if(cand1 >= cand2) {
                    ans -= cand2;
                    j += 2;
                } else {
                    ans -= cand1;
                    i += 1;
                }
            } else {
                int cand1 = i + 1 < len1 ? rem1.get(i) + rem1.get(i + 1) : Integer.MAX_VALUE;
                int cand2 = j < len2 ? rem2.get(j) : Integer.MAX_VALUE;
                if(cand1 == Integer.MAX_VALUE && cand2 == Integer.MAX_VALUE) {
                    return 0;
                }
                if(cand1 >= cand2) {
                    ans -= cand2;
                    j += 1;
                } else {
                    ans -= cand1;
                    i += 2;
                }
            }
        }


        return ans;
    }
}
