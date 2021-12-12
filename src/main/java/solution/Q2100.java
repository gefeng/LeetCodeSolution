package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Find Good Days to Rob the Bank",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/find-good-days-to-rob-the-bank/"
)
public class Q2100 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;
        List<Integer> ans = new ArrayList<>();
        int[] l = new int[n];
        int[] r = new int[n];

        for(int i = 1; i < n; i++) {
            if(security[i] <= security[i - 1]) {
                l[i] = l[i - 1] + 1;
            }
        }


        for(int i = n - 2; i >= 0; i--) {
            if(security[i] <= security[i + 1]) {
                r[i] = r[i + 1] + 1;
            }
        }

        for(int i = 0; i < n; i++) {
            if(i - time >= 0 && i + time < n && l[i] >= time && r[i] >= time) {
                ans.add(i);
            }
        }

        return ans;
    }
}
